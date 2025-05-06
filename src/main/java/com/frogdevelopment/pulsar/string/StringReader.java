package com.frogdevelopment.pulsar.string;

import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Reader;

import io.micronaut.pulsar.MessageSchema;
import io.micronaut.pulsar.annotation.PulsarReader;
import io.micronaut.pulsar.annotation.PulsarReaderClient;

@PulsarReaderClient
public class StringReader {

    private final Reader<String> reader;

    public StringReader(@PulsarReader(
            schema = MessageSchema.STRING,
            topic = "persistent://public/default/messages",
            readerName = "simple-string-reader",
            readTimeout = 5) final Reader<String> reader) {
        this.reader = reader;
    }


    String poll() throws PulsarClientException {
        return reader.readNext().getValue();
    }

    void clear() throws PulsarClientException {
        reader.seek(System.currentTimeMillis());
    }
}
