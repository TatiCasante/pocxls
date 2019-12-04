package br.com.confidencecambio.aplicacaotemplate.aplicacao.configs.kafka;

public enum EKafkaConfidenceProperties {

    CONFIDENCE_KAFKA_BOOTSTRAP_SERVERS_CONFIG("confidence.kafka.bootstrap.server.config"),
    CONFIDENCE_KAFKA_PUBSUB_POLL_TIMEOUT("confidence.kafka.pubsub.poll.timeout"),
    CONFIDENCE_KAFKA_PUBSUB_FACTORY_CONCURRENCY("confidence.kafka.pubsub.factory.concurrency"),
    CONFIDENCE_KAFKA_PUBSUB_RETRY_BACKOFF_PERIOD("confidence.kafka.pubsub.retry.backoff.period"),
    CONFIDENCE_KAFKA_PUBSUB_RETRY_ATTEMPTS("confidence.kafka.pubsub.retry.attempts"),
    CONSUMER_GROUP_ID("confidence.kafka.consumer.id"),
    MAX_POLL_RECORDS("confidence.kafka.max.poll.records");

    private String property;

    EKafkaConfidenceProperties(final String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
