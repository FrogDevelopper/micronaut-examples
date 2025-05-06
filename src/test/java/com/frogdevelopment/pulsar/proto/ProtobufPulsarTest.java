package com.frogdevelopment.pulsar.proto;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.inject.Inject;

import org.apache.pulsar.client.api.PulsarClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.google.protobuf.ByteString;
import com.google.protobuf.Timestamp;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

@Tag("integrationTest")
@MicronautTest(startApplication = false)
class ProtobufPulsarTest {

    @Inject
    private ProtobufProducer producer;
    @Inject
    private ProtobufReader reader;

    @BeforeEach
    void beforeEach() throws Exception {
        reader.clear();
    }

    @Test
    void should_works() throws PulsarClientException {
        // given
        final var message = TestMessageOuterClass.TestMessage.newBuilder().setBoolType(true)
                .setIntType(1)
                .setLongType(2L)
                .setFloatType(1.2f)
                .setDoubleType(2.3d)
                .setEnumType(TestMessageOuterClass.TestFlags.BAR)
                .setStringType("abc")
                .setBytesType(ByteString.copyFromUtf8("xyz"))
                .setTimestampType(Timestamp.newBuilder().setSeconds(11L))
                .build();

        // when
        producer.sendBlocking(message);

        // then
        var publishedPulsarEvent = reader.poll();
        assertThat(publishedPulsarEvent).isEqualTo(message);
    }
}
