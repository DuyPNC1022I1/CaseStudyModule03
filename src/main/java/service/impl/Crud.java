package service.impl;

import java.util.List;

public interface Crud<E> {
    List<E> display();
    void creat(E e);
    void update(E e);
    void delete(int id);
}
