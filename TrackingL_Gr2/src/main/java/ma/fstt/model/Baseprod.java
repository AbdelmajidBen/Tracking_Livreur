package ma.fstt.model;

import java.sql.*;
import java.util.List;

public abstract  class Baseprod <T>{

    protected Connection connection ;
    protected Statement statement ;

    protected PreparedStatement preparedStatement;

    protected ResultSet resultSet ;

    // connexion avec bdd

    private String url = "jdbc:mysql://127.0.0.1:3306/glovo";
    private String login = "root";
    private String password = "";



    Baseprod() throws SQLException {
        this.connection = DriverManager.getConnection(url , login ,password );
    }


    public abstract void save( T object ) throws SQLException;

    public abstract void update( T object ) throws SQLException ;

    public abstract void delete( T object ) throws SQLException ;


    public abstract List<T> getAll(  ) throws SQLException ;


    public abstract T getOne( Long id  ) throws SQLException  ;





}
