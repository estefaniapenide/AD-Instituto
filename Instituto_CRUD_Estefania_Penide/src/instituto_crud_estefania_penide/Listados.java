/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instituto_crud_estefania_penide;

import CRUD.Controlador.AlumnoControlador;
import CRUD.Controlador.AsignaturaControlador;
import CRUD.Controlador.ProfesorControlador;
import CRUD.Modelo.Alumno;
import CRUD.Modelo.Asignatura;
import CRUD.Modelo.Profesor;
import controldata.ControlData;
import java.util.Scanner;

/**
 *
 * @author Estefania
 */
public class Listados {

    public static void consultas(Scanner input) {

        byte op = 0;
        do {
            Menus.menuListados();
            op = ControlData.leerByte(input);

            switch (op) {
                case 1:
                    listadoProfesor(input);
                    break;
                case 2:
                    listadoAlumno(input);
                    break;
                case 3:
                    listadoAsignatura(input);
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

    private static void listadoProfesor(Scanner input) {

        boolean existe = false;

        ProfesorControlador profCont = new ProfesorControlador();

        System.out.println("ASIGNATURAS DE UN PROFESOR");
        System.out.println("Introduzca el DNI del profesor:");
        String dni = ControlData.leerString(input);

        Profesor profesor = new Profesor(dni);
        existe = profCont.existe(profesor);

        if (existe) {
            profesor = profCont.obtener(profesor);

            profCont.verAsignaturas(profesor);

        } else if (!existe) {
            System.out.println("\nERROR. El profesor con DNI " + dni + " no está registrado.");
        }

    }

    private static void listadoAlumno(Scanner input) {

        boolean existe = false;

        AlumnoControlador alumCont = new AlumnoControlador();

        System.out.println("NOTAS DE UN ALUMNO");
        System.out.println("Introduzca el CÓDIGO del alumno:");
        String codigoAlumno = ControlData.leerString(input);

        Alumno alumno = new Alumno(codigoAlumno);
        existe = alumCont.existe(alumno);

        if (existe) {
            alumno = alumCont.obtener(alumno);

            alumCont.verNotas(alumno);

        } else if (!existe) {
            System.out.println("\nERROR. El alumno con CÓDIGO " + codigoAlumno + " no está registrado.");
        }

    }

    private static void listadoAsignatura(Scanner input) {

        boolean existe = false;

        AsignaturaControlador asigCont = new AsignaturaControlador();

        System.out.println("ASIGNATURA Y PROFESOR");
        System.out.println("Introduzca el CÓDIGO de la asignatura:");
        String codigoAsignatura = ControlData.leerString(input);

        Asignatura asignatura = new Asignatura(codigoAsignatura);
        existe = asigCont.existe(asignatura);

        if (existe) {
            asignatura = asigCont.obtener(asignatura);
            asigCont.verProfesor(asignatura);

        } else if (!existe) {
            System.out.println("\nERROR. La asignatura con CÓDIGO " + codigoAsignatura + " no está registrada.");
        }

    }

}
