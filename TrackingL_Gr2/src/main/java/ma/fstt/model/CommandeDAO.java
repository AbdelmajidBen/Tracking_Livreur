package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.parseFloat;

public class CommandeDAO extends BaseDAO<Commande>{
    public CommandeDAO() throws SQLException {

        super();
    }

    @Override
    public void save(Commande object) throws SQLException {
        String request = "INSERT INTO `commande1` (`Date_De`, `Etat`, `Nom_Client`) VALUES ('1', ?, ?)";
        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(2 , object.getEtat());
        this.preparedStatement.setString(2 , object.getNom_Client());
        this.preparedStatement.execute();
    }



    @Override
    public  void delete(Commande object) throws SQLException {
        String request = "DELETE FROM commande1 WHERE Date_De = ?";
        System.out.println(object);
        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setLong(1 , object.getDate_De());
        this.preparedStatement.execute();
    }

    @Override
    public List<Commande> getAll()  throws SQLException {

        List<Commande> mylist = new ArrayList<Commande>();
        String request = "select * from commande1";
        this.statement = this.connection.createStatement();
        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){
// mapping table objet
            mylist.add(new Commande(this.resultSet.getLong(1) , this.resultSet.getString(2),this.resultSet.getString(3)));
        }
        return mylist;
    }

    @Override
    public Commande getOne(Long id) throws SQLException {
        return null;
    }

    public void modify(Commande object) throws SQLException {
        String request = "update commande1 set Nom_Client = ?, Etat=? where Date_De=? " ;
        this.preparedStatement=this.connection.prepareStatement(request);
        this.preparedStatement.setString(1,object.getEtat());
        this.preparedStatement.setString(2,object.getNom_Client());
        this.preparedStatement.setLong(3,object.getDate_De());


        this.preparedStatement.execute();
    }
}

