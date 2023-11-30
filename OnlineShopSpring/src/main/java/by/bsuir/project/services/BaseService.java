package by.bsuir.project.services;

import by.bsuir.project.domain.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    T create(T entity);

    List<T> read();

    T update(T entity);

    void delete(Integer id);
}
