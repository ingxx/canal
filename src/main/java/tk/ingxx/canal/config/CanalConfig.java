package tk.ingxx.canal.config;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.ingxx.canal.canalHandler.CanalHandler;
import tk.ingxx.canal.canalHandler.EntryHandler;
import tk.ingxx.canal.executor.Impl.CanalExecutor;

import java.net.InetSocketAddress;

/**
 * @program: canal
 * @description:
 * @author: weijiankai
 * @create: 2020-07-27 14:14
 **/

@ConfigurationProperties(prefix="canal")
@Configuration
@Data
public class CanalConfig {

    private String host;

    private Integer port;

    private String destination;

    private String username;

    private String password;

    private Integer batchSize;

    /**
     * 监控
     * @return
     */
    @Bean
    public CanalConnector connector(){
        return CanalConnectors.newSingleConnector(new InetSocketAddress(host,port), destination, username, password);
    }


    @Bean
    public EntryHandler canalHandler(){
        return new CanalHandler();
    }
    /**
     * 具体逻辑
     * @param connector
     * @return
     */
    @Bean
    public CanalExecutor canalExecutor(CanalConnector connector, EntryHandler canalHandler){
        return new CanalExecutor(connector,canalHandler);
    }
}
