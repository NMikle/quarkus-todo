package io.nmikle.moose.todo.exception;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

public class RestException extends RuntimeException {

    private final Response.Status status;

    public RestException(String message) {
        this(message, BAD_REQUEST);
    }

    public RestException(String message, Response.Status status) {
        super(message);
        this.status = status;
    }

    public Response.Status getStatus() {
        return status;
    }
}
