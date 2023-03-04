package kz.dar.tech.eventstoreapi.service;

import kz.dar.tech.eventstoreapi.document.Event;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventProducer {
    private final KafkaTemplate<String, Event> kafkaTemplate;

    public void sendEvent(
            Event event,
            String key
    ) {
        ProducerRecord<String, Event> producerRecord = new ProducerRecord<>(
                "event-topic",
                key,
                event);
        kafkaTemplate.send(producerRecord);
        System.out.println("Sent event: " + event + "with key: " + key);
    }
}
