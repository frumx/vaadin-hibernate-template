package com.ksmk.back.service;

import java.util.List;

public interface Dao<T> {

    void create(T entity);
    void delete(T entity);
    List<T> getAll();
    <T> T getById(Long id);
    void update(T entity);
    List<T> selectPage(int start, int count);
    Long countAll();
    Integer deleteAll();

}
