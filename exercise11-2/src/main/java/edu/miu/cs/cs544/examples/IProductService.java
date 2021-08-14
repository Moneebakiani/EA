package edu.miu.cs.cs544.examples;

public interface IProductService {
    public Product getProduct(int productNumber);
    public int getNumberInStock(int productNumber);
}
