package io.nmikle.moose.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "app_todo")
public class TodoEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @SequenceGenerator(name = "todoSeq", sequenceName = "app_todo_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "todoSeq")
    private Long id;

    @Column(name = "todo_content", nullable = false, length = 200)
    private String content;

    public TodoEntity() {
        this(null, null);
    }

    public TodoEntity(String content) {
        this(null, content);
    }

    public TodoEntity(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoEntity that = (TodoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }

    @Override
    public String toString() {
        return "TodoEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
