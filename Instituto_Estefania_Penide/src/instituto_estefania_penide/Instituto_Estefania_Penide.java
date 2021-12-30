/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instituto_estefania_penide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a20estefaniapc
 */
public class Instituto_Estefania_Penide {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        //Declaracion de conectores
        /*Connection conexion = null;*/
        Statement sentencia = null;
        ResultSet rstAux = null;
        
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        int cont = 0, op = 0;
        
        //Declaracion de driver y url
        
        //Para usbwebserver
        //String driver= "com.mysql.jdbc.Driver";   
        //String url= "jdbc:mysql://localhost:3307/BDInstituto?user=root&password=usbw";
        
        //Para my server de mysql en casa
        String driver="com.mysql.cj.jdbc.Driver";
        String url= "jdbc:mysql://localhost:3306/BDInstituto?user=root&password=1234";

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

    }
    
}
