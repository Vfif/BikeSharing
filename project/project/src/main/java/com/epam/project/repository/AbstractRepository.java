package com.epam.project.repository;

import com.epam.project.entity.Entity;
import com.epam.project.exception.RepositoryException;
import com.epam.project.repository.specification.Specification;

import java.util.List;

public interface AbstractRepository<T extends Entity> {
    void save(T entity) throws RepositoryException;
    void remove(T entity);
    void update(T entity);
    List<T> query(Specification specification) throws RepositoryException;
}



