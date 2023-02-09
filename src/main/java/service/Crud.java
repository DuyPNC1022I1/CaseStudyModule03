package service;

import model.Brand;
import model.Product;

import java.util.List;

public interface Crud<E> {
        List<E> display();
        void create(E e);
        void update(E e);
        void delete(int id);
}
