/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.DaoImpl;

import CRUD.Conexion.Conexion;
import CRUD.IDao.IProfesorDao;
import CRUD.Modelo.Profesor;
import java.sql.Connection;
import java.sql.Date;
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
public class ProfesorDaoImpl implements IProfesorDao {

    @Override
    public boolean existe(Profesor profesor) {
        boolean existe = false;

        Statement sentencia = null;
        Connection conexion = null;
        ResultSet rstAux = null;

        String sql = "SELECT * FROM PROFESORES";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                if (profesor.getDni().equalsIgnoreCase(rstAux.getString("dni"))) {
                    existe = true;
                }
            }
            sentencia.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error: Clase ProfesorDaoImple, método existe");
            e.printStackTrace();
        }
        return existe;
    }

    @Override
    public boolean registrar(Profesor profesor) {
        boolean registrar = false;

        Statement sentencia = null;
        Connection conexion = null;

        String sql = "INSERT INTO PROFESORES (dni, nombre, titulacion) VALUES ('" + profesor.getDni() + "','" + profesor.getNombre() + "','" + profesor.getTitulacion() + "')";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            registrar = true;
            sentencia.close();
            conexion.close();

            System.out.println("\nPROFESOR AÑADIDO");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("\nERROR. No se puede registar el DNI " + profesor.getDni() + ".\nEl dni que intenta introducir YA EXISTE en la tabla PROFESORES.");
        } catch (SQLException e) {
            System.out.println(e + "Error: Clase ProfesorDaoImple, método registrar");
            e.printStackTrace();
        }
        return registrar;
    }

    @Override
    public List<Profesor> obtenerTodos() {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        String sql = "SELECT * FROM PROFESORES ORDER BY dni";

        List<Profesor> listaProfesores = new ArrayList<Profesor>();

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                Profesor p = new Profesor();
                p.setDni(rstAux.getString(1));
                p.setNombre(rstAux.getString(2));
                p.setTitulacion(rstAux.getString(3));
                listaProfesores.add(p);
            }
            sentencia.close();
            rstAux.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ProfesorDaoImple, método obtenerTodos");
            e.printStackTrace();
        }

        return listaProfesores;
    }

    @Override
    public Profesor obtener(Profesor profesor) {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        Profesor prof = new Profesor();

        String sql = "SELECT * FROM PROFESORES WHERE dni='" + profesor.getDni() + "';";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                prof.setDni(rstAux.getString(1));
                prof.setNombre(rstAux.getString(2));
                prof.setTitulacion(rstAux.getString(3));
            }
            sentencia.close();
            rstAux.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ProfesorDaoImple, método obtener");
            e.printStackTrace();
        }

        return prof;
    }

    @Override
    public boolean actualizar(Profesor profesor) {
        Connection conexion = null;
        Statement sentencia = null;

        boolean actualizar = false;

        String sql = "UPDATE PROFESORES SET nombre='" + profesor.getNombre() + "', titulacion='" + profesor.getTitulacion() + "' WHERE dni='" + profesor.getDni() + "'";
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            actualizar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase ProfesorDaoImple, método actualizar");
            e.printStackTrace();
        }
        return actualizar;
    }

    @Override
    public boolean eliminar(Profesor profesor) {
        Connection conexion = null;
        Statement sentencia = null;

        boolean eliminar = false;

        String sql = "DELETE FROM PROFESORES WHERE dni='" + profesor.getDni() + "'";
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            eliminar = true;
            System.out.println("\nPROFESOR ELIMINADO.");
        } catch (SQLException e) {
            System.out.println("Error: Clase ProfesorDaoImple, método eliminar");
            e.printStackTrace();
        }
        return eliminar;
    }

    @Override
    public void verAsignaturas(Profesor profesor) {

        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        String sql = "SELECT AG.codigo_asignatura, AG.nombre_ciclo FROM PROFESORES P JOIN ALUMNOSASIGNATURASPROFESORES AAP ON P.dni=AAP.dni_profesor JOIN ASIGNATURAS AG USING(idas) WHERE P.dni='" + profesor.getDni() + "' GROUP BY AG.codigo_asignatura;";


        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            System.out.println("\nPROFESOR " + profesor.getNombre().toUpperCase() + ", DNI " + profesor.getDni().toUpperCase() + ", TITULACION " + profesor.getTitulacion().toUpperCase()+"\n\n"
                    + "ASIGNATURAS:\n"
                    + "CÓDIGO\t\tNOMBRE\n"
                    + "-----------------------------------------------------");
            while (rstAux.next()) {
                System.out.print(rstAux.getString("AG.codigo_asignatura") + "\t\t");
                System.out.print(rstAux.getString("AG.nombre_ciclo") + "\n");
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
