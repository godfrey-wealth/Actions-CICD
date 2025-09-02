//package com.example.rabbitmgspringbootdemo.consumer;
//
//import com.example.rabbitmgspringbootdemo.domain.request.SocialMediaRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RabbitConsumer {
////    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConsumer.class);
////
////    @RabbitListener(queues = "${rabbitmq.queue.name}")
////    public void consume(String message) {
////        LOGGER.info("Message received -> {}", message);
////    }
////
////    @RabbitListener(queues = "${rabbitmq.new.queue.name}")
////    public void consumeNew(String message) {
////        LOGGER.info("Message received -> {}", message);
////    }
////
////    @RabbitListener(queues = "${rabbitmq.json.queue.name}")
////    public void consumeJson(SocialMediaRequest message) {
////        LOGGER.info("JSON Message received -> {}", message);
////    }
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConsumer.class);
//
//    // SLOW consumer for hello_javaguides - 10 second delay
//    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
//    public void consumeMessage(String message) {
//        LOGGER.info("🐰 String Message received from hello_javaguides -> {}", message);
//
//        // 10 second delay - plenty of time to see in UI!
//        try {
//            LOGGER.info("⏳ Processing message for 10 seconds...");
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//
//        LOGGER.info("✅ Finished processing: {}", message);
//    }
//
//    // SLOW consumer for Godfrey_Wealth - 8 second delay
//    @RabbitListener(queues = {"${rabbitmq.new.queue.name}"})
//    public void consumeNewQueueMessage(String message) {
//        LOGGER.info("🐰 String Message received from Godfrey_Wealth -> {}", message);
//
//        try {
//            LOGGER.info("⏳ Processing Godfrey_Wealth message for 8 seconds...");
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//
//        LOGGER.info("✅ Finished processing Godfrey_Wealth: {}", message);
//    }
//
//    // SLOW consumer for JSON messages - 15 second delay
//    @RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
//    public void consumeJsonMessage(SocialMediaRequest request) {
//        LOGGER.info("🐰 JSON Message received from json_queue_Grace -> {}", request);
//
//        try {
//            LOGGER.info("⏳ Processing JSON message for 15 seconds...");
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//
//        LOGGER.info("✅ Finished processing JSON: {}", request.getHeadline());
//    }
//}


// new updates


package com.example.demostudent2025.consumer;



import com.example.demostudent2025.configuration.RabbitMQConfig;
import com.example.demostudent2025.domain.request.CreateStudentRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {
    @RabbitListener(queues = RabbitMQConfig.QUEUE, autoStartup = "false")
    public void consume(CreateStudentRequest orderDTO) {
        System.out.println("consumer is able to consume message from queues " + orderDTO);
    }
}
