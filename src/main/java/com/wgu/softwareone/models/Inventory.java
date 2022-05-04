package com.wgu.softwareone.models;

import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public void addPart(Part newPart) {
        this.allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        this.allProducts.add(newProduct);
    }

    public Part lookupPart(int partId) {
        return this.allParts.get(partId);
    }

    public Product lookupProduct(int productId) {
        return this.allProducts.get(productId);
    }

    public ObservableList<Part> lookupPart(String partName) {
        return this.allParts.filtered(part -> part.getName().contentEquals(partName));
    }

    public ObservableList<Product> lookupProduct(String productName) {
        return this.allProducts.filtered(product -> product.getName().contentEquals(productName));
    }

    public void updatePart(int index, Part selectedPart) {
        this.allParts.remove(index);
        this.allParts.add(index, selectedPart);
    }

    public void updateProduct(int index, Product newProduct) {
        this.allProducts.remove(index);
        this.allProducts.add(index, newProduct);
    }

    public boolean deletePart(Part selectedPart) {
        return this.allParts.remove(selectedPart);
    }

    public boolean deleteProduct(Product selectedProduct) {
        return this.allProducts.remove(selectedProduct);
    }

    public ObservableList<Part> getAllParts() {
        return this.allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return this.allProducts;
    }

}
