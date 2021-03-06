package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.exceptions.QuantityException;
import model.ingredients.Ingredient;
import model.inventories.GroceryInventory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static GroceryInventory gl = new GroceryInventory();

    public void initialize(URL url, ResourceBundle rb) {
        for (Ingredient i: gl.getInventory()) {
            checklistView.getItems().add(i.getQuantity() + " " + i.getName());
        }
    }

    @FXML
    private ListView checklistView;

    @FXML
    private Button currentInventory;

    @FXML
    private Button removeButton;

    @FXML
    private Button purchase;

    @FXML
    private Button addIngredient;

    @FXML
    private Button checklist;


    @FXML
    private Pane body;


    @FXML
    void fffffa(ActionEvent event) {

    }

    @FXML
    void addingIngredient(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("addIngredient.fxml"));
        Scene scene = new Scene(root);
        // Get the stage info, cast as a stage
        Stage window = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        //window.show();

    }


    @FXML
    void purchaseItem(ActionEvent event) throws QuantityException {
        int toPurchase = checklistView.getSelectionModel().getSelectedIndex();
            gl.getInventory().get(toPurchase).purchase();
            checklistView.getItems().remove(toPurchase);
    }

    @FXML
    void removeItem(ActionEvent event) {
        int toRemove = checklistView.getSelectionModel().getSelectedIndex();
        checklistView.getItems().remove(toRemove);
        gl.removeIngredient(toRemove);

    }


    @FXML
    void goToInventory(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("inventory.fxml"));
        Scene scene = new Scene(root);
        // Get the stage info, cast as a stage
        Stage window = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);

    }

}
