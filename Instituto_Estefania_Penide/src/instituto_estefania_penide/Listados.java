/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instituto_estefania_penide;

import controldata.ControlData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Estefania
 */
public class Listados {

    public static void consultas(Scanner input, Statement sentencia, ResultSet rstAux) {

        try {
            byte op = 0;
            do {
                Menus.menuListados();
                op = ControlData.leerByte(input);

                switch (op) {
                    case 1:
                        listadoProfesor(input, sentencia, rstAux);
                        break;
                    case 2:
                        listadoAlumno(input, sentencia, rstAux);
                        break;
                    case 3:
                        listadoAsignatura(input, sentencia, rstAux);
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

    private static void listadoProfesor(Scanner input, Statement sentencia, ResultSet rstAux) throws SQLException {

        boolean existe = false;

        System.out.println("ASIGNATURAS DE UN PROFESOR");
        System.out.println("Introduzca el DNI del profesor:");
        String dni = ControlData.leerString(input);
        rstAux = sentencia.executeQuery("SELECT * FROM PROFESORES");
        while (rstAux.next()) {
            if (dni.equalsIgnoreCase(rstAux.getString("dni"))) {
                existe = true;
            }
        }
        if(existe){
            rstAux=sentencia.executeQuery("SELECT nombre, titulacion FROM PROFESORES WHERE dni='"+dni+"';");
            String nombre=null;
            String titulacion=null;
            while(rstAux.next()){
                nombre=rstAux.getString("nombre");
                titulacion=rstAux.getString("titulacion");
            }
            System.out.println("\nPROFESOR "+nombre.toUpperCase()+", DNI "+dni.toUpperCase()+", TITULACION "+titulacion.toUpperCase());
            System.out.println("\nASISNATURAS:");
            rstAux=sentencia.executeQuery("SELECT AG.codigo_asignatura, AG.nombre_ciclo FROM PROFESORES P JOIN ALUMNOSASIGNATURASPROFESORES AAP ON P.dni=AAP.dni_profesor JOIN ASIGNATURAS AG USING(idas) WHERE AAP.dni_profesor='"+dni+"' GROUP BY AG.codigo_asignatura;");
            System.out.println("CÓDIGO\t\tNOMBRE");
            System.out.println("-----------------------------------------------------");
            while (rstAux.next()) {
                    System.out.print(rstAux.getString("AG.codigo_asignatura") + "\t\t");
                    System.out.print(rstAux.getString("AG.nombre_ciclo") + "\t\t\n");
                }
        }else if(!existe){
        System.out.println("\nERROR. El profesor con DNI "+dni+" no está registrado.");
        }

    }

    private static void listadoAlumno(Scanner input, Statement sentencia, ResultSet rstAux) {

    }

    private static void listadoAsignatura(Scanner input, Statement sentencia, ResultSet rstAux) {

    }

}
