package com.frogdevelopment;

import static io.micronaut.http.HttpStatus.OK;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

@MicronautTest
class MyControllerTest {

    @Inject
    @Client(MyController.BASE_ENDPOINT)
    private HttpClient client;

    @Test
    void should_use_default_pageable_when_not_set() {
        // Given

        // When
        var response = client.toBlocking().exchange(HttpRequest.GET("/search"), Page.class);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(OK.getCode());
        assertThat(response.getBody()).hasValueSatisfying(page -> {
            assertThat(page.getContent()).hasSize(100);
            assertThat(page.getPageable().getSize()).isEqualTo(100);
            assertThat(page.getPageable().getNumber()).isZero();
            assertThat(page.getTotalSize()).isEqualTo(300);
        });
    }

    @Test
    void should_getPage_with_pageable_values() {
        // Given
        final var request = HttpRequest.GET("/search?page=3&size=33");

        // When
        var response = client.toBlocking().exchange(request, Page.class);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(OK.getCode());
        assertThat(response.getBody()).hasValueSatisfying(page -> {
            assertThat(page.getContent()).hasSize(33);
            assertThat(page.getPageable().getSize()).isEqualTo(33);
            assertThat(page.getPageable().getNumber()).isEqualTo(3);
            assertThat(page.getTotalSize()).isEqualTo(99);
        });
    }

}
