package io.nmikle.moose.todo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoDto {

    private final Long id;
    private final String content;
    private final UserDto user;

    @JsonCreator
    public TodoDto(Long id, String content, UserDto user) {
        this.id = id;
        this.content = content;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public UserDto getUser() {
        return user;
    }

    public TodoDto withId(Long id) {
        return new TodoDto(id, content, user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoDto todoDto = (TodoDto) o;
        return Objects.equals(id, todoDto.id)
                && Objects.equals(content, todoDto.content)
                && Objects.equals(user, todoDto.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, user);
    }

    @Override
    public String toString() {
        return "TodoDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", user=" + user +
                '}';
    }
}
