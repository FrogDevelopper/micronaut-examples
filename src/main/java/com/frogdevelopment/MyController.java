package com.frogdevelopment;

import static io.micronaut.http.HttpResponse.ok;
import static io.micronaut.http.HttpStatus.OK;
import static io.micronaut.scheduling.TaskExecutors.BLOCKING;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Status;
import io.micronaut.scheduling.annotation.ExecuteOn;

@Controller(MyController.BASE_ENDPOINT)
@ExecuteOn(BLOCKING)
public class MyController {

    public static final String BASE_ENDPOINT = "/items";
    private static final Logger log = LoggerFactory.getLogger(MyController.class);

    @Status(OK)
    @Get("/search")
    public HttpResponse<Page<MyItem>> searchItems(Pageable pageable) {
        final var items = IntStream.range(0, pageable.getSize())
                .mapToObj(i -> new MyItem("field_" + i, i))
                .collect(Collectors.toCollection(ArrayList::new));
        final var page = Page.of(items, pageable, pageable.getSize()* 3L);
        log.info("Searched items: {}", page);
        return ok(page);
    }

}
