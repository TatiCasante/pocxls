package br.com.confidencecambio.aplicacaotemplate.aplicacao.kafka.producers;

public interface IDefaultProducer {
    String call(String topico, Object object);

    String call(String topico, String key, Object object);

    String call(String topico, String key, String json);
}