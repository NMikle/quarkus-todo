package io.nmikle.moose.todo.exception;

import io.nmikle.moose.todo.model.ErrorDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class RestExceptionMapper implements ExceptionMapper<RestException> {

    @Override
    public Response toResponse(RestException e) {
        return Response.status(e.getStatus())
                .entity(new ErrorDto(e.getMessage(), LocalDateTime.now()))
                .build();
    }

//    @ServerExceptionMapper
//    public Response mapValidationException(ValidationException e) {
//
//    }

}
