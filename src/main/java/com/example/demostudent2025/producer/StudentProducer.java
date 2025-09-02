package com.example.demostudent2025.producer;


import com.example.demostudent2025.configuration.RabbitMQConfig;

import com.example.demostudent2025.domain.request.CreateStudentRequest;
import com.example.demostudent2025.persistence.entity.StudentEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class StudentProducer {

    private final RabbitTemplate rabbitTemplate;

    public StudentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(StudentEntity student, String action) {
        String message = action + " Student: " + student.toString();
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                student
        );
        System.out.println("📤 Sent message -> " + message);
    }
}
