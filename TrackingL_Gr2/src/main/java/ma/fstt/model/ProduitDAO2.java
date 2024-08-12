package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.parseFloat;

public class ProduitDAO2 extends BaseDAO<Produit2>{
    public ProduitDAO2() throws SQLException {

        super();
    }

    @Override
    public void save(Produit2 object) throws SQLException {
        String request = "INSERT INTO `produit2` (`id_produit`, `prix`, `description`) values ('1',? , ?)";
        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getPrix());
        this.preparedStatement.setString(2 , object.getDescription());
        this.preparedStatement.execute();
    }



    @Override
    public  void delete(Produit2 object) throws SQLException {
        String request = "DELETE FROM produit2 WHERE id_produit = ?";
        System.out.println(object);
        // mapping objet table
        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setLong(1 , object.getId_produit());
        this.preparedStatement.execute();
    }

    @Override
    public List<Produit2> getAll()  throws SQLException {

        List<Produit2> mylist = new ArrayList<Produit2>();
        String request = "select * from produit2 ";
        this.statement = this.connection.createStatement();
        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){
// mapping table objet
            mylist.add(new Produit2(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2) , this.resultSet.getString(3)));
        }
        return mylist;
    }

    @Override
    public Produit2 getOne(Long id) throws SQLException {
        return null;
    }

    public void modify(Produit2 object) throws SQLException {
        String request = "update produit2 set prix = ?, description=? where id_produit =?" ;
        this.preparedStatement=this.connection.prepareStatement(request);
        this.preparedStatement.setFloat(1,parseFloat(object.getPrix()));
        this.preparedStatement.setString(2,object.getDescription());
        this.preparedStatement.setLong(3,object.getId_produit());

        this.preparedStatement.execute();
    }
}

