/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instituto_crud_estefania_penide;

import CRUD.Controlador.AlumnoControlador;
import CRUD.Controlador.ProfesorControlador;
import CRUD.Modelo.Alumno;
import CRUD.Modelo.Profesor;
import controldata.ControlData;
import java.util.Scanner;

/**
 *
 * @author Estefania
 */
public class Bajas {

    public static void bajas(Scanner input) {

        byte op = 0;
        do {
            Menus.menuBajas();
            op = ControlData.leerByte(input);

            switch (op) {
                case 1:
                    profesores(input);
                    break;
                case 2:
                    alumnos(input);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("El valor introducido no se corresponde con ninguna de las opciones.\n");
                    break;
            }

        } while (op != 0);

    }

    private static void profesores(Scanner input) {

        boolean existe;
        ProfesorControlador profCont = new ProfesorControlador();

        System.out.println("ELIMINAR PROFESOR");
        System.out.println("Introduzca el DNI del profesor:");
        String dni = ControlData.leerString(input);

        Profesor profesor = new Profesor(dni);

        existe = profCont.existe(profesor);

        if (existe) {

            profCont.ver(profesor);

            byte opb = 2;
            do {
                System.out.println("¿Está seguro que desea eliminar este profesor y los datos de cuáles son las asignaturas que él imparte y de quién son sus alumnos?");
                System.out.println("1. Si");
                System.out.println("2. No");
                opb = ControlData.leerByte(input);
                switch (opb) {
                    case 1:
                        profCont.eliminar(profesor);
                        break;
                    case 2:
                        System.out.println("Volviendo al menú bajas...");
                        break;
                    default:
                        System.out.println("No ha introducido ninguna de las opciones.");
                        break;
                }
            } while (opb != 2 && opb != 1);

        } else if (!existe) {
            System.out.println("\nERROR.No existe ningún profesor con DNI " + dni);
        }

    }

    private static void alumnos(Scanner input) {

        boolean existe;
        AlumnoControlador alumnCont = new AlumnoControlador();

        System.out.println("ELIMINAR ALUMNO");
        System.out.println("Introduzca el CÓDIGO del alumno:");
        String codigo = ControlData.leerString(input);

        Alumno alumno = new Alumno(codigo);

        existe = alumnCont.existe(alumno);

        if (existe) {

            alumnCont.ver(alumno);

            byte opb = 2;
            do {
                System.out.println("¿Está seguro que desea eliminar este alumno, sus notas y los datos de en qué asignaturas está matriculado y cuáles son sus profesores?");
                System.out.println("1. Si");
                System.out.println("2. No");
                opb = ControlData.leerByte(input);
                switch (opb) {
                    case 1:
                        alumnCont.eliminar(alumno);
                        break;
                    case 2:
                        System.out.println("Volviendo al menú bajas...");
                        break;
                    default:
                        System.out.println("No ha introducido ninguna de las opciones.");
                        break;
                }
            } while (opb != 2 && opb != 1);

        } else if (!existe) {
            System.out.println("\nERROR.No existe ningún alumno con CÓDIGO " + codigo);
        }

    }

}
