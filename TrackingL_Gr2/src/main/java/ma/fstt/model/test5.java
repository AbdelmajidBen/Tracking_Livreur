package ma.fstt.model;

import java.sql.SQLException;
import java.sql.*;
import java.util.List;

public class test5 {

    public static void main(String[] args) {

// trait bloc try catch
        try {


            ProduitDAO2 produitDAO2 = new ProduitDAO2();
            //  Livreur liv = new Livreur(0l , "liv3" , "200000000");

            //livreurDAO.save(liv);



            //Livreur liv2 = new Livreur(0l , "liv2" , "100000000");


            // livreurDAO.save(liv2);


            List<Produit2> livlist =  produitDAO2.getAll();

            for (Produit2 liv :livlist) {

                System.out.println(liv.toString());

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
