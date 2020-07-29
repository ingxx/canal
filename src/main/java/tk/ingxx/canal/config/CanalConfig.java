package tk.ingxx.canal.config;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.net.InetSocketAddress;

/**
 * @program: canal
 * @description:
 * @author: weijiankai
 * @create: 2020-07-27 14:14
 **/

@ConfigurationProperties(prefix="canal")
@Configuration
@Order(1)
@Data
public class CanalConfig {

    private String host;

    private Integer port;

    private String goods;

    private String username;

    private String password;

    private Integer batchSize;

    @Bean
    @Order(2)
    public CanalConnector goodsConnector(){
        return CanalConnectors.newSingleConnector(new InetSocketAddress(host,
                port), goods, username, password);
    }

}
