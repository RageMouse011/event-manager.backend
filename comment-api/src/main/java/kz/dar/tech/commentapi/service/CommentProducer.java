package kz.dar.tech.commentapi.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendComment(
            String comment,
            String key
    ) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(
                "comment-topic",
                key,
                comment);
        kafkaTemplate.send(producerRecord);
        System.out.println("Sent comment: " + comment + " with key: " + key);
    }
}
