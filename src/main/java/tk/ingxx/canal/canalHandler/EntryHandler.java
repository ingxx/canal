package tk.ingxx.canal.canalHandler;

import com.alibaba.otter.canal.protocol.CanalEntry;

import java.util.List;

public interface EntryHandler {
    default void insert(List<CanalEntry.Column> list) {

    }


    default void update(List<CanalEntry.Column> before, List<CanalEntry.Column> after) {

    }


    default void delete(List<CanalEntry.Column> list) {

    }
}
