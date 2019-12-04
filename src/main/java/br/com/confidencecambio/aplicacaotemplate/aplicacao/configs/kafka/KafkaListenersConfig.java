package br.com.confidencecambio.aplicacaotemplate.aplicacao.configs.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Profile({"local-kafka"})
@Configuration
public class KafkaListenersConfig {

    private Environment environment;

    public KafkaListenersConfig(final Environment environment) {
        this.environment = environment;
    }

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactory() {
        final ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckOnError(false);
        factory.setConcurrency(Integer.valueOf(environment.getRequiredProperty(
            EKafkaConfidenceProperties.CONFIDENCE_KAFKA_PUBSUB_FACTORY_CONCURRENCY.getProperty())));
        factory.getContainerProperties()
            .setPollTimeout(Long.parseLong(environment.getRequiredProperty(
                EKafkaConfidenceProperties.CONFIDENCE_KAFKA_PUBSUB_POLL_TIMEOUT.getProperty())));
        factory.setRetryTemplate(getRetryTemplate());
        return factory;
    }

    @Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public RetryTemplate getRetryTemplate() {
        final RetryTemplate retryTemplate = new RetryTemplate();

        final FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(Long.parseLong(environment.getRequiredProperty(
            EKafkaConfidenceProperties.CONFIDENCE_KAFKA_PUBSUB_RETRY_BACKOFF_PERIOD.getProperty())));
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        final SimpleRetryPolicy policy = new SimpleRetryPolicy(Integer.parseInt(environment.getRequiredProperty(
            EKafkaConfidenceProperties.CONFIDENCE_KAFKA_PUBSUB_RETRY_ATTEMPTS.getProperty())),
            Collections.singletonMap(Exception.class, true)
        );
        retryTemplate.setRetryPolicy(policy);
        return retryTemplate;
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        final Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty(
            EKafkaConfidenceProperties.CONFIDENCE_KAFKA_BOOTSTRAP_SERVERS_CONFIG.getProperty()));
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", StringDeserializer.class);
        props.put("group.id", environment.getProperty(EKafkaConfidenceProperties.CONSUMER_GROUP_ID.getProperty()));
        props.put("max.poll.records", environment.getProperty(
            EKafkaConfidenceProperties.MAX_POLL_RECORDS.getProperty()));

        return props;
    }

}
