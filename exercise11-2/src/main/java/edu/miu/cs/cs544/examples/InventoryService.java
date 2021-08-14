package edu.miu.cs.cs544.examples;

public class InventoryService implements  IInventoryService{

    @Override
    public int getNumberInStock(int productNumber) {
        return productNumber-200;
    }
}
