////package com.example.rabbitmgspringbootdemo.config;
////import org.springframework.amqp.core.*;
//////import org.springframework.amqp.rabbit.connection.ConnectionFactory;
////import org.springframework.amqp.rabbit.core.RabbitTemplate;
////import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
////import org.springframework.amqp.support.converter.MessageConverter;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//////@Configuration
////public class RabbitMQConfig {
////    @Value("${rabbitmq.queue.name}")
////    private String queue;
////    @Value("${rabbitmq.new.queue.name}")
////    private String newQueue;
////    @Value("${rabbitmq.json.queue.name}")
////    private String jsonQueue;
////    @Value("${rabbitmq.exchange.name}")
////    private  String exchange;
////    @Value("${rabbitmq.routing.key}")
////    private  String routingKey;
////    @Value("${rabbitmq.routing.json.key}")
////    private String routingJsonKey;
////    // spring bean  for RabbitMQ Queue
////    @Bean
////    public Queue queue() {
////        return new Queue(queue);
////    }
////    @Bean
////    public Queue newQueue() {
////        return new Queue(newQueue);
////    }
////    @Bean
////    public TopicExchange exchange() {
////        return new TopicExchange(exchange);
////    }
////    // binding between queue and exchange using routing key
////    @Bean
////    public Binding binding (){
////        return BindingBuilder.bind(queue())
////                .to(exchange()).with(routingKey);
////    }
////    @Bean
////    public Binding newBinding (){
////        return BindingBuilder.bind(newQueue())
////                .to(exchange()).with(routingKey);
////    }
////    //  Stored JSON Queue
////    @Bean
////    public Queue jsonQueue() {
////        return new Queue(jsonQueue);
////    }
////    @Bean
////    public  Binding JsonBinding(){
////        return BindingBuilder
////                .bind(jsonQueue())
////                .to(exchange())
////                .with(routingJsonKey);
////    }
////    @Bean
////    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
////        RabbitTemplate rabbitTemplate =  new RabbitTemplate(connectionFactory);
////        rabbitTemplate.setMessageConverter(converter());
////        return  rabbitTemplate;
////    }
////    @Bean
////    public MessageConverter converter(){
////        return new Jackson2JsonMessageConverter();
////    }
//
//
//// new updates
//// Updated RabbitMQConfig.java - Fixed configuration
//package com.example.rabbitmgspringbootdemo.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConfig {
//
//    @Value("${rabbitmq.queue.name}")
//    private String queue;
//
//    @Value("${rabbitmq.new.queue.name}")
//    private String newQueue;
//
//    @Value("${rabbitmq.json.queue.name}")
//    private String jsonQueue;
//
//    @Value("${rabbitmq.exchange.name}")
//    private String exchange;
//
//    @Value("${rabbitmq.routing.key}")
//    private String routingKey;
//
//    @Value("${rabbitmq.routing.json.key}")
//    private String routingJsonKey;
//
//    // Create DURABLE queues (they persist server restarts)
//    @Bean
//    public Queue queue() {
//        return QueueBuilder.durable(queue).build();
//    }
//
//    @Bean
//    public Queue newQueue() {
//        return QueueBuilder.durable(newQueue).build();
//    }
//
//    @Bean
//    public Queue jsonQueue() {
//        return QueueBuilder.durable(jsonQueue).build();
//    }
//
//    // Create DURABLE exchange
//    @Bean
//    public TopicExchange exchange() {
//        return ExchangeBuilder.topicExchange(exchange).durable(true).build();
//    }
//
//    // Bindings
//    @Bean
//    public Binding binding() {
//        return BindingBuilder.bind(queue())
//                .to(exchange())
//                .with(routingKey);
//    }
//
//    @Bean
//    public Binding newBinding() {
//        return BindingBuilder.bind(newQueue())
//                .to(exchange())
//                .with(routingKey);
//    }
//
//    @Bean
//    public Binding jsonBinding() {
//        return BindingBuilder.bind(jsonQueue())
//                .to(exchange())
//                .with(routingJsonKey);
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(messageConverter());
//        return template;
//    }
//
//    @Bean
//    public MessageConverter messageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//}


// new Updates
package com.example.demostudent2025.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE  = "rabbit_grace_queue";
    public static final String EXCHANGE  = "rabbit_grace_exchange";
    public static final String ROUTING_KEY  = "rabbit_grace_r_key";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE);
    }
    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange){
        return  BindingBuilder.bind(queue).to(directExchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate getTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
