/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.DaoImpl;

import CRUD.Conexion.Conexion;
import CRUD.IDao.IAsignaturaDao;
import CRUD.Modelo.Asignatura;
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
public class AsignaturaDaoImpl implements IAsignaturaDao {
    
        @Override
    public boolean existe(Asignatura asignatura) {
        boolean existe = false;

        Statement sentencia = null;
        Connection conexion = null;
        ResultSet rstAux = null;

        String sql = "SELECT * FROM ASIGNATURAS";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux=sentencia.executeQuery(sql);
            while (rstAux.next()) {
                if (asignatura.getCodigo_asignatura().equalsIgnoreCase(rstAux.getString("codigo_asignatura"))) {
                    existe = true;
                }
            }
            sentencia.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error: Clase AsignaturaDaoImple, método existe");
            e.printStackTrace();
        }
        return existe;
    }

    @Override
    public boolean registrar(Asignatura asignatura) {
        boolean registrar = false;

        Statement sentencia = null;
        Connection conexion = null;

        String sql = "INSERT INTO ASIGNATURAS (codigo_asignatura, nombre_ciclo) VALUES ('" + asignatura.getCodigo_asignatura() + "','" + asignatura.getNombre_ciclo() + "')";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            registrar = true;
           
            sentencia.close();
            conexion.close();
            
             System.out.println("\nASIGNATURA AÑADIDA");
             
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("\nERROR. No se puede registar el CÓDIGO " + asignatura.getCodigo_asignatura() + ".\nEl código que intenta introducir YA EXISTE en la tabla ASIGNATURAS.");
        } catch (SQLException e) {
            System.out.println("Error: Clase AsignaturaDaoImple, método registrar");
            e.printStackTrace();
        }
        return registrar;
    }

    @Override
    public List<Asignatura> obtenerTodos() {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        String sql = "SELECT * FROM ASIGNATURAS ORDER BY idas";

        List<Asignatura> listaAsignaturas = new ArrayList<Asignatura>();

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                Asignatura a = new Asignatura();
                a.setIdas(rstAux.getInt(1));
                a.setCodigo_asignatura(rstAux.getString(2));
                a.setNombre_ciclo(rstAux.getString(3));
                listaAsignaturas.add(a);
            }
            sentencia.close();
            rstAux.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase AsignaturaDaoImple, método obtenerTodos");
            e.printStackTrace();
        }

        return listaAsignaturas;
    }

    @Override
    public Asignatura obtener(Asignatura asignatura) {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        Asignatura asig = new Asignatura();

        String sql = "SELECT * FROM ASIGNATURAS WHERE codigo_asignatura='" + asignatura.getCodigo_asignatura() + "';";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                asig.setIdas(rstAux.getInt(1));
                asig.setCodigo_asignatura(rstAux.getString(2));
                asig.setNombre_ciclo(rstAux.getString(3));
            }
            sentencia.close();
            rstAux.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase AsignaturaDaoImple, método obtener");
            e.printStackTrace();
        }

        return asig;
    }

    @Override
    public boolean actualizar(Asignatura asignatura) {
        Connection conexion = null;
        Statement sentencia = null;

        boolean actualizar = false;

        String sql = "UPDATE ASIGNATURAS SET codigo_asignatura='" + asignatura.getCodigo_asignatura() + "', nombre_ciclo='" + asignatura.getNombre_ciclo() + "' WHERE idas=" + asignatura.getIdas();
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            actualizar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase AsignaturaDaoImple, método actualizar");
            e.printStackTrace();
        }
        return actualizar;
    }

    @Override
    public boolean eliminar(Asignatura asignatura) {
        Connection conexion = null;
        Statement sentencia = null;

        boolean eliminar = false;

        String sql = "DELETE FROM ASIGNATURAS WHERE idas=" + asignatura.getIdas();
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            eliminar = true;
            System.out.println("\nASIGNATURA ELIMINADA.");
        } catch (SQLException e) {
            System.out.println("Error: Clase AsignaturaDaoImple, método eliminar");
            e.printStackTrace();
        }
        return eliminar;
    }
    
    @Override
    public void verProfesor(Asignatura asignatura){
           
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        String sql = "SELECT CONCAT(AG.codigo_asignatura,' ',AG.nombre_ciclo) AS Asignatura, P.nombre, P.dni FROM ASIGNATURAS AG LEFT JOIN ALUMNOSASIGNATURASPROFESORES AAP USING(idas) LEFT JOIN PROFESORES P ON P.dni=AAP.dni_profesor WHERE AG.codigo_asignatura='" + asignatura.getCodigo_asignatura() + "' GROUP BY AG.idas;";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            System.out.println("\nASIGNATURA\t\tPROFSEOR\t\tDNI\n"
                    + "----------------------------------------------------------");
            while (rstAux.next()) {
                System.out.print(rstAux.getString("Asignatura") + "\t");
                System.out.print(rstAux.getString("P.nombre") + "\t\t");
                System.out.print(rstAux.getString("P.dni") + "\n");
            }

            sentencia.close();
            rstAux.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase AsignaturaDaoImple, método verProfesor(Asignatura asignatura)");
            e.printStackTrace();
        }
    }

}
