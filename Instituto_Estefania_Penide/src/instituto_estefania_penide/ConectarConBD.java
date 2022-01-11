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
            indicador = "NO HAY CONEXIÓN CON BD";
        } else {
            indicador = "CONECTADO CON BD";
        }
    }

    public static void conectarseInstitutoBD(Scanner input) {
        byte op = 0;
        do {
            Menus.menuLogInReset();
            op = ControlData.leerByte(input);

            switch (op) {
                case 1:
                    System.out.println("CONECTARSE A BDINSTITUTO\n");
                    pedirDatosConexion(input);
                    conexionBD();
                    break;
                case 2:
                    System.out.println("ELIMINAR TODOS LOS DATOS DE BDINSTITUTO\n");
                    pedirDatosConexion(input);
                    conexionBDEliminarReiniciar(input);
                    break;
                case 0:
                    System.out.println("Volviendo al menú pirncipal...");
                    break;
                default:
                    System.out.println("No ha introducido ninguna de las opciones.");
                    break;

            }

        } while (op != 0);

    }

    private static void pedirDatosConexion(Scanner input) {

        System.out.println("Para conectarse a la base de datos BDINSTITUTO introduzca los datos que se piden a continuación.");
        System.out.println("NOTA: El nombre de la base de datos que se pide para poder establecer la conexión será cualquiera que\nya tenga usted creada en su servidor (puede ser BDINSTITUTO en caso de ya tenerla creada en su servidor).\n");

        System.out.println("PUERTO:");
        puerto = ControlData.leerString(input);
        System.out.println("NOMBRE BD:");
        nombreBD = ControlData.leerString(input);
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
        //Para my server de mysql en casa
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:" + puerto + "/" + nombreBD + "?user=" + usuario + "&password=" + password;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encontro el driver" + driver);
            System.exit(1);
        }
        try {
            Connection conexion = DriverManager.getConnection(url);
            sentencia = conexion.createStatement();
            System.out.println("\nSe ha establecido la conexión con la base de datos " + nombreBD+", la cual le da acceso a 'BDINSTITUTO'.");
        } catch (SQLException e) {
            System.out.println(e+"No hay ningún Driver registrado que reconozca la URL especificada");
            System.exit(2);
        } catch (Exception e) {
            System.out.println("\n\t Se ha producido algún otro error.");
            System.exit(3);
        }

        CrearTablas.crearTabla(sentencia);
        //CrearTablas.restriccionesDNI(sentencia);
        //CrearTablas.restriccionesCodigos(sentencia);
        System.out.println("BASE DE DATOS 'BDINSTITUTO' LISTA");

    }

    public static void conexionBDEliminarReiniciar(Scanner input) {

        //Declaracion de driver y url
        //Para usbwebserver
        //String driver= "com.mysql.cj.jdbc.Driver";   
        //String url= "jdbc:mysql://localhost:3307/Alumnos?user=root&password=usbw";
        //Para my server de mysql en casa
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:" + puerto + "/" + nombreBD + "?user=" + usuario + "&password=" + password;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encontro el driver" + driver);
            System.exit(1);
        }
        try {
            Connection conexion = DriverManager.getConnection(url);
            sentencia = conexion.createStatement();
            System.out.println("\nSe ha establecido la conexión con la base de datos " + nombreBD+", la cual le da acceso a 'BDINSTITUTO'.");
        } catch (SQLException e) {
            System.out.println("No hay ningún Driver registrado que reconozca la URL especificada");
            System.exit(2);
        } catch (Exception e) {
            System.out.println("\n\t Se ha producido algún otro error.");
            System.exit(3);
        }

        byte opb = 2;
        do {
            System.out.println("¿Está seguro que desea eliminar todos los datos de 'BDINSTITUTO'?");
            System.out.println("1.-SI");
            System.out.println("2.- NO");
            opb = ControlData.leerByte(input);
            switch (opb) {
                case 1:
                        try {
                    sentencia.execute("DROP DATABASE IF EXISTS BDINSTITUTO");
                    System.out.println("TODOS LOS DATOS DE 'BDINSTITUTO' HAN SIDO BORRADOS");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                CrearTablas.crearTabla(sentencia);
                //CrearTablas.restriccionesDNI(sentencia);
                //CrearTablas.restriccionesCodigos(sentencia);
                System.out.println("BASE DE DATOS 'BDINSTITUTO' REINICIADA Y LISTA");
                break;
                case 2:
                    System.out.println("Volviendo al menú de conexión con BD...");
                    break;
                default:
                    System.out.println("No ha introducido ninguna de las opciones.");
                    break;
            }
        } while (opb != 2 && opb != 1);

    }

}
