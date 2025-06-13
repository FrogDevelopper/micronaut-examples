package com.frogdevelopment.pulsar.proto;

import io.micronaut.pulsar.MessageSchema;
import io.micronaut.pulsar.annotation.PulsarProducer;
import io.micronaut.pulsar.annotation.PulsarProducerClient;

@PulsarProducerClient
public interface ProtobufProducer {

    @PulsarProducer(schema = MessageSchema.PROTOBUF, topic = "persistent://public/default/messages", producerName = "protobuf-producer")
    void sendBlocking(TestMessageOuterClass.TestMessage message);
}
