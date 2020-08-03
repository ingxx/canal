package tk.ingxx.canal.executor.Impl;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import tk.ingxx.canal.canalHandler.EntryHandler;
import tk.ingxx.canal.executor.CanalExecutor;

import java.util.List;

/**
 * @program: canal
 * @description:
 * @author: weijiankai
 * @create: 2020-07-27 17:27
 **/
public abstract class AbstractCanalExecutor implements CanalExecutor {

    private CanalConnector connector;

    private EntryHandler<?> entryHandler;
    /**
     * 定义公共初始化
     * @param canalConnector
     */
    public AbstractCanalExecutor(CanalConnector canalConnector, EntryHandler<?> entryHandler){
        this.connector = canalConnector;
        this.entryHandler = entryHandler;
    }

    public void connect(){
        connector.connect();
        connector.subscribe(".*\\..*");
        connector.rollback();
    }

    public final void execute() {
        int emptyCount = 0;
        while (emptyCount < 120) {
            Message message = connector.getWithoutAck(1000); // 获取指定数量的数据
            long batchId = message.getId();
            int size = message.getEntries().size();
            if (batchId == -1 || size == 0) {
                emptyCount++;
                System.out.println("empty count : " + emptyCount);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            } else {
                emptyCount = 0;
                readRow(message.getEntries());
            }
            connector.ack(batchId); // 提交确认
        }
    }

    /**
     * 读取到的数据交给子类处理
     * @param entrys
     */
    private final void readRow(List<CanalEntry.Entry> entrys){
        for (CanalEntry.Entry entry : entrys) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                continue;
            }

            CanalEntry.RowChange rowChage = null;
            try {
                rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            CanalEntry.EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == CanalEntry.EventType.DELETE) {
                } else if (eventType == CanalEntry.EventType.INSERT) {
                } else {
                    System.out.println("-------&gt; before");
                    System.out.println("-------&gt; after");
                }
            }
        }
    }

    public final void disconnect(){
        connector.disconnect();
    }
}
