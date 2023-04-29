package me.woo.wmarket.config;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import me.woo.wmarket.chatting.dto.MessageDetails;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@EnableKafka
public class KafkaProducerConfig {

  @Value("${spring.kafka.producer.bootstrap-servers}")
  private String server;

  @Value("${spring.kafka.consumer.group-id}")
  private String groupId;

  @Bean
  public ProducerFactory<String, MessageDetails> producerFactory() {
    return new DefaultKafkaProducerFactory<>(kafkaProducerConfiguration());
  }

  @Bean
  public Map<String, Object> kafkaProducerConfiguration() {
    return ImmutableMap.<String, Object>builder()
        .put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server)
        .put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
        .put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class)
        .put("group.id", groupId)
        .build();
  }

  @Bean
  public KafkaTemplate<String, MessageDetails> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}
