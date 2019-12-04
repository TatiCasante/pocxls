package br.com.confidencecambio.aplicacaotemplate.aplicacao.kafka.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultProducerBean implements IDefaultProducer {

    private static final Logger logger = LoggerFactory.getLogger(DefaultProducerBean.class);

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public DefaultProducerBean(
        final KafkaTemplate<String, String> kafkaTemplate,
        final ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public String call(final String topico, final Object object) {
        return this.call(topico, UUID.randomUUID().toString(), object);
    }

    @Override
    public String call(final String topico, final String key, final Object object) {

        try {
            final String json = objectMapper.writeValueAsString(object);
            kafkaTemplate.send(topico, key, json);

            logger.debug("[{}] Objeto {} enviado para o topico {}", key, object.getClass().getSimpleName(), topico);

        } catch (JsonProcessingException e) {
            logger.error("Falha ao converter objeto: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }

        return key;
    }

    @Override
    public String call(final String topico, final String key, final String json) {
        kafkaTemplate.send(topico, key, json);
        logger.debug("[{}] Objeto {} enviado para o topico {}", key, json.getClass().getSimpleName(), topico);
        return key;
    }
}