/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instituto_estefania_penide;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Estefania
 */
public class CreacionBD {
    
    public static Statement sentencia=null;
    public static ResultSet rstAux = null;
    
    public static void creacionBD(){
    
        //Declaracion de conectores
        /*Connection conexion = null;*/
        //Statement sentencia = null;
        //ResultSet rstAux = null;
       
        //Declaracion de driver y url
        
        //Para usbwebserver
        //String driver= "com.mysql.jdbc.Driver";   
        //String url= "jdbc:mysql://localhost:3307/Alumnos?user=root&password=usbw";
        
        //Para my server de mysql en casa
        String driver="com.mysql.cj.jdbc.Driver";
        String url= "jdbc:mysql://localhost:3306/Alumnos?user=root&password=1234";

        //No es necesario desde la ultima versión(pero me sirvió para encontrar el driver conncetor j de mysql)
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encontro el driver" + driver);
            System.exit(1);
        }
        try {
            Connection conexion = DriverManager.getConnection(url);
            sentencia = conexion.createStatement();
        } catch (SQLException e) {
            System.out.println("No hay ningún Driver registrado que reconozca la URL especificada");
            System.exit(2);
        } catch (Exception e) {
            System.out.println("\n\t Se ha producido algún otro error.");
            System.exit(3);
        }
        
        CrearTablas.crearTabla(sentencia);
        CrearTablas.restriccionesDNI(sentencia);
        CrearTablas.restriccionesCodigos(sentencia);
        
        System.out.println("BASE DE DATOS 'INSTITUTOBD' CREADA");
    
    }
    
}
