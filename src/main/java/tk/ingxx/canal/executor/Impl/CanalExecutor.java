package tk.ingxx.canal.executor.Impl;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import lombok.Data;
import org.springframework.stereotype.Component;
import tk.ingxx.canal.canalHandler.EntryHandler;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: canal
 * @description:
 * @author: weijiankai
 * @create: 2020-07-27 17:21
 **/
public class CanalExecutor extends AbstractCanalExecutor {

    /**
     * 初始化连接类
     * @param canalConnector
     */
    public CanalExecutor(CanalConnector canalConnector, EntryHandler entryHandler) {
        super(canalConnector,entryHandler);
    }
}
