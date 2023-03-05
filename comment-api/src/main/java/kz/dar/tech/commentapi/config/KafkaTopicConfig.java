package kz.dar.tech.commentapi.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic eventTopic() {
        return TopicBuilder.name("comment-topic")
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }
}
