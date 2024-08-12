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
import ma.fstt.model.Produit2;
import ma.fstt.model.ProduitDAO2;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
public class controllv5 implements Initializable {

    @FXML
    private TextField prixField ;
    @FXML
    private Button btn1;

    public long idproduitSelectionne;
    @FXML
    private TextField descriptionField ;
    @FXML
    private TableView<Produit2> mytable ;
    @FXML
    private TableColumn<Produit2,Long> col_id ;
    @FXML
    private TableColumn <Produit2 , String> col_prix;
    @FXML
    private TableColumn <Produit2 ,String> col_description ;
    @FXML
    private Button save;
    @FXML
    private Button modify;

    public void change() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Dashbord.fxml"));
        Stage window =(Stage) btn1.getScene().getWindow();
        window.setScene(new Scene(root,800,500));

    }
    @FXML
    protected void onSaveButtonClick() {

        // access a la bdd
        try {
            ProduitDAO2 produitDAO = new ProduitDAO2();

            Produit2 liv = new Produit2(0l , prixField.getText() , descriptionField.getText());

            produitDAO.save(liv);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Produit2,Long>("id_produit"));
        col_description.setCellValueFactory(new PropertyValueFactory<Produit2,String>("description"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produit2,String>("prix"));

        mytable.setItems(this.getDataProduits());
        addButtonToTable("Modifier",0);
        addButtonToTable("Supprimer",1);

    }

    public static ObservableList<Produit2> getDataProduits(){

        ProduitDAO2 produitDAO2 = null;

        ObservableList<Produit2> listfx = FXCollections.observableArrayList();

        try {
            produitDAO2 = new ProduitDAO2();
            listfx.addAll(produitDAO2.getAll());


        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listfx ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();

    }
    private void addButtonToTable(String ButtonName,int btnId) {
        if(mytable.getColumns().size()==5){
            return;
        }
        TableColumn<Produit2, Void> colBtn = new TableColumn(ButtonName);
        Button btn1 = new Button();
        Callback<TableColumn<Produit2, Void>, TableCell<Produit2, Void>> cellFactory = new Callback<TableColumn<Produit2, Void>, TableCell<Produit2, Void>>() {
            @Override
            public TableCell<Produit2, Void> call(final TableColumn<Produit2, Void> param) {
                final TableCell<Produit2, Void> cell = new TableCell<Produit2, Void>() {

                    private final Button btn = new Button(ButtonName);

                    {
                        btn.setId(""+btnId);
                        btn.setOnAction((ActionEvent event)  -> {
                            ProduitDAO2 produitDAO = null;
                            try {
                                produitDAO = new ProduitDAO2();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            Produit2 produit = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + produit);
                            if (Objects.equals(btn.getId(), "1")){

                                try {
                                    produitDAO.delete(produit);
                                    UpdateTable();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }else if(Objects.equals(btn.getId(), "0")){
                                save.setDisable(true);
                                modify.setDisable(false);
                                idproduitSelectionne=produit.getId_produit();
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        mytable.getColumns().add(colBtn);

    }

    public void onModifyButtonClick(ActionEvent actionEvent) {
        try {
            ProduitDAO2 produitDAO = new ProduitDAO2();
            Produit2 liv = new Produit2(idproduitSelectionne , prixField.getText() , descriptionField.getText());
            produitDAO.modify(liv);
            save.setDisable(false);
            modify.setDisable(true);
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}