package com.frogdevelopment.pulsar.string;

import io.micronaut.pulsar.annotation.PulsarProducer;
import io.micronaut.pulsar.annotation.PulsarProducerClient;

@PulsarProducerClient
public interface StringProducer {

    @PulsarProducer(topic = "persistent://public/default/messages", producerName = "simple-string-producer")
    void sendBlocking(String message);
}
