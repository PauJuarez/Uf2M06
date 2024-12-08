package Tasque2;

import java.util.List;

public interface DAOInterface<T> {
    void add(T item);
    List<T> getAll();
}
