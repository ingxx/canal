package tk.ingxx.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class CanalApplication {


    @Resource
    private CanalConnector goodsConnector;

    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class, args);
    }

}
