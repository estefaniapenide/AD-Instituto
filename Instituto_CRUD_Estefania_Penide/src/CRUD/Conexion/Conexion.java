/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Conexion;

import static instituto_crud_estefania_penide.ConectarConBD.password;
import static instituto_crud_estefania_penide.ConectarConBD.puerto;
import static instituto_crud_estefania_penide.ConectarConBD.sentencia;
import static instituto_crud_estefania_penide.ConectarConBD.usuario;
import instituto_crud_estefania_penide.CrearTablas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Estefania
 */
public class Conexion {
    
    public static String puerto=null;
    public static String nombreBD=null;
    public static String usuario=null;
    public static String password=null;

    public static Connection conexionBD() {

        Connection conexion = null;

        //Declaracion de driver y url
        //Para usbwebserver
        //String driver= "com.mysql.cj.jdbc.Driver";   
        //String url= "jdbc:mysql://localhost:3307/Alumnos?user=root&password=usbw";
        //Para mi server de mysql en casa
        String driver = "com.mysql.cj.jdbc.Driver";
        //String url = "jdbc:mysql://localhost:" + puerto + "/" + nombreBD + "?user=" + usuario + "&password=" + password;

        String url = "jdbc:mysql://localhost:" + puerto + "/" + nombreBD + "?useSSL=false&serverTimezone=UTC&user=" + usuario + "&password=" + password;
        //String url = "jdbc:mysql://localhost:" + puerto + "/?useSSL=false&serverTimezone=UTC&user=" + usuario + "&password=" + password;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encontro el driver" + driver);
            System.exit(1);
        }
        try {
            conexion = DriverManager.getConnection(url);
            System.out.println("\nCONEXIÓN CON MYSQL LISTA.");
        } catch (SQLException e) {
            System.out.println(e + "No hay ningún Driver registrado que reconozca la URL especificada");
            System.exit(2);
        } catch (Exception e) {
            System.out.println("\n\t Se ha producido algún otro error.");
            System.exit(3);
        }

        return conexion;

    }

}
