package fun.mizhuo.mailserver.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

/**
 * @author: Mizhuo
 * @time: 2020/10/19 10:00 下午
 * @description: rabbitMq配置
 */
public class MqConfig {

    @Bean
    Queue queue(){
        return new Queue("mizhuo.mail");
    }
}
