package br.com.confidencecambio.aplicacaotemplate.aplicacao.kafka.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExemploSucessoProducer implements IExemploSucessoProducer {

    private static final Logger log = LoggerFactory.getLogger(ExemploSucessoProducer.class);

    private final String exemploSucessoTopico;
    private final IDefaultProducer defaultProducer;

    public ExemploSucessoProducer(
        @Value("${kafka.topics.exemplo.sucesso}") final String exemploSucessoTopico,
        final IDefaultProducer defaultProducer) {

        this.exemploSucessoTopico = exemploSucessoTopico;
        this.defaultProducer = defaultProducer;
    }

    @Override
    public void sendMessageSucesso(final String key, final String data) {
        try {
            final String json = new ObjectMapper().writeValueAsString("");
            defaultProducer.call(exemploSucessoTopico, key, json);
            log.debug("Mensagem enviada com para o topico {} com key {}", exemploSucessoTopico, key);
        } catch (JsonProcessingException e) {
            log.error("Falha para parsear mensagem de sucesso , key {} ", key, e);
        }
    }
}
