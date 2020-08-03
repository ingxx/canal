package tk.ingxx.canal.canalHandler;

import com.alibaba.otter.canal.protocol.CanalEntry;

import java.util.List;
import java.util.Map;

public class CanalHandler implements EntryHandler{

    @Override
    public void insert(List<CanalEntry.Column> list) {
        printColumn(list);
    }

    @Override
    public void update(List<CanalEntry.Column> before, List<CanalEntry.Column> after) {
        printColumn(before);
        printColumn(after);
    }

    @Override
    public void delete(List<CanalEntry.Column> list) {
        printColumn(list);
    }

    private static void printColumn(List<CanalEntry.Column> columns) {
        for (CanalEntry.Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
    }
}
