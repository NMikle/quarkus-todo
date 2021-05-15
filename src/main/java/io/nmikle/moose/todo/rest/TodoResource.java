package io.nmikle.moose.todo.rest;

import io.nmikle.moose.todo.exception.RestException;
import io.nmikle.moose.todo.model.TodoDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/v1/todo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoResource {

    private final Map<Long, TodoDto> todos;
    private final AtomicLong maxId;

    public TodoResource() {
        todos = new ConcurrentHashMap<>();
        maxId = new AtomicLong(0);
    }

    @GET
    public Collection<TodoDto> findAll() {
        return todos.values();
    }

    @GET
    @Path("{id}")
    public TodoDto findById(@PathParam("id") Long id) {
        return Optional.ofNullable(todos.get(id))
                .orElseThrow(() -> new RestException(String.format("entity not found by id: %s", id), NOT_FOUND));
    }

    @POST
    public TodoDto save(TodoDto body) {
        if (body.getId() != null) {
            throw new RestException("id field should be empty");
        }
        long id = maxId.incrementAndGet();
        TodoDto bodyWithId = body.withId(id);
        todos.put(id, bodyWithId);
        return bodyWithId;
    }

}
