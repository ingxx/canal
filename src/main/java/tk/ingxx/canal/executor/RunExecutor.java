package tk.ingxx.canal.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: canal
 * @description:
 * @author: weijiankai
 * @create: 2020-07-27 17:44
 **/
@Component
public class RunExecutor implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        Map<String, CanalExecutor> beansOfType = applicationContext.getBeansOfType(CanalExecutor.class);
        beansOfType.forEach((key,value)->{
            value.connect();
        });
    }
}
