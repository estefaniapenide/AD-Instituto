/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Conexion;

import controldata.ControlData;
import static instituto_crud_estefania_penide.ConectarConBD.password;
import static instituto_crud_estefania_penide.ConectarConBD.puerto;
import static instituto_crud_estefania_penide.ConectarConBD.sentencia;
import static instituto_crud_estefania_penide.ConectarConBD.usuario;
import instituto_crud_estefania_penide.CrearTablas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.SQLNonTransientConnectionException;

/**
 *
 * @author Estefania
 */
public class Conexion {

    public static String puerto = null;
    public static String usuario = null;
    public static String password = null;

    public static boolean conexionBDInstituto = false;

    public static String indicadorConexion() {
        String indicador = "";
        if (conexionBDInstituto) {
            indicador = "CONECTADO CON BDINSTITUTO";
        } else {
            indicador = "NO HAY CONEXIÓN CON BDINSTITUTO";
        }
        return indicador;
    }

    public static void conectarseInstitutoBD(Scanner input) {

        System.out.println("CONECTARSE A BDINSTITUTO\n");

        pedirDatosConexion(input);
        Connection conexion = conexionMySQL();
        System.out.println("\nCONEXIÓN CON MYSQL LISTA.");
        try {
            CrearTablas.crearTabla(conexion.createStatement());
            System.out.println("BASE DE DATOS 'BDINSTITUTO' LISTA.");
            conexionBDInstituto = true;
        } catch (NullPointerException e) {
            conexionBDInstituto = false;
            System.out.println("NO HA SIDO POSIBLE CONECTARSE CON MYSQL.");
        } catch (SQLException e) {
            conexionBDInstituto = false;
            System.out.println("NO HA SIDO POSIBLE CONECTARSE A BDINSTITUTO");
        }

    }

    private static void pedirDatosConexion(Scanner input) {

        System.out.println("PUERTO:");
        puerto = ControlData.leerString(input);
        System.out.println("USUARIO:");
        usuario = ControlData.leerString(input);
        System.out.println("CONTRASEÑA:");
        password = ControlData.leerString(input);
    }

    public static Connection conexionMySQL() {

        Connection conexion = null;

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:" + puerto + "/?useSSL=false&serverTimezone=UTC&user=" + usuario + "&password=" + password;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encontro el driver" + driver);
            System.exit(1);
        }
        try {
            conexion = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("No hay ningún Driver registrado que reconozca la URL especificada");
            System.exit(2);
        } catch (Exception e) {
            System.out.println("\n\t Se ha producido algún otro error.");
            System.exit(3);

        }

        return conexion;

    }

}
