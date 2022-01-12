/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instituto_crud_estefania_penide;

import controldata.ControlData;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Estefania
 */
public class Bajas {

    public static void bajas(Scanner input, Statement sentencia) {

        try {
            byte op = 0;
            do {
                Menus.menuBajas();
                op = ControlData.leerByte(input);

                switch (op) {
                    case 1:
                        profesores(input, sentencia);
                        break;
                    case 2:
                        alumnos(input, sentencia);
                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("El valor introducido no se corresponde con ninguna de las opciones.\n");
                        break;
                }

            } while (op != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void profesores(Scanner input, Statement sentencia) throws SQLException {

        boolean existe = false;

        System.out.println("ELIMINAR PROFESOR");
        System.out.println("Introduzca el DNI del profesor:");
        String dni = ControlData.leerString(input);
        ResultSet rstAux = sentencia.executeQuery("SELECT * FROM PROFESORES");
        while (rstAux.next()) {
            if (dni.equalsIgnoreCase(rstAux.getString("dni"))) {
                existe = true;
            }
        }
        if (existe) {
            byte opb = 2;
            do {
                System.out.println("¿Está seguro que desea eliminar este profesor y los datos de cuáles son las asignaturas que él imparte y de quién son sus alumnos?");
                System.out.println("1. Si");
                System.out.println("2. No");
                opb = ControlData.leerByte(input);
                switch (opb) {
                    case 1:
                        sentencia.executeUpdate("DELETE FROM PROFESORES WHERE (dni = '" + dni + "')");
                        break;
                    case 2:
                        System.out.println("Volviendo al menú bajas...");
                        break;
                    default:
                        System.out.println("No ha introducido ninguna de las opciones.");
                        break;
                }
            } while (opb != 2 && opb!=1);

        } else if (!existe) {
            System.out.println("\nERROR.No existe ningún profesor con DNI " + dni);
        }
        
        rstAux.close();

    }

    private static void alumnos(Scanner input, Statement sentencia) throws SQLException {
        
        boolean existe = false;

        System.out.println("ELIMINAR ALUMNO");
        System.out.println("Introduzca el CÓDIGO del alumno:");
        String codigo = ControlData.leerString(input);
        ResultSet rstAux = sentencia.executeQuery("SELECT * FROM ALUMNOS");
        while (rstAux.next()) {
            if (codigo.equalsIgnoreCase(rstAux.getString("codigo_alumno"))) {
                existe = true;
            }
        }
        if (existe) {
            byte opb = 2;
            do {
                System.out.println("¿Está seguro que desea eliminar este alumno, sus notas y los datos de en qué asignaturas está matriculado y cuáles son sus profesores?");
                System.out.println("1. Si");
                System.out.println("2. No");
                opb = ControlData.leerByte(input);
                switch (opb) {
                    case 1:
                        sentencia.executeUpdate("DELETE FROM ALUMNOS WHERE (codigo_alumno = '" + codigo + "')");
                        break;
                    case 2:
                        System.out.println("Volviendo al menú bajas...");
                        break;
                    default:
                        System.out.println("No ha introducido ninguna de las opciones.");
                        break;
                }
            } while (opb != 2 && opb!=1);

        } else if (!existe) {
            System.out.println("\nERROR.No existe ningún alumno con CÓDIGO " + codigo);
        }
        
        rstAux.close();

    }

}
