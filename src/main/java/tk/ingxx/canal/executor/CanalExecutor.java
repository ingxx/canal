package tk.ingxx.canal.executor;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;

import java.util.List;

public interface CanalExecutor {
    void connect();

    void execute();

    void disconnect();
}
