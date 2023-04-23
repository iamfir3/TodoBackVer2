//package com.example.todobackver2.security;
//
//import com.example.todobackver2.entity.ChatEntity;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.EventListener;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Value("${spring.rabbitmq.host}")
//    private String rabbitMQHost;
//
//    @Value("${spring.rabbitmq.port}")
//    private int rabbitMQPort;
//
//    @Value("${spring.rabbitmq.username}")
//    private String rabbitMQUsername;
//
//    @Value("${spring.rabbitmq.password}")
//    private String rabbitMQPassword;
//
//    @Value("${chat.topic.exchange}")
//    private String topicExchangeName;
//    @Value("${chat.queue.prefix}")
//    private String queuePrefix;
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws").withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.enableStompBrokerRelay("/topic")
//                .setRelayHost(rabbitMQHost)
//                .setRelayPort(rabbitMQPort)
//                .setClientLogin(rabbitMQUsername)
//                .setClientPasscode(rabbitMQPassword);
//        registry.setApplicationDestinationPrefixes("/app");
//    }
//
//    @Bean
//    public MessageConverter messageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setHost(rabbitMQHost);
//        connectionFactory.setPort(rabbitMQPort);
//        connectionFactory.setUsername(rabbitMQUsername);
//        connectionFactory.setPassword(rabbitMQPassword);
//        return connectionFactory;
//    }
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(messageConverter());
//        return rabbitTemplate;
//    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void init() {
//        rabbitTemplate.setExchange(topicExchangeName);
//    }
//
//    @PostMapping("/message")
//    public void handleMessage(@RequestBody ChatEntity chatMessage) {
//        rabbitTemplate.convertAndSend(topicExchangeName,queuePrefix+chatMessage.getRoomId(), chatMessage);
//    }
//}
