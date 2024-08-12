package ma.fstt.model;

import java.sql.SQLException;
import java.sql.*;
import java.util.List;

public class Test {

    public static void main(String[] args) {

// trait bloc try catch
        try {


            LivreurDAO livreurDAO = new LivreurDAO();
            Livreur liv = new Livreur(0l , "liv3" , "200000000");

            livreurDAO.delete(liv);

            //Livreur liv2 = new Livreur(0l , "liv2" , "100000000");


           // livreurDAO.save(liv2);


          List<Livreur> livlist =  livreurDAO.getAll();

            for (Livreur liv1 :livlist) {

                System.out.println(liv1.toString());

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
