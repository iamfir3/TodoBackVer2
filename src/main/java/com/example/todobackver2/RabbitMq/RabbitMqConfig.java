//package com.example.todobackver2.RabbitMq;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMqConfig {
//    @Value("${chat.topic.exchange}")
//    private String topicExchangeName;
//
//    @Value("${chat.queue.prefix}")
//    private String queuePrefix;
//
//    @Bean
//    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }
//
//    @Bean
//    public TopicExchange topicExchange() {
//        return new TopicExchange(topicExchangeName);
//    }
//
////    @Bean
////    public Queue queue() {
////        return new Queue(queuePrefix + ".chat", true, false, false);
////    }
//
//    @Bean
//    public Queue queue() {
//        return new AnonymousQueue();
//    }
//
//    @Bean
//    public Binding binding(Queue queue, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queue)
//                .to(topicExchange)
//                .with(queuePrefix + "*");
//    }
//
//
//}
