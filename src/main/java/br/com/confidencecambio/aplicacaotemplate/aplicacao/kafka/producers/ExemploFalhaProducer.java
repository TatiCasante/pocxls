package br.com.confidencecambio.aplicacaotemplate.aplicacao.kafka.producers;

import br.com.confidencecambio.aplicacaotemplate.exceptions.enums.ECodigoErro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExemploFalhaProducer implements IExemploFalhaProducer {

    private static final Logger log = LoggerFactory.getLogger(ExemploFalhaProducer.class);

    private final String exemploFalhaTopico;
    private final IDefaultProducer defaultProducer;

    public ExemploFalhaProducer(
        @Value("${kafka.topics.exemplo.falha}") final String exemploFalhaTopico,
        final IDefaultProducer defaultProducer) {

        this.exemploFalhaTopico = exemploFalhaTopico;
        this.defaultProducer = defaultProducer;
    }

    @Override
    public void sendMessageErro(final String key, final ECodigoErro codigoErro, final String data) {
        try {
            final String json = new ObjectMapper().writeValueAsString("");
            log.debug("Enviando mensagem de erro, key: {} , erros :{}", key, json);
            defaultProducer.call(exemploFalhaTopico, key, json);
        } catch (JsonProcessingException e) {
            log.error("Falha ao parsear mensagem de retorno ", e);
        }
    }
}
