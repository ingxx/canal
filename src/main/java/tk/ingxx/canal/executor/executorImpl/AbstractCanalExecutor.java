package tk.ingxx.canal.executor.executorImpl;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import tk.ingxx.canal.executor.CanalExecutor;

import java.util.List;

/**
 * @program: canal
 * @description:
 * @author: weijiankai
 * @create: 2020-07-27 17:27
 **/
public abstract class AbstractCanalExecutor implements CanalExecutor {

    public CanalConnector connector;


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

    public final void disconnect(){
        connector.disconnect();
    }

    protected abstract void readRow(List<CanalEntry.Entry> entry);
}
