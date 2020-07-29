package tk.ingxx.canal.executor.executorImpl;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
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
@Component
public class GoodsCanalExecutor extends AbstractCanalExecutor {

    @Resource
    private CanalConnector goodsConnector;

    public void connect(){
        goodsConnector.connect();
        goodsConnector.subscribe(".*\\..*");
        goodsConnector.rollback();
    }

    @Override
    public void readRow(List<CanalEntry.Entry> entry) {

    }
}
