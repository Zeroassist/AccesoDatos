package org.example.Clases.Bases_Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionFicha{
    /**
     *
     * Strings de la url, user y password de la base de datos en postgres
     *
     * Esta es una clase de la que las clases que necesiten conexion con la base de datos
     *
     */
    private String url="jdbc:postgresql://localhost:1432/aprendizaje";
    private String user="postgres";
    private String password="alumno";

    /**
     * Metodo que devuelve la conexion con la base de datos para no tener que crearla en cada metodo
     *
     * @return con La conexion con la base de datos
     *
     * @throws SQLException si sucede algun error en establecer la conexion con la base de datos
     */
    public Connection getConnection() throws SQLException {
        Connection con= DriverManager.getConnection(url,user,password);
        return con;
    }
}
