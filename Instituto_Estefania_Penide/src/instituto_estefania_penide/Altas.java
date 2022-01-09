/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instituto_estefania_penide;

import controldata.ControlData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;

/**
 *
 * @author Estefania
 */
public class Altas {

    public static void altas(Scanner input, Statement sentencia, ResultSet rstAux) {

        try {

            byte op = 0;
            do {

                Menus.menuAltas();
                op = ControlData.leerByte(input);

                switch (op) {
                    case 1:
                        profesores(input, sentencia, rstAux);
                        break;
                    case 2:
                        alumnos(input, sentencia, rstAux);
                        break;
                    case 3:
                        asignaturas(input, sentencia, rstAux);
                        break;
                    case 4:
                        notas(input, sentencia, rstAux);
                        break;
                    case 5:
                        alumnoAsignaturaProfesor(input, sentencia, rstAux);
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

    private static void profesores(Scanner input, Statement sentencia, ResultSet rstAux) throws SQLException {

        boolean existeDni = false;

        System.out.println("NUEVO PROFESOR");
        System.out.println("Introduzca el DNI del profesor:");
        String dni = ControlData.leerDni(input);
        rstAux = sentencia.executeQuery("SELECT * FROM PROFESORES");
        while (rstAux.next()) {
            if (dni.equalsIgnoreCase(rstAux.getString("dni"))) {
                existeDni = true;
            }
        }
        if (existeDni) {
            System.out.println("No se puede registar el DNI " + dni + ".\nEl dni que intenta introducir ya existe en la tabla profesores.");
            rstAux = sentencia.executeQuery("SELECT * FROM PROFESORES WHERE dni = '" + dni + "'");
            while (rstAux.next()) {
                System.out.println("Los datos del profesor son:");
                System.out.println("\tDNI: " + rstAux.getString("dni"));
                System.out.println("\tNombre: " + rstAux.getString("nombre"));
                System.out.println("\tTitulación: " + rstAux.getString("titulacion"));
            }
        } else if (!existeDni) {
            System.out.println("Introduzca el NOMBRE del profesor:");
            String nombre = ControlData.leerString(input);
            System.out.println("Introduzca la TITULACIÓN del profesor:");
            String titulacion = ControlData.leerString(input);
            sentencia.executeUpdate("INSERT INTO PROFESORES (dni, nombre, titulacion) VALUES ('" + dni + "','" + nombre + "','" + titulacion + "')");
            System.out.println("\nPROFESOR AÑADIDO");
            rstAux = sentencia.executeQuery("SELECT * FROM PROFESORES WHERE dni = '" + dni + "'");
            while (rstAux.next()) {
                System.out.println("Los datos del profesor son:");
                System.out.println("\tDNI: " + rstAux.getString("dni"));
                System.out.println("\tNombre: " + rstAux.getString("nombre"));
                System.out.println("\tTitulación: " + rstAux.getString("titulacion"));
            }
        }

    }

    private static void alumnos(Scanner input, Statement sentencia, ResultSet rstAux) throws SQLException {

        boolean existeCodigo = false;

        System.out.println("NUEVO ALUMNO");
        System.out.println("Introduzca el CÓDIGO del alumno:");
        String codigo = ControlData.leerCodigo(input);
        rstAux = sentencia.executeQuery("SELECT * FROM ALUMNOS");
        while (rstAux.next()) {
            if (codigo.equalsIgnoreCase(rstAux.getString("codigo_alumno"))) {
                existeCodigo = true;
            }
        }
        if (existeCodigo) {
            System.out.println("No se puede registar el código " + codigo + ".\nEl código que intenta introducir ya existe en la tabla alumnos.");
            rstAux = sentencia.executeQuery("SELECT * FROM ALUMNOS WHERE codigo_alumno = '" + codigo + "'");
            while (rstAux.next()) {
                System.out.println("Los datos del alumno son:");
                System.out.println("\tIdal: " + rstAux.getInt("idal"));
                System.out.println("\tCódigo_alumno: " + rstAux.getString("codigo_alumno"));
                System.out.println("\tNombre: " + rstAux.getString("nombre"));
            }
        } else if (!existeCodigo) {
            System.out.println("Introduzca el NOMBRE del alumno:");
            String nombre = ControlData.leerString(input);
            sentencia.executeUpdate("INSERT INTO ALUMNOS (codigo_alumno, nombre) VALUES ('" + codigo + "','" + nombre + "')");
            System.out.println("\nALUMNO AÑADIDO");
            rstAux = sentencia.executeQuery("SELECT * FROM ALUMNOS WHERE codigo_alumno = '" + codigo + "'");
            while (rstAux.next()) {
                System.out.println("Los datos del alumno son:");
                System.out.println("\tIdal: " + rstAux.getInt("idal"));
                System.out.println("\tCódigo_alumno: " + rstAux.getString("codigo_alumno"));
                System.out.println("\tNombre: " + rstAux.getString("nombre"));
            }
        }

    }

    private static void asignaturas(Scanner input, Statement sentencia, ResultSet rstAux) throws SQLException {

        boolean existeCodigo = false;

        System.out.println("NUEVA ASIGNATURA");
        System.out.println("Introduzca el CÓDIGO de la asignatura:");
        String codigo = ControlData.leerCodigo(input);
        rstAux = sentencia.executeQuery("SELECT * FROM ASIGNATURAS");
        while (rstAux.next()) {
            if (codigo.equalsIgnoreCase(rstAux.getString("codigo_asignatura"))) {
                existeCodigo = true;
            }
        }
        if (existeCodigo) {
            System.out.println("No se puede registar el código " + codigo + ".\nEl código que intenta introducir ya existe en la tabla asignaturas.");
            rstAux = sentencia.executeQuery("SELECT * FROM ASIGNATURAS WHERE codigo_asignatura = '" + codigo + "'");
            while (rstAux.next()) {
                System.out.println("Los datos de la asignatura son:");
                System.out.println("\tIdas: " + rstAux.getInt("idas"));
                System.out.println("\tCódigo_asignatura: " + rstAux.getString("codigo_asignatura"));
                System.out.println("\tNombre: " + rstAux.getString("nombre_ciclo"));
            }
        } else if (!existeCodigo) {
            System.out.println("Introduzca el NOMBRE de la asignatura:");
            String nombre = ControlData.leerString(input);
            sentencia.executeUpdate("INSERT INTO ASIGNATURAS (codigo_asignatura, nombre_ciclo) VALUES ('" + codigo + "','" + nombre + "')");
            System.out.println("\nASIGNATURA AÑADIDA");
            rstAux = sentencia.executeQuery("SELECT * FROM ASIGNATURAS WHERE codigo_asignatura = '" + codigo + "'");
            while (rstAux.next()) {
                System.out.println("Los datos de la asignatura son:");
                System.out.println("\tIdas: " + rstAux.getInt("idas"));
                System.out.println("\tCódigo_asignatura: " + rstAux.getString("codigo_asignatura"));
                System.out.println("\tNombre: " + rstAux.getString("nombre_ciclo"));
            }
        }

    }

    private static void notas(Scanner input, Statement sentencia, ResultSet rstAux) {

    }

    private static void alumnoAsignaturaProfesor(Scanner input, Statement sentencia, ResultSet rstAux) throws SQLException {

        boolean existeAlumno = false;
        boolean existeAsignatura = false;
        boolean existeProfesor = false;

        boolean existeAlumnoAsignatura = false;
        boolean existeAsignaturaProfesor = false;

        boolean existeAsignaturaEnTablaRelacional = false;

        System.out.println("NUEVA FILA ALUMNO + ASIGNATURA + PROFESOR");

        System.out.println("Introduzca el CÓDIGO del alumno:");
        String codigoAlumno = ControlData.leerString(input);
        rstAux = sentencia.executeQuery("SELECT * FROM ALUMNOS");
        while (rstAux.next()) {
            if (codigoAlumno.equalsIgnoreCase(rstAux.getString("codigo_alumno"))) {
                existeAlumno = true;
            }
        }
        if (existeAlumno) {//Si el alumno está registrado en la tabla ALUMNOS, se busca su IDAL para añadirlo a la tabla relacional
            int idal = 0;
            rstAux = sentencia.executeQuery("SELECT * FROM ALUMNOS WHERE codigo_alumno = '" + codigoAlumno + "'");
            while (rstAux.next()) {
                idal = rstAux.getInt("idal");
            }

            System.out.println("Introduzca el CÓDIGO de la asignatura:");
            String codigoAsignatura = ControlData.leerString(input);
            rstAux = sentencia.executeQuery("SELECT * FROM ASIGNATURAS");
            while (rstAux.next()) {
                if (codigoAsignatura.equalsIgnoreCase(rstAux.getString("codigo_asignatura"))) {
                    existeAsignatura = true;
                }
            }
            if (existeAsignatura) {//Si la asignatura está registrada en la tabla ASIGNATURAS, se busca su IDAS para añadirlo a la tabla relacional
                int idas = 0;
                rstAux = sentencia.executeQuery("SELECT * FROM ASIGNATURAS WHERE codigo_asignatura = '" + codigoAsignatura + "'");
                while (rstAux.next()) {
                    idas = rstAux.getInt("idas");
                }

                rstAux = sentencia.executeQuery("SELECT * FROM ALUMNOSASIGNATURASPROFESORES");
                while (rstAux.next()) {
                    if (idal == rstAux.getInt("idal") && idas == rstAux.getInt("idas")) {
                        existeAlumnoAsignatura = true;
                    }
                }
                if (!existeAlumnoAsignatura) {//Si el alumno no está asociado a la asignatura en la tabla relacional,se pide el DNI del profesor para poder añadirlos a la fila
                    System.out.println("Introduzca el DNI del profesor:");
                    String dni = ControlData.leerString(input);
                    rstAux = sentencia.executeQuery("SELECT * FROM PROFESORES");
                    while (rstAux.next()) {
                        if (dni.equalsIgnoreCase(rstAux.getString("dni"))) {
                            existeProfesor = true;
                        }
                    }
                    if (existeProfesor) {//Si el profesor existe...

                        rstAux = sentencia.executeQuery("SELECT * FROM ALUMNOSASIGNATURASPROFESORES");
                        while (rstAux.next()) {
                            if (idas == rstAux.getInt("idas")) {
                                existeAsignaturaEnTablaRelacional = true;
                            }
                        }
                        if (!existeAsignaturaEnTablaRelacional) { //Si la asignatura no aparece en la tabla relacional, se podrá asignar a ese profesor y al alumno
                            sentencia.executeUpdate("INSERT INTO ALUMNOSASIGNATURASPROFESORES (idal, idas, dni_profesor) VALUES (" + idal + "," + idas + ",'" + dni + "')");
                            System.out.println("LOS DATOS HAN SIDO REGISTRADOS CORRECTAMENTE.");
                        } else if (existeAsignaturaEnTablaRelacional) {//Si la asignatura ya está en la tabla relacional...
                            rstAux = sentencia.executeQuery("SELECT * FROM ALUMNOSASIGNATURASPROFESORES");
                            while (rstAux.next()) {
                                if (idas == rstAux.getInt("idas") && dni.equalsIgnoreCase(rstAux.getString("dni_profesor"))) {
                                    existeAsignaturaProfesor = true;
                                }
                            }
                            if (existeAsignaturaProfesor) {//Si ya hay en la tabla una asignatura con ese código asignada a ese profesor, se podrá asignar al nuevo alumno.
                                sentencia.executeUpdate("INSERT INTO ALUMNOSASIGNATURASPROFESORES (idal, idas, dni_profesor) VALUES (" + idal + "," + idas + ",'" + dni + "')");
                                System.out.println("LOS DATOS HAN SIDO REGISTRADOS CORRECTAMENTE.");
                            } else if (!existeAsignaturaProfesor) {//Si ya está la asignatura en la tabla y no está asociada a ese profesor, no se podrá asociar.
                                rstAux = sentencia.executeQuery("SELECT nombre_ciclo FROM ASIGNATURAS WHERE codigo_asignatura = '" + codigoAsignatura + "'");
                                String asig = null;
                                while (rstAux.next()) {
                                    asig = rstAux.getString("nombre_ciclo");
                                }
                                System.out.println("No es posible asignar al profesor con DNI " + dni + " la asignatura de " + asig + " (CÓDIGO " + codigoAsignatura + "), ya que esta la imparte otro profesor.");
                                rstAux = sentencia.executeQuery("SELECT AAP.dni_profesor FROM ALUMNOSASIGNATURASPROFESORES AAP JOIN ASIGNATURAS A USING(idas) WHERE A.codigo_asignatura ='" + codigoAsignatura + "';");
                                String prof = null;
                                while (rstAux.next()) {
                                    prof = rstAux.getString("AAP.dni_profesor");
                                }
                                System.out.println("\nASIGNATURA + PROFESOR");
                                System.out.println(asig + " (CÓDIGO " + codigoAsignatura + "), Profesor(DNI): " + prof);
                            }

                        }

                    } else if (!existeProfesor) {//Si el profesor no está registrado en PROFESORES, no se podrá añadir a la tabla relacional
                        System.out.println("No es posible añadir al profesor con DNI " + dni + " a la tabla ya que no está registrada en PROFESORES.");
                    }

                } else if (existeAlumnoAsignatura) {//Si el alumno ya está matriculado en la asignatura, no se puede volver a matricular
                    rstAux = sentencia.executeQuery("SELECT A.nombre, AG.nombre_ciclo, AAP.dni_profesor FROM ALUMNOS A JOIN "
                            + " ALUMNOSASIGNATURASPROFESORES AAP USING (idal) JOIN ASIGNATURAS AG USING(idas) WHERE AAP.idal = " + idal + " AND AAP.idas = " + idas + ";");
                    String nomAl = null;
                    String nomAs = null;
                    String dniProf = null;
                    while (rstAux.next()) {
                        nomAl = rstAux.getString("A.nombre");
                        nomAs = rstAux.getString("AG.nombre_ciclo");
                        dniProf = rstAux.getString("AAP.dni_profesor");
                    }
                    System.out.println("No es posible matricular al alumno.\nEl alumno " + nomAl + "(CÓDIGO " + codigoAlumno + ") ya está matriculado en " + nomAs + "(CÓDIGO " + codigoAsignatura + ").");
                    System.out.println("\nALUMNO + ASIGNATURA + PROFESOR:");
                    System.out.println("\tCódigo_alumno: " + codigoAlumno);
                    System.out.println("\tCódigo_asignatura: " + codigoAsignatura);
                    System.out.println("\tProfesor: " + dniProf);

                }

            } else if (!existeAsignatura) {//Si la asignatura no está registrada en ASIGNATURAS, no se podrá añadir a la tabla relacional
                System.out.println("No es posible añadir la asignatura con CÓDIGO " + codigoAsignatura + " a la tabla ya que no está registrada en ASIGNATURAS.");
            }

        } else if (!existeAlumno) {//Si el alumno no está registrado en ALUMNOS, no se podrá añadir a la tabla relacional
            System.out.println("No es posible añadir el alumno con CÓDIGO " + codigoAlumno + " a la tabla ya que no está registrado en ALUMNOS.");
        }

    }

}
