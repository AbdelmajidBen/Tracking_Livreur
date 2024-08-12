package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import ma.fstt.model.CommandeDAO;
import ma.fstt.model.Commande;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controlldash {

    @FXML
    private Button btnliv;

    @FXML
    private Button btncom;

    @FXML
    private Button btnprod;


    public void change() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Livreurscene.fxml"));
        Stage window =(Stage) btnliv.getScene().getWindow();
        window.setScene(new Scene(root,800,500));

    }

    public void change1() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Commande.fxml"));
        Stage window =(Stage) btncom.getScene().getWindow();
        window.setScene(new Scene(root,800,500));

    }

    public void change2() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        Stage window =(Stage) btnprod.getScene().getWindow();
        window.setScene(new Scene(root,800,500));

    }

}
