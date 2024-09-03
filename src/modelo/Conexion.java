/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author ELIAS VILLATORO0
 */
public class Conexion {
    //url conexion = jdbc,mysql,localhost,3306,db_empresa
    public final String puerto = "3306";
    public final String bd = "db_empresa";
    public final String urlConexion = String.format("jdbc:mysql://localhost:%s/%s?serverTimezone=UTC",puerto,bd);
    public final String usuario = "root";
    public final String contra = "Elias174+-";
    public final String jdbc = "com.mysql.cj.jdbc.Driver";
    public Connection conexionDB;
    
    public void abrir_conexion(){
        try{
            Class.forName(jdbc);
            conexionDB = DriverManager.getConnection(urlConexion,usuario,contra);
            System.out.println("Conexion Exitosa ;)");
        }catch(ClassNotFoundException | SQLException ex){
        System.out.println("Algo salio mal :( "+ex.getMessage());
        }
    }
    public void cerrar_conexion(){
        try{
            conexionDB.close();
       }catch(SQLException ex){
       System.out.println("Algo salio mal :("+ex.getMessage());
       }
    }
}
