package datasource.dao;

import model.Product;

import java.util.List;

public interface IProductDAO {
    Product findById(String id);
    List<Product> findByName(String name);
    Product save(Product product);
    void delete(String id);
    Product update(Product product);
    boolean isExists(String id);
}
