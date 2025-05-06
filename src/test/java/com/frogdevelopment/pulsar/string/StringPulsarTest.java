package com.frogdevelopment.pulsar.string;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.inject.Inject;

import org.apache.pulsar.client.api.PulsarClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

@Tag("integrationTest")
@MicronautTest(startApplication = false)
class StringPulsarTest {

    @Inject
    private StringProducer producer;
    @Inject
    private StringReader reader;

    @BeforeEach
    void beforeEach() throws Exception {
        reader.clear();
    }

    @Test
    void should_works() throws PulsarClientException {
        // given
        final var message = "Hello World";

        // when
        producer.sendBlocking(message);

        // then
        var publishedPulsarEvent = reader.poll();
        assertThat(publishedPulsarEvent).isEqualTo(message);
    }
}
