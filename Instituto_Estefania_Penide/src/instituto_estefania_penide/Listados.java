
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

    public static void consultas(Scanner input, Statement sentencia) {

        try {
            byte op = 0;
            do {
                Menus.menuListados();
                op = ControlData.leerByte(input);

                switch (op) {
                    case 1:
                        listadoProfesor(input, sentencia);
                        break;
                    case 2:
                        listadoAlumno(input, sentencia);
                        break;
                    case 3:
                        listadoAsignatura(input, sentencia);
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

    private static void listadoProfesor(Scanner input, Statement sentencia) throws SQLException {

        boolean existe = false;

        System.out.println("ASIGNATURAS DE UN PROFESOR");
        System.out.println("Introduzca el DNI del profesor:");
        String dni = ControlData.leerString(input);
        ResultSet rstAux = sentencia.executeQuery("SELECT * FROM PROFESORES");
        while (rstAux.next()) {
            if (dni.equalsIgnoreCase(rstAux.getString("dni"))) {
                existe = true;
            }
        }
        if (existe) {
            rstAux = sentencia.executeQuery("SELECT nombre, titulacion FROM PROFESORES WHERE dni='" + dni + "';");
            String nombre = null;
            String titulacion = null;
            while (rstAux.next()) {
                nombre = rstAux.getString("nombre");
                titulacion = rstAux.getString("titulacion");
            }
            System.out.println("\nPROFESOR " + nombre.toUpperCase() + ", DNI " + dni.toUpperCase() + ", TITULACION " + titulacion.toUpperCase());
            System.out.println("ASISNATURAS:");
            rstAux = sentencia.executeQuery("SELECT AG.codigo_asignatura, AG.nombre_ciclo FROM PROFESORES P JOIN ALUMNOSASIGNATURASPROFESORES AAP ON P.dni=AAP.dni_profesor JOIN ASIGNATURAS AG USING(idas) WHERE P.dni='" + dni + "' GROUP BY AG.codigo_asignatura;");
            System.out.println("CÓDIGO\t\tNOMBRE");
            System.out.println("-----------------------------------------------------");
            while (rstAux.next()) {
                System.out.print(rstAux.getString("AG.codigo_asignatura") + "\t\t");
                System.out.print(rstAux.getString("AG.nombre_ciclo") + "\n");
            }
        } else if (!existe) {
            System.out.println("\nERROR. El profesor con DNI " + dni + " no está registrado.");
        }

        rstAux.close();

    }

    private static void listadoAlumno(Scanner input, Statement sentencia) throws SQLException {

        boolean existe = false;

        System.out.println("NOTAS DE UN ALUMNO");
        System.out.println("Introduzca el CÓDIGO del alumno:");
        String codigoAlumno = ControlData.leerString(input);
        ResultSet rstAux = sentencia.executeQuery("SELECT * FROM ALUMNOS");
        while (rstAux.next()) {
            if (codigoAlumno.equalsIgnoreCase(rstAux.getString("codigo_alumno"))) {
                existe = true;
            }
        }
        if (existe) {
            rstAux = sentencia.executeQuery("SELECT idal, nombre FROM ALUMNOS WHERE codigo_alumno='" + codigoAlumno + "';");
            int idal = 0;
            String nombre = null;
            while (rstAux.next()) {
                idal = rstAux.getInt("idal");
                nombre = rstAux.getString("nombre");
            }
            System.out.println("\nALUMNO " + nombre.toUpperCase());
            System.out.println("NOTAS:");
            rstAux = sentencia.executeQuery("SELECT CONCAT (AG.codigo_asignatura,' ',AG.nombre_ciclo) AS Asignatura, N.fecha, N.nota FROM ASIGNATURAS AG JOIN NOTAS N USING(idas) JOIN ALUMNOS A USING(idal) WHERE A.idal=" + idal + " ORDER BY AG.codigo_asignatura;");
            System.out.println("ASIGNATURA\t\tFECHA\t\tNOTA");
            System.out.println("-----------------------------------------------------");
            while (rstAux.next()) {
                System.out.print(rstAux.getString("Asignatura") + "\t");
                System.out.print(rstAux.getString("N.fecha") + "\t");
                System.out.print(rstAux.getFloat("N.nota") + "\n");
            }
        } else if (!existe) {
            System.out.println("\nERROR. El alumno con CÓDIGO " + codigoAlumno + " no está registrado.");
        }

        rstAux.close();

    }

    private static void listadoAsignatura(Scanner input, Statement sentencia) throws SQLException {

        boolean existe = false;

        System.out.println("ASIGNATURA Y PROFESOR");
        System.out.println("Introduzca el CÓDIGO de la asignatura:");
        String codigoAsignatura = ControlData.leerString(input);
        ResultSet rstAux = sentencia.executeQuery("SELECT * FROM ASIGNATURAS");
        while (rstAux.next()) {
            if (codigoAsignatura.equalsIgnoreCase(rstAux.getString("codigo_asignatura"))) {
                existe = true;
            }
        }
        if (existe) {
            rstAux = sentencia.executeQuery("SELECT CONCAT(AG.codigo_asignatura,' ',AG.nombre_ciclo) AS Asignatura, P.nombre, P.dni FROM ASIGNATURAS AG LEFT JOIN ALUMNOSASIGNATURASPROFESORES AAP USING(idas) LEFT JOIN PROFESORES P ON P.dni=AAP.dni_profesor WHERE AG.codigo_asignatura='" + codigoAsignatura + "' GROUP BY AG.idas;");
            System.out.println("\nASIGNATURA\t\tPROFSEOR\t\tDNI");
            System.out.println("----------------------------------------------------------");
            while (rstAux.next()) {
                System.out.print(rstAux.getString("Asignatura") + "\t");
                System.out.print(rstAux.getString("P.nombre") + "\t\t");
                System.out.print(rstAux.getString("P.dni") + "\n");
            }
        } else if (!existe) {
            System.out.println("\nERROR. La asignatura con CÓDIGO " + codigoAsignatura + " no está registrada.");
        }

        rstAux.close();
    }
   

}
