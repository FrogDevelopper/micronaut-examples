package com.frogdevelopment.pulsar.string;

import io.micronaut.pulsar.MessageSchema;
import io.micronaut.pulsar.annotation.PulsarProducer;
import io.micronaut.pulsar.annotation.PulsarProducerClient;

@PulsarProducerClient
public interface StringProducer {

    @PulsarProducer(schema = MessageSchema.STRING,
                    topic = "persistent://public/default/messages",
                    producerName = "simple-string-producer")
    void sendBlocking(String message);
}
