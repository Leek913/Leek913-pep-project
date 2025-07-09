
package DAO;

import java.util.List;

public interface BaseRepository<T, ID> {
    List<T> FindAll() throws DataException;
    T FindById(int Id) throws DataException;
    T Add(T Value) throws DataException;
    boolean Update(T Value) throws DataException;
    boolean Delete(T Value) throws DataException;
}