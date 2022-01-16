package instituto_crud_estefania_penide;

import CRUD.Controlador.AlumnoControlador;
import CRUD.Controlador.AsignaturaControlador;
import CRUD.Controlador.NotaControlador;
import CRUD.Modelo.Alumno;
import CRUD.Modelo.Asignatura;
import CRUD.Modelo.Nota;
import controldata.ControlData;
import java.sql.Date;
import java.util.Scanner;


/**
 *
 * @author Estefania
 */
public class Modificaciones {

    public static void notaAlumno(Scanner input) {


            boolean existeAlumno = false;
            boolean existeAsignatura = false;
            boolean existeAsignaturaAlumno = false;
            boolean existeNota = false;

            AlumnoControlador alumCont = new AlumnoControlador();
            AsignaturaControlador asigCont = new AsignaturaControlador();
            NotaControlador notaCont = new NotaControlador();

            System.out.println("MODIFICAR NOTA DE ALUMNO");

            System.out.println("Introduzca el CÓDIGO del alumno:");
            String codigoAlumno = ControlData.leerString(input);

            Alumno alumno = new Alumno(codigoAlumno);
            existeAlumno = alumCont.existe(alumno);

            if (existeAlumno) {
                alumno = alumCont.obtener(alumno);
                
                alumCont.verNotas(alumno);

                System.out.println("\nIntroduzca el CÓDIGO de la asignatura de la nota a modificar:");
                String codigoAsignatura = ControlData.leerString(input);

                Asignatura asignatura = new Asignatura(codigoAsignatura);
                existeAsignatura = asigCont.existe(asignatura);

                if (existeAsignatura) {

                    asignatura = asigCont.obtener(asignatura);

                    existeAsignaturaAlumno = notaCont.existe(alumno, asignatura);

                    if (existeAsignaturaAlumno) {
                        System.out.println("Introduzca la FECHA de la nota a modificar (aaaa-mm-dd):");
                        String fecha = ControlData.leerString(input);
                        int dia = Integer.parseInt(Character.toString(fecha.charAt(8)) + Character.toString(fecha.charAt(9)));
                        int mes = Integer.parseInt(Character.toString(fecha.charAt(5)) + Character.toString(fecha.charAt(6)));
                        int ano = Integer.parseInt(Character.toString(fecha.charAt(0)) + Character.toString(fecha.charAt(1)) + Character.toString(fecha.charAt(2)) + Character.toString(fecha.charAt(3)));

                        Date date = new Date(ano - 1900, mes - 1, dia);
                        
                        Nota nota = new Nota(alumno,asignatura,date);
                        existeNota=notaCont.existe(nota);

                        if (existeNota) {
                            nota=notaCont.obtener(nota);
                            notaCont.ver(nota);

                            System.out.println("\nIntroduzca la NUEVA NOTA:");
                            Float n = ControlData.leerFloat(input);
                            nota=new Nota(alumno,asignatura,date,n);
                            
                            notaCont.actualizar(nota);

                        } else if (!existeNota) {
                            System.out.println("\nERROR. El alumno " + codigoAlumno + " no tiene ninguna nota en la asignatura " + codigoAsignatura + " a fecha de " + fecha + ".");
                        }
                    } else if (!existeAsignaturaAlumno) {
                        System.out.println("\nERROR. El alumno " + codigoAlumno + " no está matriculado en la asignatura " + codigoAsignatura + ".");
                    }
                } else if (!existeAsignatura) {
                    System.out.println("\nERROR.La asignatura " + codigoAsignatura + " no está registrada.");
                }
            } else if (!existeAlumno) {
                System.out.println("\nERROR.El alumno " + codigoAlumno + " no está registrado.");
            }

    }

}
