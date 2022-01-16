/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instituto_crud_estefania_penide;

import CRUD.Controlador.AlumnoControlador;
import CRUD.Controlador.AsignaturaControlador;
import CRUD.Controlador.MatriculaControlador;
import CRUD.Controlador.NotaControlador;
import CRUD.Controlador.ProfesorControlador;
import CRUD.Modelo.Alumno;
import CRUD.Modelo.Asignatura;
import CRUD.Modelo.Matricula;
import CRUD.Modelo.Nota;
import CRUD.Modelo.Profesor;
import controldata.ControlData;
import java.util.Scanner;
import java.sql.Date;

/**
 *
 * @author Estefania
 */
public class Altas {

    public static void altas(Scanner input) {

            byte op = 0;
            do {

                Menus.menuAltas();
                op = ControlData.leerByte(input);

                switch (op) {
                    case 1:
                        profesores(input);
                        break;
                    case 2:
                        alumnos(input);
                        break;
                    case 3:
                        asignaturas(input);
                        break;
                    case 4:
                        notas(input);
                        break;
                    case 5:
                        alumnoAsignaturaProfesor(input);
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

        System.out.println("NUEVO PROFESOR");
        System.out.println("Introduzca el DNI del profesor:");
        String dni = ControlData.leerDni(input);
        System.out.println("Introduzca el NOMBRE del profesor:");
        String nombre = ControlData.leerString(input);
        System.out.println("Introduzca la TITULACIÓN del profesor:");
        String titulacion = ControlData.leerString(input);

        Profesor profesor = new Profesor(dni, nombre, titulacion);

        ProfesorControlador profCont = new ProfesorControlador();
        profCont.registrar(profesor);

        profCont.ver(profesor);

    }

    private static void alumnos(Scanner input) {

        System.out.println("NUEVO ALUMNO");
        System.out.println("Introduzca el CÓDIGO del alumno:");
        String codigo = ControlData.leerCodigo(input);
        System.out.println("Introduzca el NOMBRE del alumno:");
        String nombre = ControlData.leerString(input);

        Alumno alumno = new Alumno(codigo, nombre);

        AlumnoControlador alumCont = new AlumnoControlador();
        alumCont.registrar(alumno);

        alumCont.ver(alumno);

    }

    private static void asignaturas(Scanner input) {

        System.out.println("NUEVA ASIGNATURA");
        System.out.println("Introduzca el CÓDIGO de la asignatura:");
        String codigo = ControlData.leerCodigo(input);
        System.out.println("Introduzca el NOMBRE de la asignatura:");
        String nombre = ControlData.leerString(input);

        Asignatura asignatura = new Asignatura(codigo, nombre);

        AsignaturaControlador asigCont = new AsignaturaControlador();
        asigCont.registrar(asignatura);

        asigCont.ver(asignatura);

    }

    private static void notas(Scanner input) {

        boolean existeAlumno = false;
        boolean existeAsignatura = false;

        AlumnoControlador alumCont = new AlumnoControlador();
        AsignaturaControlador asigCont = new AsignaturaControlador();
        NotaControlador notCont = new NotaControlador();

        System.out.println("NUEVA NOTA");

        System.out.println("Introduzca el CÓDIGO del alumno:");
        String codigoAlumno = ControlData.leerString(input);

        Alumno alumno = new Alumno(codigoAlumno);
        existeAlumno = alumCont.existe(alumno);//Comprueba si el alumno está registado en ALUMNOS.

        if (existeAlumno) {

            //Teníamos solo el código del alumno, pero empleando el controlador, obtenemos el alumno de la tabla ALUMNOS
            alumno = alumCont.obtener(alumno);

            System.out.println("Introduzca el CÓDIGO de la asignatura:");
            String codigoAsignatura = ControlData.leerString(input);

            Asignatura asignatura = new Asignatura(codigoAsignatura);
            existeAsignatura = asigCont.existe(asignatura);//Comprueba si la asignatura está registrada en ASIGNATURAS.

            if (existeAsignatura) {

                //Teníamos solo el código de la asignatura, pero empleando el controlador, obtenermos la asignatura de la tabla ASIGNATURAS
                asignatura = asigCont.obtener(asignatura);

                System.out.println("Introduzca la FECHA (aaaa-mm-dd):");
                String fecha = ControlData.leerFecha(input);
                int dia = Integer.parseInt(Character.toString(fecha.charAt(8)) + Character.toString(fecha.charAt(9)));
                int mes = Integer.parseInt(Character.toString(fecha.charAt(5)) + Character.toString(fecha.charAt(6)));
                int ano = Integer.parseInt(Character.toString(fecha.charAt(0)) + Character.toString(fecha.charAt(1)) + Character.toString(fecha.charAt(2)) + Character.toString(fecha.charAt(3)));

                Date date = new Date(ano - 1900, mes - 1, dia);

                System.out.println("Introduzca la NOTA:");
                Float n = ControlData.leerFloat(input);

                Nota nota = new Nota(alumno, asignatura, date, n);
                notCont.registrar(nota);

                notCont.ver(nota);

            } else if (!existeAsignatura) {//Si la asignatura no está registrada en ASIGNATURAS, no se podrá añadir a la tabla NOTAS
                System.out.println("ERROR.No es posible añadir la asignatura con CÓDIGO " + codigoAsignatura + " a la tabla ya que no está registrada en ASIGNATURAS.");
            }
        } else if (!existeAlumno) {//Si el alumno no está registrado en ALUMNOS, no se podrá añadir a la tabla NOTAS
            System.out.println("ERROR.No es posible añadir el alumno con CÓDIGO " + codigoAlumno + " a la tabla ya que no está registrado en ALUMNOS.");
        }

    }

    private static void alumnoAsignaturaProfesor(Scanner input) {

        boolean existeAlumno = false;
        boolean existeAsignatura = false;
        boolean existeProfesor = false;

        boolean existeMatricula = false;
        boolean existeAsignaturaProfesor = false;

        boolean existeAsignaturaEnTablaRelacional = false;

        AlumnoControlador alumCont = new AlumnoControlador();
        AsignaturaControlador asigCont = new AsignaturaControlador();
        MatriculaControlador aapCont = new MatriculaControlador();
        ProfesorControlador profCont = new ProfesorControlador();

        System.out.println("NUEVA FILA ALUMNO + ASIGNATURA + PROFESOR");

        System.out.println("Introduzca el CÓDIGO del alumno:");
        String codigoAlumno = ControlData.leerString(input);

        Alumno alumno = new Alumno(codigoAlumno);
        existeAlumno = alumCont.existe(alumno);//Comprueba si el alumno está registado en ALUMNOS.

        if (existeAlumno) {

            //Teníamos solo el código del alumno, pero empleando el controlador, obtenemos el alumno de la tabla ALUMNOS
            alumno = alumCont.obtener(alumno);

            System.out.println("Introduzca el CÓDIGO de la asignatura:");
            String codigoAsignatura = ControlData.leerString(input);

            Asignatura asignatura = new Asignatura(codigoAsignatura);
            existeAsignatura = asigCont.existe(asignatura);//Comprueba si la asignatura está registada en ASIGNATURAS.

            if (existeAsignatura) {

                //Teníamos solo el código de la asignatura, pero empleando el controlador, obtenemos la asignatura de la tabla ASIGNATURAS
                asignatura = asigCont.obtener(asignatura);

                Matricula aap = new Matricula(alumno, asignatura);
                existeMatricula = aapCont.existe(aap);//Comprueba si la matricula está registada en ALUMNOSASIGNATURASPROFESORES.

                if (!existeMatricula) {

                    System.out.println("Introduzca el DNI del profesor:");
                    String dni = ControlData.leerString(input);

                    Profesor profesor = new Profesor(dni);
                    existeProfesor = profCont.existe(profesor);//Comprueba si el profesor está registado en PROFESORES.

                    if (existeProfesor) {//Si el profesor existe...
                        
                        //Teníamos solo el dni del profesor, pero empleando el controlador, obtenemos el mprofesor de la tabla PROFESORES
                        profesor = profCont.obtener(profesor);
                        existeAsignaturaEnTablaRelacional = aapCont.existe(asignatura);//Comprueba si la asignatura está registada en ALUMNOSASIGNATURASPROFESORES.

                        if (!existeAsignaturaEnTablaRelacional) { //Si la asignatura no aparece en la tabla relacional, se podrá asignar a ese profesor
                            aap = new Matricula(alumno, asignatura, profesor);
                            aapCont.registrar(aap);
                            aapCont.ver(aap);

                        } else if (existeAsignaturaEnTablaRelacional) {//Si la asignatura ya está en la tabla relacional...
                            existeAsignaturaProfesor = aapCont.existe(asignatura, profesor);//Comprueba si esa asignatura ya tiene asociado un profesor

                            if (existeAsignaturaProfesor) {//Si ya hay en la tabla una asignatura con ese código asignada a ese profesor, se podrá asignar al nuevo alumno.
                                aap = new Matricula(alumno, asignatura, profesor);
                                aapCont.registrar(aap);
                                aapCont.ver(aap);

                            } else if (!existeAsignaturaProfesor) {//Si ya está la asignatura en la tabla y no está asociada a ese profesor, no se podrá asociar, ya que le pertenece a otro.
                                
                                System.out.println("\nERROR.No es posible asignar al profesor con DNI " + dni + " la asignatura de " + asignatura.getNombre_ciclo() + " (CÓDIGO " + asignatura.getCodigo_asignatura()+ "), ya que esta la imparte otro profesor.");

                                Profesor profAsig = new Profesor();
                                profAsig=aapCont.obteneProfesor(asignatura);//Se obtien el profesor al que tiene asignada esa asignatura
                                profAsig=profCont.obtener(profAsig);

                                System.out.println("\nASIGNATURA + PROFESOR");
                                System.out.println("\tAsignatura: " + codigoAsignatura + " " + asignatura.getNombre_ciclo());
                                System.out.println("\tProfesor: " + profAsig.getDni()+", "+profAsig.getNombre());
                            }

                        }

                    } else if (!existeProfesor) {//Si el profesor no está registrado en PROFESORES, no se podrá añadir a la tabla relacional
                        System.out.println("\nERROR.No es posible añadir al profesor con DNI " + dni + " a la tabla ya que no está registrada en PROFESORES.");
                    }

                } else if (existeMatricula) {//Si el alumno ya está matriculado en la asignatura, no se puede volver a matricular

                    System.out.println("\nERROR.No es posible matricular al alumno.\nEl alumno " + aap.getAlumno().getCodigo_alumno() + " ya está matriculado en la asignatura "+ aap.getAsignatura().getCodigo_asignatura() + ".");
                    aapCont.ver(aap);

                }

            } else if (!existeAsignatura) {//Si la asignatura no está registrada en ASIGNATURAS, no se podrá añadir a la tabla relacional
                System.out.println("\nERROR.No es posible añadir la asignatura con CÓDIGO " + codigoAsignatura + " a la tabla ya que no está registrada en ASIGNATURAS.");
            }

        } else if (!existeAlumno) {//Si el alumno no está registrado en ALUMNOS, no se podrá añadir a la tabla relacional
            System.out.println("\nERROR.No es posible añadir el alumno con CÓDIGO " + codigoAlumno + " a la tabla ya que no está registrado en ALUMNOS.");
        }

    }

}
