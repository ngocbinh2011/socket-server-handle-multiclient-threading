package datasource.dao.impl;

import datasource.dao.IProductDAO;
import datasource.sourcedao.IMapper;
import datasource.sourcedao.impl.ServiceDAO;
import model.Product;

import java.util.List;

public class ProductDAO extends ServiceDAO<Product> implements IProductDAO {

    private IMapper<Product> mapper = new Product.ProductMapper();

    @Override
    public Product findById(String id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        List<Product> list = query(sql, mapper, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Product> findByName(String name) {
        String sql = "SELECT * FROM products WHERE name = ?";
        return query(sql, mapper, name);
    }

    @Override
    public Product save(Product product) {
        synchronized (Product.class) {
            String sql = "INSERT INTO products(id, name, price) VALUES(?,?,?)";
            insert(sql, product.getId(), product.getName(), product.getPrice());
            return product;
        }
    }

    @Override
    public void delete(String id) {
        synchronized (Product.class) {
            String sql = "DELETE FROM products WHERE id = ?";
            delete(sql, id);
        }
    }

    @Override
    public Product update(Product product) {
        synchronized (Product.class) {
            String sql = "UPDATE products SET price = ? WHERE id = ?";
            update(sql, product.getPrice(), product.getId());
            return findById(product.getId());
        }
    }

    @Override
    public boolean isExists(String id) {
        return findById(id) != null;
    }

}
