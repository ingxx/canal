package tk.ingxx.canal.executor;

import io.netty.util.concurrent.SingleThreadEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.lang.reflect.Executable;
import java.util.Map;
import java.util.concurrent.*;

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

    private static ExecutorService executable = new ThreadPoolExecutor(10,10,120,
            TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));

    @Override
    public void run(String... args) throws Exception {
        Map<String, CanalExecutor> beansOfType = applicationContext.getBeansOfType(CanalExecutor.class);
        beansOfType.forEach((key,value)->{
            value.connect();
            //开启线程执行
            executable.execute(value::execute);
        });
    }

    @PreDestroy
    public void stop(){
        Map<String, CanalExecutor> beansOfType = applicationContext.getBeansOfType(CanalExecutor.class);
        beansOfType.forEach((key,value)->{
            value.disconnect();
        });
        executable.shutdown();
    }

}
