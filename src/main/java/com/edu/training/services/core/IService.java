package com.edu.training.services.core;

import java.util.List;

public interface IService<T> {
   
    void save(T t);
    
    void update(T t);

    void delete(long theId);

    T findById(long theId);

    List<T> getAll();

}
