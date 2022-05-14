package com.wgu.softwareone.controllers;

import com.wgu.softwareone.InventoryApplication;
import com.wgu.softwareone.models.Part;
import com.wgu.softwareone.models.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static ObservableList<Part> partData;
    private static ObservableList<Product> productData;

    @FXML
    private TableView<Part> partTable;
    @FXML
    private TableColumn<Part, Integer> partId;
    @FXML
    private TableColumn<Part, String> partName;
    @FXML
    private TableColumn<Part, Integer> partStock;
    @FXML
    private TableColumn<Part, Integer> partPrice;

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> productId;
    @FXML
    private TableColumn<Product, String> productName;
    @FXML
    private TableColumn<Product, Integer> productStock;
    @FXML
    private TableColumn<Product, Integer> productPrice;

    @FXML
    private TextField partFilter;
    @FXML
    private TextField productFilter;
    @FXML
    private Button addPartButton;
    @FXML
    private Button addProductButton;
    @FXML
    private Button modifyPartButton;
    @FXML
    private Button modifyProductButton;
    @FXML
    private Button deletePartButton;
    @FXML
    private Button deleteProductButton;
    @FXML
    private Button exitApplicationButton;

    @FXML
    private void exitApplication(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void filterPartTable(KeyEvent event) {
        ObservableList<Part> filteredParts = partData.filtered(part -> {
            try {
                if (part.getName().toLowerCase().contains(partFilter.getText().toLowerCase())) {
                    return true;
                } else if (part.getId() == Integer.parseInt(partFilter.getText())) {
                    return true;
                } else {
                    return false;
                }
            } catch (NumberFormatException ex) {
                return false;
            }
        });

        if (filteredParts.size() == 0) {
            partTable.setItems(partData);
            return;
        }

        partTable.setItems(filteredParts);
    }

    @FXML
    private void filterProductTable(KeyEvent event) {
        ObservableList<Product> filteredProducts = productData.filtered(product -> {
            try {
                if (product.getName().toLowerCase().contains(productFilter.getText().toLowerCase())) {
                    return true;
                } else if (product.getId() == Integer.parseInt(productFilter.getText())) {
                    return true;
                } else {
                    return false;
                }
            } catch (NumberFormatException ex) {
                return false;
            }
        });

        if (filteredProducts.size() == 0) {
            productTable.setItems(productData);
            return;
        }

        productTable.setItems(filteredProducts);
    }

    @FXML
    private void deletePart(ActionEvent event) {
        ObservableList<Part> selectedItems = partTable.getSelectionModel().getSelectedItems();
        for (int i = 0; i < selectedItems.size(); i++) {
            partData.remove(selectedItems.get(i));
        }
    }

    @FXML
    private void deleteProduct(ActionEvent event) {
        ObservableList<Product> selectedItems = productTable.getSelectionModel().getSelectedItems();
        for (int i = 0; i < selectedItems.size(); i++) {
            productData.remove(selectedItems.get(i));
        }
    }

    @FXML
    private void addPartView(ActionEvent event) throws IOException {
        // disable buttons to prevent opening another scene;
        addPartButton.setDisable(true);
        modifyPartButton.setDisable(true);
        deletePartButton.setDisable(true);

        Stage newWindow = new Stage();
        newWindow.setTitle("Add Part");
        FXMLLoader loader = new FXMLLoader(InventoryApplication.class.getResource("add-part-view.fxml"));
        newWindow.setScene(new Scene(loader.load()));
        newWindow.showAndWait();

        addPartButton.setDisable(false);
        modifyPartButton.setDisable(false);
        deletePartButton.setDisable(false);
    }

    @FXML
    private void addProductView(ActionEvent event) throws IOException {
        // disable buttons to prevent opening another scene;
        addProductButton.setDisable(true);
        modifyProductButton.setDisable(true);
        deleteProductButton.setDisable(true);

        Stage newWindow = new Stage();
        newWindow.setTitle("Add Part");
        FXMLLoader loader = new FXMLLoader(InventoryApplication.class.getResource("add-product-view.fxml"));
        newWindow.setScene(new Scene(loader.load()));
        newWindow.showAndWait();

        addProductButton.setDisable(false);
        modifyProductButton.setDisable(false);
        deleteProductButton.setDisable(false);
    }

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        // setup Part table initial data
        partData = FXCollections.observableArrayList(
                new Part(0, "Carbon Frame Cylinder", 5.0, 3, 0, 25),
                new Part(1, "Aluminium Sphere Barrings", 15.0, 16, 5, 50),
                new Part(2, "Steel Support Bar", 54.0, 5, 1, 10)
        );
        partTable.setItems(partData);

        partId.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partStock.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<Part, Integer>("price"));
        partTable.getColumns().setAll(partId, partName, partStock, partPrice);

        // setup Products table initial data
        productData = FXCollections.observableArrayList(
                new Product(0, "Small Wheel", 5.0, 3, 0, 25),
                new Product(1, "Forged Knife", 15.0, 16, 5, 50),
                new Product(2, "Bike Frame", 54.0, 5, 1, 10)
        );
        productTable.setItems(productData);

        productId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
        productTable.getColumns().setAll(productId, productName, productStock, productPrice);
    }

    public static void AddPart(Part part) {
        partData.add(part);
    }

    public static void AddProduct(Product product) {
        productData.add(product);
    }

    public static int nextPartId() {
        int nextInt = partData.stream().max(Comparator.comparing(x -> x.getId())).get().getId();
        return nextInt + 1;
    }

    public static int nextProductId() {
        int nextInt = productData.stream().max(Comparator.comparing(x -> x.getId())).get().getId();
        return nextInt + 1;
    }
}
