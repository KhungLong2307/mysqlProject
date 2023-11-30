package business;

import entity.Product;

import java.util.List;

public interface IProduct {
    List<Product> getProduct(int offSet);
    int getProductLength();
    boolean productCheckName(String name);
    boolean productCheckId(String id);
    boolean productCheckBatch(int batch);
    boolean createProduct(Product product);
    Product findProduct(String id);
    boolean updateProduct(Product product);
    List<Product> getSearchProduct(int offSet,String name);
    int getSearchProductLength(String name);


}
