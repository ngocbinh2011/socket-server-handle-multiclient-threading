package model;

import datasource.sourcedao.IMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
    private String id;
    private String name;
    private double price;

    public Product() {
    }

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static class ProductMapper implements IMapper<Product> {

        @Override
        public Product mapping(ResultSet resultSet) {
            Product product = new Product();
            try {
                product.setId(resultSet.getString("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return product;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product[" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ']';
    }
}
