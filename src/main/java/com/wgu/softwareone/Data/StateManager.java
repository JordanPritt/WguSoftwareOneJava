package com.wgu.softwareone.Data;

import com.wgu.softwareone.models.Part;
import com.wgu.softwareone.models.Product;

import java.util.Comparator;

public class StateManager {
    public static void addPart(Part part) {
        AppState.partData.add(part);
    }

    public static void removePart(Part part) {
        AppState.partData.remove(part);
    }

    public static void addProduct(Product product) {
        AppState.productData.add(product);
    }

    public static void removeProduct(Product product) {
        AppState.productData.remove(product);
    }

    public static void modifyPart(Part oldPart, Part newPart) {
        try {
            int oldPartIndex = AppState.partData.indexOf(oldPart);
            AppState.partData.remove(oldPartIndex);
            AppState.partData.add(newPart);
            AppState.partData = AppState.partData.sorted();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void modifyProduct(Product oldProduct, Product newProduct) {
        AppState.productData.remove(oldProduct);
        AppState.productData.add(newProduct);
        AppState.productData = AppState.productData.sorted();
    }

    public static int nextPartId() {
        if (AppState.partData.size() <= 0) return 0;

        int nextInt = AppState.partData.stream().max(Comparator.comparing(x -> x.getId())).get().getId();
        return nextInt + 1;
    }

    public static int nextProductId() {
        int nextInt = AppState.productData.stream().max(Comparator.comparing(x -> x.getId())).get().getId();
        return nextInt + 1;
    }
}
