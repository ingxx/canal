package tk.ingxx.canal.executor;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;

import java.util.List;

/**
 * 定义接口
 */
public interface CanalExecutor {
    /**
     * 初始化连接
     */
    void connect();

    /**
     * 执行轮询
     */
    void execute();

    /**
     * 销毁
     */
    void disconnect();
}
