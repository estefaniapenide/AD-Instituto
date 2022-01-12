/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instituto_estefania_penide;

import controldata.ControlData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Estefania
 */
public class ConectarConBD {

    public static Statement sentencia = null;
    public static String puerto = null;
    public static String nombreBD = null;
    public static String usuario = null;
    public static String password = null;

    public static String indicador = null;

    public static void indicadorConexion() {
        if (sentencia == null) {
            indicador = "NO HAY CONEXIÓN CON BDINSTITUTO";
        } else {
            indicador = "CONECTADO CON BDINSTITUTO";
        }
    }

    public static void conectarseInstitutoBD(Scanner input) {

        System.out.println("CONECTARSE A BDINSTITUTO\n");

        pedirDatosConexion(input);
        conexionBD();

    }

    private static void pedirDatosConexion(Scanner input) {

        //System.out.println("Para conectarse a la base de datos BDINSTITUTO introduzca los datos que se piden a continuación.");
        //System.out.println("NOTA: El nombre de la base de datos que se pide para poder establecer la conexión será cualquiera que\nya tenga usted creada en su servidor (puede ser BDINSTITUTO en caso de ya tenerla creada en su servidor).\n");
        System.out.println("PUERTO:");
        puerto = ControlData.leerString(input);
        //System.out.println("NOMBRE BD:");
        //nombreBD = ControlData.leerString(input);
        System.out.println("USUARIO:");
        usuario = ControlData.leerString(input);
        System.out.println("CONTRASEÑA:");
        password = ControlData.leerString(input);
    }

    private static void conexionBD() {

        //Declaracion de driver y url
        //Para usbwebserver
        //String driver= "com.mysql.cj.jdbc.Driver";   
        //String url= "jdbc:mysql://localhost:3307/Alumnos?user=root&password=usbw";
        //Para mi server de mysql en casa
        String driver = "com.mysql.cj.jdbc.Driver";
        //String url = "jdbc:mysql://localhost:" + puerto + "/" + nombreBD + "?user=" + usuario + "&password=" + password;

        //String url = "jdbc:mysql://localhost:" + puerto + "/" + nombreBD + "?useSSL=false&serverTimezone=UTC&user=" + usuario + "&password=" + password;
        String url = "jdbc:mysql://localhost:" + puerto + "/?useSSL=false&serverTimezone=UTC&user=" + usuario + "&password=" + password;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encontro el driver" + driver);
            System.exit(1);
        }
        try {
            Connection conexion = DriverManager.getConnection(url);
            System.out.println("\nCONEXIÓN CON MYSQL LISTA.");
            sentencia = conexion.createStatement();     
        } catch (SQLException e) {
            System.out.println(e + "No hay ningún Driver registrado que reconozca la URL especificada");
            System.exit(2);
        } catch (Exception e) {
            System.out.println("\n\t Se ha producido algún otro error.");
            System.exit(3);
        }

        CrearTablas.crearTabla(sentencia);
        //CrearTablas.restriccionesDNI(sentencia);
        //CrearTablas.restriccionesCodigos(sentencia);
        System.out.println("BASE DE DATOS 'BDINSTITUTO' LISTA.");

    }

}
