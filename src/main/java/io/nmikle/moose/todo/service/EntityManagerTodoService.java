package io.nmikle.moose.todo.service;

import io.nmikle.moose.todo.model.TodoDto;
import io.nmikle.moose.todo.model.TodoEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class EntityManagerTodoService implements TodoService {

    private final EntityManager entityManager;

    @Inject
    public EntityManagerTodoService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public TodoDto save(TodoDto model) {
        final TodoEntity toBeSaved = new TodoEntity(model.getContent());
        entityManager.persist(toBeSaved);
        return new TodoDto(toBeSaved.getId(), toBeSaved.getContent(), null);
    }

    @Override
    public Collection<TodoDto> findAll() {
        CriteriaQuery<TodoEntity> cq = entityManager.getCriteriaBuilder()
                .createQuery(TodoEntity.class);
        return entityManager.createQuery(cq.select(cq.from(TodoEntity.class)))
                .getResultList()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<TodoEntity> cq = cb.createQuery(TodoEntity.class);
//        Root<TodoEntity> rootEntry = cq.from(TodoEntity.class);
//        CriteriaQuery<TodoEntity> all = cq.select(rootEntry);
//        TypedQuery<TodoEntity> allQuery = entityManager.createQuery(all);
//        return allQuery.getResultList().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<TodoDto> findById(Long id) {
        try {
            return Optional.ofNullable(entityManager.find(TodoEntity.class, id)).map(this::toDto);
        } catch (PersistenceException e) {
            return Optional.empty();
        }
    }

    private TodoDto toDto(TodoEntity entity) {
        return new TodoDto(entity.getId(), entity.getContent(), null);
    }
}
