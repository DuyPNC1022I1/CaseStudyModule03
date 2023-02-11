package dao.interfaceDAO;

import dao.IGeneralDAO;
import model.Order;

public interface IOrderDAO extends IGeneralDAO<Order> {
    default boolean add(Order order){
        return false;
    }
    boolean add(Order order, int idUser);
}
