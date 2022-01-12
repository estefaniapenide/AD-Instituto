package instituto_crud_estefania_penide;

import controldata.ControlData;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Estefania
 */
public class Modificaciones {

    public static void notaAlumno(Scanner input, Statement sentencia) {

        try {

            boolean existeAlumno = false;
            boolean existeAsignatura = false;
            boolean existeAsignaturaAlumno = false;
            boolean existeNota = false;

            System.out.println("MODIFICAR NOTA DE ALUMNO");

            System.out.println("Introduzca el CÓDIGO del alumno:");
            String codigoAlumno = ControlData.leerString(input);
            ResultSet rstAux = sentencia.executeQuery("SELECT * FROM ALUMNOS");
            while (rstAux.next()) {
                if (codigoAlumno.equalsIgnoreCase(rstAux.getString("codigo_alumno"))) {
                    existeAlumno = true;
                }
            }
            if (existeAlumno) {
                rstAux = sentencia.executeQuery("SELECT idal, nombre FROM ALUMNOS WHERE codigo_alumno='" + codigoAlumno + "'");
                int idal = 0;
                String nombre = null;
                while (rstAux.next()) {
                    idal = rstAux.getInt("idal");
                    nombre = rstAux.getString("nombre");
                }
                System.out.println("\nNOTAS DEL ALUMNO " + nombre.toUpperCase() + ":\n");
                rstAux = sentencia.executeQuery("SELECT CONCAT(AG.codigo_asignatura,' ' ,AG.nombre_ciclo) AS Asignatura ,N.fecha ,N.nota FROM ALUMNOS A JOIN NOTAS N USING(idal) JOIN ASIGNATURAS AG USING(idas) WHERE A.codigo_alumno='" + codigoAlumno + "';");
                System.out.println("ASIGNATURA\t\tFECHA\t\tNOTA");
                System.out.println("-----------------------------------------------------");
                while (rstAux.next()) {
                    System.out.print(rstAux.getString("Asignatura") + "\t");
                    System.out.print(rstAux.getDate("N.fecha") + "\t");
                    System.out.print(rstAux.getFloat("N.nota") + "\n");
                }
                System.out.println("\nIntroduzca el CÓDIGO de la asignatura de la nota a modificar:");
                String codigoAsignatura = ControlData.leerString(input);
                rstAux = sentencia.executeQuery("SELECT * FROM ASIGNATURAS");
                while (rstAux.next()) {
                    if (codigoAsignatura.equalsIgnoreCase(rstAux.getString("codigo_asignatura"))) {
                        existeAsignatura = true;
                    }
                }
                if (existeAsignatura) {
                    rstAux = sentencia.executeQuery("SELECT idas FROM ASIGNATURAS WHERE codigo_asignatura='" + codigoAsignatura + "';");
                    int idas = 0;
                    while (rstAux.next()) {
                        idas = rstAux.getInt("idas");
                    }
                    rstAux = sentencia.executeQuery("SELECT * FROM NOTAS ");
                    while (rstAux.next()) {
                        if (idal == rstAux.getInt("idal") && idas == rstAux.getInt("idas")) {
                            existeAsignaturaAlumno = true;
                        }
                    }
                    if (existeAsignaturaAlumno) {
                        System.out.println("Introduzca la FECHA de la nota a modificar (aaaa-mm-dd):");
                        String fecha = ControlData.leerString(input);
                        rstAux = sentencia.executeQuery("SELECT * FROM NOTAS ");
                        while (rstAux.next()) {
                            if (idal == rstAux.getInt("idal") && idas == rstAux.getInt("idas") && fecha.equals(rstAux.getString("fecha"))) {
                                existeNota = true;
                            }
                        }
                        if (existeNota) {
                            System.out.println("\nNOTA ACTUAL DEL ALUMNO " + nombre.toUpperCase() + ":");
                            rstAux = sentencia.executeQuery("SELECT CONCAT(AG.codigo_asignatura,' ' ,AG.nombre_ciclo) AS Asignatura ,N.fecha ,N.nota FROM ALUMNOS A JOIN NOTAS N USING(idal) JOIN ASIGNATURAS AG USING(idas) WHERE A.codigo_alumno='" + codigoAlumno + "' AND AG.codigo_asignatura='" + codigoAsignatura + "' AND N.fecha='" + fecha + "';");
                            System.out.println("ASIGNATURA\t\tFECHA\t\tNOTA");
                            System.out.println("-----------------------------------------------------");
                            while (rstAux.next()) {
                                System.out.print(rstAux.getString("Asignatura") + "\t");
                                System.out.print(rstAux.getDate("N.fecha") + "\t");
                                System.out.print(rstAux.getFloat("N.nota") + "\n");
                            }
                            System.out.println("\nIntroduzca la NUEVA NOTA:");
                            Float nota = ControlData.leerFloat(input);
                            sentencia.executeUpdate("UPDATE NOTAS SET nota = " + nota + " WHERE fecha = '" + fecha + "' AND idas='" + idas + "' AND idal='" + idal + "';");
                            System.out.println("\nLA NOTA HA SIDO MODIFICADA CORRECTAMENTE.");
                        } else if (!existeNota) {
                            System.out.println("\nERROR. El alumno " + codigoAlumno + " no tiene ninguna nota en la asignatura " + codigoAsignatura + " a fecha de " + fecha+".");
                        }
                    } else if (!existeAsignaturaAlumno) {
                        System.out.println("\nERROR. El alumno " + codigoAlumno + " no está matriculado en la asignatura " + codigoAsignatura+".");
                    }
                } else if (!existeAsignatura) {
                    System.out.println("\nERROR.La asignatura " + codigoAsignatura + " no está registrada.");
                }
            } else if (!existeAlumno) {
                System.out.println("\nERROR.El alumno " + codigoAlumno + " no está registrado.");
            }
            
            rstAux.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
