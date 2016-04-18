package org.example.rest.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.example.rest.common.HasId;

public abstract class GenericDao<T extends HasId> {
    private final Random random = new Random();
    protected final Map<Long, T> repo = new /*ConcurrentHashMap*/ LinkedHashMap<>();

    public boolean exist(final Long id) {
        return repo.containsKey(id);
    }

    public T save(final T entity) {
        Long id = entity.getId();
        if (id == null) {
            id = generateId();
            entity.setId(id);
        }
        repo.put(id, entity);
        return entity;
    }

    public boolean delete(final Long id) {
        return repo.remove(id) != null;
    }

    public boolean delete(final T entity) {
        return delete(entity.getId());
    }

    public List<T> getAll() {
        return new ArrayList<>(repo.values());
    }

    public List<T> getAll(final Integer offset, final Integer limit) {
        if (limit != null) {
            final int fromIndex = offset == null ? 0 : offset;
            try {
                return new ArrayList<>(repo.values()).subList(fromIndex, fromIndex + limit);
            } catch (final IndexOutOfBoundsException e) {
                return new ArrayList<>();
            }
        }
        return getAll();
    }

    public T findById(final Long id) {
        return repo.get(id);
    }

    private Long generateId() {
        long id;
        while (exist(id = random.nextInt(Integer.MAX_VALUE))) {}
        return id;
    }
}
