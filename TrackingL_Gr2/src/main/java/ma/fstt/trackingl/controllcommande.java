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

public class controllcommande implements Initializable {





    @FXML
    private TextField Etat ;
    @FXML
    private TextField Nom_Client ;




    public long idproduitSelectionne;
    @FXML
    private Button save;

    @FXML
    private Button btn1;
    @FXML
    private Button modify;



    @FXML
    private TableView<Commande> mytable ;


    @FXML
    private TableColumn<Commande ,Long> col_De ;


    @FXML
    private TableColumn <Commande ,String> col_client ;
    @FXML
    private TableColumn <Commande ,String> col_Etat ;

    public void change() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Dashbord.fxml"));
        Stage window =(Stage) btn1.getScene().getWindow();
        window.setScene(new Scene(root,800,500));

    }
    @FXML
    protected void onSaveButtonClick() {

        // accees a la bdd

        try {
            CommandeDAO commandeDAO = new CommandeDAO();

            Commande liv = new Commande(0l,Etat.getText(),Nom_Client.getText());

            commandeDAO.save(liv);


            UpdateTable();




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void UpdateTable(){
        col_De.setCellValueFactory(new PropertyValueFactory<Commande,Long>("Date_De"));
        col_client.setCellValueFactory(new PropertyValueFactory<Commande,String>("Nom_Client"));
        col_Etat.setCellValueFactory(new PropertyValueFactory<Commande,String>("Etat"));



        mytable.setItems(this.getDataLivreurs());
        addButtonToTable("Modifier",0);
        addButtonToTable("Supprimer",1);
    }

    public static ObservableList<Commande> getDataLivreurs(){

       CommandeDAO commandeDAO = null;

        ObservableList<Commande> listfx = FXCollections.observableArrayList();

        try {
            commandeDAO = new CommandeDAO();
            listfx.addAll(commandeDAO.getAll());


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
        TableColumn<Commande, Void> colBtn = new TableColumn(ButtonName);
        Button btn1 = new Button();
        Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>> cellFactory = new Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>>() {
            @Override
            public TableCell<Commande, Void> call(final TableColumn<Commande, Void> param) {
                final TableCell<Commande, Void> cell = new TableCell<Commande, Void>() {

                    private final Button btn = new Button(ButtonName);

                    {
                        btn.setId(""+btnId);
                        btn.setOnAction((ActionEvent event)  -> {
                            CommandeDAO commandeDAO = null;
                            try {
                                commandeDAO = new CommandeDAO();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            Commande commande = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + commande);
                            if (Objects.equals(btn.getId(), "1")){

                                try {
                                    commandeDAO.delete(commande);
                                    UpdateTable();
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            }else if(Objects.equals(btn.getId(), "0")){
                                save.setDisable(true);
                                modify.setDisable(false);
                                idproduitSelectionne=commande.getDate_De();
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
            CommandeDAO livreurDAO = new CommandeDAO();
            Commande liv = new Commande(idproduitSelectionne ,Etat.getText() , Nom_Client.getText());
            livreurDAO.modify(liv);
            save.setDisable(false);
            modify.setDisable(true);
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}