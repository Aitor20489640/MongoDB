package org.aitor.repositories;

import org.bson.types.ObjectId;

import java.util.List;

public interface Repository<T> {

    void save(T t);
    T findOneById(ObjectId id);
    List<T> findAll();
    void updateById(ObjectId id);
    void deleteById(ObjectId id);
}
