/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.DaoImpl;

import CRUD.Conexion.Conexion;
import CRUD.Modelo.Alumno;
import CRUD.IDao.IAlumnoDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estefania
 */
public class AlumnoDaoImpl implements IAlumnoDao {

    @Override
    public boolean existe(Alumno alumno) {
        boolean existe = false;

        Statement sentencia = null;
        Connection conexion = null;
        ResultSet rstAux = null;

        String sql = "SELECT * FROM ALUMNOS";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                if (alumno.getCodigo_alumno().equalsIgnoreCase(rstAux.getString("codigo_alumno"))) {
                    existe = true;
                }
            }
            sentencia.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error: Clase AlumnoDaoImple, método existe");
            e.printStackTrace();
        }
        return existe;
    }

    @Override
    public boolean registrar(Alumno alumno) {
        boolean registrar = false;

        Statement sentencia = null;
        Connection conexion = null;

        String sql = "INSERT INTO ALUMNOS (codigo_alumno, nombre) VALUES ('" + alumno.getCodigo_alumno() + "','" + alumno.getNombre() + "')";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            registrar = true;
            sentencia.close();
            conexion.close();

            System.out.println("\nALUMNO AÑADIDO");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("\nERROR. No se puede registar el CÓDIGO " + alumno.getCodigo_alumno() + ".\nEl código que intenta introducir YA EXISTE en la tabla ALUMNOS.");
        } catch (SQLException e) {
            System.out.println("Error: Clase AlumnoDaoImple, método registrar");
            e.printStackTrace();
        }
        return registrar;
    }

    @Override
    public List<Alumno> obtenerTodos() {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        String sql = "SELECT * FROM ALUMNOS ORDER BY idal";

        List<Alumno> listaAlumnos = new ArrayList<Alumno>();

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                Alumno a = new Alumno();
                a.setIdal(rstAux.getInt(1));
                a.setCodigo_alumno(rstAux.getString(2));
                a.setNombre(rstAux.getString(3));
                listaAlumnos.add(a);
            }
            sentencia.close();
            rstAux.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase AlumnoDaoImple, método obtenerTodos");
            e.printStackTrace();
        }

        return listaAlumnos;
    }

    @Override
    public Alumno obtener(Alumno alumno) {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        Alumno a = new Alumno();

        String sql = "SELECT * FROM ALUMNOS WHERE codigo_alumno='" + alumno.getCodigo_alumno() + "';";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                a.setIdal(rstAux.getInt(1));
                a.setCodigo_alumno(rstAux.getString(2));
                a.setNombre(rstAux.getString(3));
            }
            sentencia.close();
            rstAux.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase AlumnoDaoImple, método obtener");
            e.printStackTrace();
        }

        return a;
    }

    @Override
    public boolean actualizar(Alumno alumno) {
        Connection conexion = null;
        Statement sentencia = null;

        boolean actualizar = false;

        String sql = "UPDATE ALUMNOS SET codigo_alumno='" + alumno.getCodigo_alumno() + "', nombre='" + alumno.getNombre() + "' WHERE idal=" + alumno.getIdal();
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            actualizar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase AlumnoDaoImple, método actualizar");
            e.printStackTrace();
        }
        return actualizar;
    }

    @Override
    public boolean eliminar(Alumno alumno) {
        Connection conexion = null;
        Statement sentencia = null;

        boolean eliminar = false;

        String sql = "DELETE FROM ALUMNOS WHERE codigo_alumno='" + alumno.getCodigo_alumno() + "'";
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            eliminar = true;

            System.out.println("\nALUMNO ELIMINADO");

        } catch (SQLException e) {
            System.out.println("Error: Clase AlumnoDaoImple, método eliminar");
            e.printStackTrace();
        }
        return eliminar;
    }

    @Override
    public void verNotas(Alumno alumno) {
        
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        String sql = "SELECT CONCAT(AG.codigo_asignatura,' ' ,AG.nombre_ciclo) AS Asignatura ,N.fecha ,N.nota FROM ALUMNOS A JOIN NOTAS N USING(idal) JOIN ASIGNATURAS AG USING(idas) WHERE A.codigo_alumno='" + alumno.getCodigo_alumno() + "';";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            System.out.println("\nNOTAS DEL ALUMNO " + alumno.getNombre().toUpperCase() + ":\n\n"
                    + "NOTAS:\n"
                    + "ASIGNATURA\t\tFECHA\t\tNOTA\n"
                    + "-----------------------------------------------------");
            while (rstAux.next()) {
                System.out.print(rstAux.getString("Asignatura") + "\t");
                System.out.print(rstAux.getDate("N.fecha") + "\t");;
                System.out.print(rstAux.getFloat("N.nota") + "\n");
            }

            sentencia.close();
            rstAux.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase NotasDaoImple, método ver(Alumno alumno)");
            e.printStackTrace();
        }
    }

}
