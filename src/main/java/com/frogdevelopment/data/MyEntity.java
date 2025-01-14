package com.frogdevelopment.data;

import static io.micronaut.data.annotation.GeneratedValue.Type.IDENTITY;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@MappedEntity(value = "entities")
public record MyEntity(
        @Id @GeneratedValue(IDENTITY) Long id,
        String field1,
        Integer field2
) {
}
