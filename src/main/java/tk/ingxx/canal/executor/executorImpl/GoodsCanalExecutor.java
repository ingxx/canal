package tk.ingxx.canal.executor.executorImpl;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import lombok.Data;
import org.springframework.stereotype.Component;
import tk.ingxx.canal.executor.executorImpl.AbstractCanalExecutor;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: canal
 * @description:
 * @author: weijiankai
 * @create: 2020-07-27 17:21
 **/
public class GoodsCanalExecutor extends AbstractCanalExecutor {

    /**
     * 初始化连接类
     * @param canalConnector
     */
    public GoodsCanalExecutor(CanalConnector canalConnector) {
        super(canalConnector);
    }

    @Override
    public void readRow(List<CanalEntry.Entry> entry) {

    }
}
