
package instituto_estefania_penide;

import controldata.ControlData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;
import java.sql.Statement;

/**
 *
 * @author Estefania
 */
public class Altas {

    public static void altas(Scanner input, Statement sentencia) {

        try {

            byte op = 0;
            do {

                Menus.menuAltas();
                op = ControlData.leerByte(input);

                switch (op) {
                    case 1:
                        profesores(input, sentencia);
                        break;
                    case 2:
                        alumnos(input, sentencia);
                        break;
                    case 3:
                        asignaturas(input, sentencia);
                        break;
                    case 4:
                        notas(input, sentencia);
                        break;
                    case 5:
                        alumnoAsignaturaProfesor(input, sentencia);
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

        String dni = null;
        try {

            System.out.println("NUEVO PROFESOR");
            System.out.println("Introduzca el DNI del profesor:");
            dni = ControlData.leerDni(input);
            System.out.println("Introduzca el NOMBRE del profesor:");
            String nombre = ControlData.leerString(input);
            System.out.println("Introduzca la TITULACIÓN del profesor:");
            String titulacion = ControlData.leerString(input);
            sentencia.executeUpdate("INSERT INTO PROFESORES (dni, nombre, titulacion) VALUES ('" + dni + "','" + nombre + "','" + titulacion + "')");
            System.out.println("\nPROFESOR AÑADIDO");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("\nERROR. No se puede registar el DNI " + dni + ".\nEl dni que intenta introducir YA EXISTE en la tabla PROFESORES.");
        } finally {
            ResultSet rstAux = sentencia.executeQuery("SELECT * FROM PROFESORES WHERE dni = '" + dni + "'");
            while (rstAux.next()) {
                System.out.println("\nLos DATOS del PROFESOR son:");
                System.out.println("\tDNI: " + rstAux.getString("dni"));
                System.out.println("\tNombre: " + rstAux.getString("nombre"));
                System.out.println("\tTitulación: " + rstAux.getString("titulacion"));
            }
            rstAux.close();
        }
    }

    private static void alumnos(Scanner input, Statement sentencia) throws SQLException {

        String codigo = null;

        try {

            System.out.println("NUEVO ALUMNO");
            System.out.println("Introduzca el CÓDIGO del alumno:");
            codigo = ControlData.leerCodigo(input);
            System.out.println("Introduzca el NOMBRE del alumno:");
            String nombre = ControlData.leerString(input);
            sentencia.executeUpdate("INSERT INTO ALUMNOS (codigo_alumno, nombre) VALUES ('" + codigo + "','" + nombre + "')");
            System.out.println("\nALUMNO AÑADIDO");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("\nERROR. No se puede registar el CÓDIGO " + codigo + ".\nEl código que intenta introducir YA EXISTE en la tabla ALUMNOS.");
        } finally {
            ResultSet rstAux = sentencia.executeQuery("SELECT * FROM ALUMNOS WHERE codigo_alumno = '" + codigo + "'");
            while (rstAux.next()) {
                System.out.println("\nLos DATOS del ALUMNO son:");
                System.out.println("\tIdal: " + rstAux.getInt("idal"));
                System.out.println("\tCódigo_alumno: " + rstAux.getString("codigo_alumno"));
                System.out.println("\tNombre: " + rstAux.getString("nombre"));
            }
            rstAux.close();
        }

    }

    private static void asignaturas(Scanner input, Statement sentencia) throws SQLException {

        String codigo = null;

        try {

            System.out.println("NUEVA ASIGNATURA");
            System.out.println("Introduzca el CÓDIGO de la asignatura:");
            codigo = ControlData.leerCodigo(input);
            System.out.println("Introduzca el NOMBRE de la asignatura:");
            String nombre = ControlData.leerString(input);
            sentencia.executeUpdate("INSERT INTO ASIGNATURAS (codigo_asignatura, nombre_ciclo) VALUES ('" + codigo + "','" + nombre + "')");
            System.out.println("\nASIGNATURA AÑADIDA");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("\nERROR. No se puede registar el CÓDIGO " + codigo + ".\nEl código que intenta introducir YA EXISTE en la tabla ASIGNATURAS.");
        } finally {
            ResultSet rstAux = sentencia.executeQuery("SELECT * FROM ASIGNATURAS WHERE codigo_asignatura = '" + codigo + "'");
            while (rstAux.next()) {
                System.out.println("\nLos DATOS de la ASIGNATURA son:");
                System.out.println("\tIdas: " + rstAux.getInt("idas"));
                System.out.println("\tCódigo_asignatura: " + rstAux.getString("codigo_asignatura"));
                System.out.println("\tNombre: " + rstAux.getString("nombre_ciclo"));
            }
            
            rstAux.close();
        }

    }

    private static void notas(Scanner input, Statement sentencia) throws SQLException {

        boolean existeAlumno = false;
        boolean existeAsignatura = false;
        boolean existeNota = false;

        System.out.println("NUEVA NOTA");

        System.out.println("Introduzca el CÓDIGO del alumno:");
        String codigoAlumno = ControlData.leerString(input);
        ResultSet rstAux = sentencia.executeQuery("SELECT * FROM ALUMNOS");
        while (rstAux.next()) {
            if (codigoAlumno.equalsIgnoreCase(rstAux.getString("codigo_alumno"))) {
                existeAlumno = true;
            }
        }
        if (existeAlumno) {//Si el alumno está registrado en la tabla ALUMNOS, se busca su IDAL para añadirlo a la tabla NOTAS
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
            if (existeAsignatura) {//Si la asignatura está registrada en la tabla ASIGNATURAS, se busca su IDAS para añadirlo a la tabla NOTAS
                int idas = 0;
                rstAux = sentencia.executeQuery("SELECT * FROM ASIGNATURAS WHERE codigo_asignatura = '" + codigoAsignatura + "'");
                while (rstAux.next()) {
                    idas = rstAux.getInt("idas");
                }
                System.out.println("Introduzca la FECHA (aaaa-mm-dd):");
                String fecha = ControlData.leerFecha(input);
                rstAux = sentencia.executeQuery("SELECT * FROM NOTAS");
                while (rstAux.next()) {
                    if (idal == rstAux.getInt("idal") && idas == rstAux.getInt("idas") && fecha.equals(rstAux.getString("fecha"))) {
                        existeNota = true;
                    }
                }
                if (!existeNota) {
                    System.out.println("Introduzca la NOTA:");
                    Float nota = ControlData.leerFloat(input);
                    sentencia.executeUpdate("INSERT INTO NOTAS (idal, idas, fecha, nota) VALUES (" + idal + "," + idas + ",'" + fecha + "'," + nota + ");");
                    System.out.println("\nLOS DATOS HAN SIDO REGISTRADOS CORRECTAMENTE.");
                } else if (existeNota) {
                    System.out.println("\nERROR.No es posible añadir la nota.\nEl alumno " + codigoAlumno + " ya tiene una nota asignada en la asignatura " + codigoAsignatura + " a fecha de " + fecha + ".");
                    rstAux = sentencia.executeQuery("SELECT CONCAT(A.codigo_alumno,' ', A.nombre) as Alumno , CONCAT(AG.codigo_asignatura,' ',AG.nombre_ciclo) as Asignatura , N.fecha , N.nota from ALUMNOS A JOIN NOTAS N USING (idal) JOIN ASIGNATURAS AG USING(idas) WHERE A.idal=" + idal + " AND AG.idas=" + idas + " AND fecha='" + fecha + "';");
                    while (rstAux.next()) {
                        System.out.println("\nNOTA YA REGISTRADA");
                        System.out.println("\tAlumno: " + rstAux.getString("Alumno"));
                        System.out.println("\tAsignatura: " + rstAux.getString("Asignatura"));
                        System.out.println("\tFecha: " + rstAux.getDate("N.fecha"));
                        System.out.println("\tNota: " + rstAux.getFloat("N.nota"));
                    }
                }
            } else if (!existeAsignatura) {//Si la asignatura no está registrada en ASIGNATURAS, no se podrá añadir a la tabla NOTAS
                System.out.println("ERROR.No es posible añadir la asignatura con CÓDIGO " + codigoAsignatura + " a la tabla ya que no está registrada en ASIGNATURAS.");
            }
        } else if (!existeAlumno) {//Si el alumno no está registrado en ALUMNOS, no se podrá añadir a la tabla NOTAS
            System.out.println("ERROR.No es posible añadir el alumno con CÓDIGO " + codigoAlumno + " a la tabla ya que no está registrado en ALUMNOS.");
        }
        
        rstAux.close();

    }

    private static void alumnoAsignaturaProfesor(Scanner input, Statement sentencia) throws SQLException {

        boolean existeAlumno = false;
        boolean existeAsignatura = false;
        boolean existeProfesor = false;

        boolean existeAlumnoAsignatura = false;
        boolean existeAsignaturaProfesor = false;

        boolean existeAsignaturaEnTablaRelacional = false;

        System.out.println("NUEVA FILA ALUMNO + ASIGNATURA + PROFESOR");

        System.out.println("Introduzca el CÓDIGO del alumno:");
        String codigoAlumno = ControlData.leerString(input);
        ResultSet rstAux = sentencia.executeQuery("SELECT * FROM ALUMNOS");
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
                            System.out.println("\nLOS DATOS HAN SIDO REGISTRADOS CORRECTAMENTE.");
                        } else if (existeAsignaturaEnTablaRelacional) {//Si la asignatura ya está en la tabla relacional...
                            rstAux = sentencia.executeQuery("SELECT * FROM ALUMNOSASIGNATURASPROFESORES");
                            while (rstAux.next()) {
                                if (idas == rstAux.getInt("idas") && dni.equalsIgnoreCase(rstAux.getString("dni_profesor"))) {
                                    existeAsignaturaProfesor = true;
                                }
                            }
                            if (existeAsignaturaProfesor) {//Si ya hay en la tabla una asignatura con ese código asignada a ese profesor, se podrá asignar al nuevo alumno.
                                sentencia.executeUpdate("INSERT INTO ALUMNOSASIGNATURASPROFESORES (idal, idas, dni_profesor) VALUES (" + idal + "," + idas + ",'" + dni + "')");
                                System.out.println("\nLOS DATOS HAN SIDO REGISTRADOS CORRECTAMENTE.");
                            } else if (!existeAsignaturaProfesor) {//Si ya está la asignatura en la tabla y no está asociada a ese profesor, no se podrá asociar.
                                rstAux = sentencia.executeQuery("SELECT nombre_ciclo FROM ASIGNATURAS WHERE codigo_asignatura = '" + codigoAsignatura + "'");
                                String asig = null;
                                while (rstAux.next()) {
                                    asig = rstAux.getString("nombre_ciclo");
                                }
                                System.out.println("\nERROR.No es posible asignar al profesor con DNI " + dni + " la asignatura de " + asig + " (CÓDIGO " + codigoAsignatura + "), ya que esta la imparte otro profesor.");
                                rstAux = sentencia.executeQuery("SELECT AAP.dni_profesor FROM ALUMNOSASIGNATURASPROFESORES AAP JOIN ASIGNATURAS A USING(idas) WHERE A.codigo_asignatura ='" + codigoAsignatura + "' GROUP BY AAP.dni_profesor;");
                                String prof = null;
                                while (rstAux.next()) {
                                    prof = rstAux.getString("AAP.dni_profesor");
                                }
                                System.out.println("\nASIGNATURA + PROFESOR");
                                System.out.println("\tAsignatura: " + codigoAsignatura + " " + asig);
                                System.out.println("\tProfesor(DNI): " + prof);
                            }

                        }

                    } else if (!existeProfesor) {//Si el profesor no está registrado en PROFESORES, no se podrá añadir a la tabla relacional
                        System.out.println("\nERROR.No es posible añadir al profesor con DNI " + dni + " a la tabla ya que no está registrada en PROFESORES.");
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
                    System.out.println("\nERROR.No es posible matricular al alumno.\nEl alumno " + nomAl + "(CÓDIGO " + codigoAlumno + ") ya está matriculado en " + nomAs + "(CÓDIGO " + codigoAsignatura + ").");
                    System.out.println("\nALUMNO + ASIGNATURA + PROFESOR:");
                    System.out.println("\tCódigo_alumno: " + codigoAlumno);
                    System.out.println("\tCódigo_asignatura: " + codigoAsignatura);
                    System.out.println("\tProfesor: " + dniProf);

                }

            } else if (!existeAsignatura) {//Si la asignatura no está registrada en ASIGNATURAS, no se podrá añadir a la tabla relacional
                System.out.println("\nERROR.No es posible añadir la asignatura con CÓDIGO " + codigoAsignatura + " a la tabla ya que no está registrada en ASIGNATURAS.");
            }

        } else if (!existeAlumno) {//Si el alumno no está registrado en ALUMNOS, no se podrá añadir a la tabla relacional
            System.out.println("\nERROR.No es posible añadir el alumno con CÓDIGO " + codigoAlumno + " a la tabla ya que no está registrado en ALUMNOS.");
        }
        
        rstAux.close();

    }

}
