
package Service;

import java.util.List;

import DAO.DataException;

public interface BaseService <T, ID> {
    public List<T> FindAll() throws DataException;
    public T FindById(int Id) throws DataException;
    public T Add(T Value) throws DataException;
    public T Update(T Value) throws DataException;
    public T Delete(T Value) throws DataException;

} 