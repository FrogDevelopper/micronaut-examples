package com.frogdevelopment;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;

@Serdeable
public record MyItem(
        @Schema(description = "My 1st field", requiredMode = REQUIRED)
        String field1,
        @Schema(description = "An other field", requiredMode = NOT_REQUIRED)
        Integer field2) {
}
