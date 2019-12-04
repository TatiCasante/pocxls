package br.com.confidencecambio.aplicacaotemplate.aplicacao.kafka.producers;

import br.com.confidencecambio.aplicacaotemplate.exceptions.enums.ECodigoErro;

public interface IExemploFalhaProducer {
    void sendMessageErro(String key, ECodigoErro codigoErro, String data);
}
