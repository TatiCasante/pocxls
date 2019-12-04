package br.com.confidencecambio.aplicacaotemplate.aplicacao.kafka.producers;

public interface IExemploSucessoProducer {
    void sendMessageSucesso(String key, String data);
}
