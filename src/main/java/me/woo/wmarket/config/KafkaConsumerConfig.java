package me.woo.wmarket.config;

import com.google.common.collect.ImmutableMap;
import me.woo.wmarket.chatting.dto.MessageDetails;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  @Value("${spring.kafka.consumer.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${spring.kafka.consumer.group-id}")
  private String groupId;

  @Value("${spring.kafka.consumer.auto-offset-reset}")
  private String offSet;

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, MessageDetails> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, MessageDetails> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

  @Bean
  public ConsumerFactory<String, MessageDetails> consumerFactory() {
    JsonDeserializer<MessageDetails> deserializer = new JsonDeserializer<>(MessageDetails.class);
    deserializer.setRemoveTypeHeaders(false);
    deserializer.addTrustedPackages("*");
    deserializer.setUseTypeMapperForKey(true);

    ImmutableMap<String, Object> config = ImmutableMap.<String, Object>builder()
        .put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
        .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
        .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer)
        .put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offSet)
        .put("group.id", groupId)
        .build();

    return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
  }

}
