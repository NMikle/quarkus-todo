package io.nmikle.moose.todo.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class ErrorDto {
    private final String message;
    private final LocalDateTime timestamp;

    public ErrorDto(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDto that = (ErrorDto) o;
        return Objects.equals(message, that.message) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, timestamp);
    }

    @Override
    public String toString() {
        return "ExceptionDto{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}
