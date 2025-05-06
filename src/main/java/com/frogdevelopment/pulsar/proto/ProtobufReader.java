package com.frogdevelopment.pulsar.proto;

import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Reader;

import io.micronaut.pulsar.MessageSchema;
import io.micronaut.pulsar.annotation.PulsarReader;
import io.micronaut.pulsar.annotation.PulsarReaderClient;

@PulsarReaderClient
public class ProtobufReader {

    private final Reader<TestMessageOuterClass.TestMessage> reader;

    public ProtobufReader(@PulsarReader(
            schema = MessageSchema.PROTOBUF,
            topic = "persistent://public/default/messages",
            readerName = "protobuf-reader",
            readTimeout = 5) final Reader<TestMessageOuterClass.TestMessage> reader) {
        this.reader = reader;
    }


    TestMessageOuterClass.TestMessage poll() throws PulsarClientException {
        return reader.readNext().getValue();
    }

    void clear() throws PulsarClientException {
        reader.seek(System.currentTimeMillis());
    }
}
