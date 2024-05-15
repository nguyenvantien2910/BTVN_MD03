package com.ra.demo.dao;

import java.util.List;

public interface IDAO<E,T,S> {
    public List<E> getAll();
    public E findById(T id);
    public boolean addNew(E e);
    public boolean update(E e);
    public boolean delete(T id);
    public List<E> getByName(S name);
}
