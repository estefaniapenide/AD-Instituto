/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instituto_crud_estefania_penide;

import CRUD.Conexion.Conexion;
import controldata.ControlData;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Estefania
 */
public class ResetDataBase {

    public static void EliminarDatosBDInstituto(Scanner input) {

        Connection conexion = null;
        Statement sentencia = null;

        System.out.println("ELIMINAR TODOS LOS DATOS DE 'BDINSTITUTO'");

        byte opb = 2;
        do {
            System.out.println("¿Está seguro que desea eliminar todos los datos de 'BDINSTITUTO'?");
            System.out.println("1.-SI");
            System.out.println("2.- NO");
            opb = ControlData.leerByte(input);
            switch (opb) {
                case 1:
                        try {
                    conexion = Conexion.conexionMySQL();
                    sentencia = conexion.createStatement();

                    sentencia.execute("USE BDINSTITUTO");
                    sentencia.execute("DROP DATABASE IF EXISTS BDINSTITUTO");
                    System.out.println("TODOS LOS DATOS DE 'BDINSTITUTO' HAN SIDO BORRADOS");

                    CrearTablas.crearTabla(sentencia);
                    //CrearTablas.restriccionesDNI(sentencia);
                    //CrearTablas.restriccionesCodigos(sentencia);
                    System.out.println("BASE DE DATOS 'BDINSTITUTO' REINICIADA Y LISTA");

                    sentencia.close();
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
                case 2:
                    System.out.println("Volviendo al menú de principal...");
                    break;
                default:
                    System.out.println("No ha introducido ninguna de las opciones.");
                    break;
            }
        } while (opb != 2 && opb != 1);
    }

}
