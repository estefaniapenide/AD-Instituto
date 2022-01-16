/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.DaoImpl;

import CRUD.Conexion.Conexion;
import CRUD.Modelo.Alumno;
import CRUD.Modelo.Matricula;
import CRUD.Modelo.Asignatura;
import CRUD.Modelo.Profesor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import CRUD.IDao.IMatriculaDao;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author a20estefaniapc
 */
public class MatriculaDaoImpl implements IMatriculaDao {
    
    @Override
    public boolean existe(Matricula aap) {
        boolean existe = false;
        
        Statement sentencia = null;
        Connection conexion = null;
        ResultSet rstAux = null;
        
        String sql = "SELECT * FROM ALUMNOSASIGNATURASPROFESORES";
        
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                if (aap.getAlumno().getIdal() == rstAux.getInt("idal") && aap.getAsignatura().getIdas() == rstAux.getInt("idas")) {
                    existe = true;
                }
            }
            sentencia.close();
            conexion.close();
            
        } catch (SQLException e) {
            System.out.println("Error: Clase MatriculaDaoImple, método existe(Matricula aap)");
            e.printStackTrace();
        }
        return existe;
    }
    
    @Override
    public boolean existe(Asignatura asignatura) {
        boolean existe = false;
        Statement sentencia = null;
        Connection conexion = null;
        ResultSet rstAux = null;
        
        String sql = "SELECT * FROM ALUMNOSASIGNATURASPROFESORES";
        
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                if (asignatura.getIdas() == rstAux.getInt("idas")) {
                    existe = true;
                }
            }
            sentencia.close();
            conexion.close();
            
        } catch (SQLException e) {
            System.out.println("Error: Clase MatriculaDaoImple, método existe(Asignatura asignatura)");
            e.printStackTrace();
        }
        return existe;
    }
    
    @Override
    public boolean existe(Asignatura asignatura, Profesor profesor) {
        boolean existe = false;
        Statement sentencia = null;
        Connection conexion = null;
        ResultSet rstAux = null;
        
        String sql = "SELECT * FROM ALUMNOSASIGNATURASPROFESORES";
        
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                if (asignatura.getIdas() == rstAux.getInt("idas") && profesor.getDni().equalsIgnoreCase(rstAux.getString("dni_profesor"))) {
                    existe = true;
                }
            }
            sentencia.close();
            conexion.close();
            
        } catch (SQLException e) {
            System.out.println("Error: Clase MatriculaDaoImple, método existe(Asignatura asignatura, Profesor profesor)");
            e.printStackTrace();
        }
        return existe;
        
    }
    
    @Override
    public boolean registrar(Matricula aap) {
        boolean registrar = false;
        
        Statement sentencia = null;
        Connection conexion = null;
        
        String sql = "INSERT INTO ALUMNOSASIGNATURASPROFESORES (idal, idas, dni_profesor) VALUES (" + aap.getAlumno().getIdal() + "," + aap.getAsignatura().getIdas() + ",'" + aap.getProfesor().getDni() + "')";
        
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            registrar = true;
            System.out.println("\nMATRICULA AÑADIDA");
            sentencia.close();
            conexion.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("\nERROR.No es posible matricular al alumno.\nEl alumno " + aap.getAlumno().getNombre() + "(CÓDIGO " + aap.getAlumno().getCodigo_alumno() + ") ya está matriculado en " + aap.getAsignatura().getNombre_ciclo() + "(CÓDIGO " + aap.getAsignatura().getCodigo_asignatura() + ").");
        } catch (SQLException e) {
            System.out.println("Error: Clase AlumnoAsignaturaProfesorDaoImple, método registrar");
            e.printStackTrace();
        }
        return registrar;
    }
    
    @Override
    public List<Matricula> obtenerTodos() {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;
        
        String sql = "SELECT * FROM ALUMNOSASIGNATURASPROFESORES ORDER BY idal";
        
        List<Matricula> listaAAPs = new ArrayList<Matricula>();
        
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                Matricula aap = new Matricula();
                
                Alumno a = new Alumno();
                a.setIdal(rstAux.getInt(1));
                Asignatura ag = new Asignatura();
                ag.setIdas(rstAux.getInt(2));
                Profesor p = new Profesor();
                p.setDni(rstAux.getString(3));
                
                aap.setAlumno(a);
                aap.setAsignatura(ag);
                aap.setProfesor(p);
                listaAAPs.add(aap);
            }
            sentencia.close();
            rstAux.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase AlumnoAsignaturaProfesorDaoImple, método obtener");
            e.printStackTrace();
        }
        
        return listaAAPs;
    }
    
    @Override
    public Matricula obtener(Matricula aap) {
        
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;
        
        Matricula matricula = new Matricula();
        
        matricula.setAlumno(aap.getAlumno());
        matricula.setAsignatura(aap.getAsignatura());
        
        String sql = "SELECT * FROM ALUMNOSASIGNATURASPROFESORES WHERE idal=" + aap.getAlumno().getIdal() + " AND idas=" + aap.getAsignatura().getIdas() + ";";
        Profesor profesor = new Profesor();
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                profesor.setDni(rstAux.getString(3));
                //matricula.setProfesor(profesor);  
            }
            
            rstAux = sentencia.executeQuery("SELECT * FROM PROFESORES WHERE dni='" + profesor.getDni() + "';");
                while (rstAux.next()) {
                    profesor.setNombre(rstAux.getString(2));
                    profesor.setTitulacion(rstAux.getString(3));
                }
            
            matricula.setProfesor(profesor); 
            sentencia.close();
            rstAux.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase MatriculaDaoImple, método obtener(Matricula aap)");
            e.printStackTrace();
        }
        
        return matricula;
        
    }
    
    @Override
    public Profesor obtenerProfesor(Asignatura asignatura) {
        
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;
        
        Profesor profesor = new Profesor();
        
        String sql = "SELECT * FROM ALUMNOSASIGNATURASPROFESORES WHERE idas=" + asignatura.getIdas() + ";";
        
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                
                profesor.setDni(rstAux.getString(3));
                
            }
            sentencia.close();
            rstAux.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase MatriculaDaoImple, método obtenerProfesor(Asignatura asignatura)");
            e.printStackTrace();
        }
        
        return profesor;
    }
    
    @Override
    public boolean actualizar(Matricula aap) {
        Connection conexion = null;
        Statement sentencia = null;
        
        boolean actualizar = false;
        
        String sql = "UPDATE ALUMNOSASIGNATURASPROFESORES SET dni_profesor='" + aap.getProfesor().getDni() + " WHERE idal=" + aap.getAlumno().getIdal() + " AND idas=" + aap.getAsignatura().getIdas();
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            actualizar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase AlumnoAsignaturaProfesorDaoImple, método actualizar");
            e.printStackTrace();
        }
        return actualizar;
    }
    
    @Override
    public boolean eliminar(Matricula aap) {
        Connection conexion = null;
        Statement sentencia = null;
        
        boolean eliminar = false;
        
        String sql = "DELETE FROM ALUMNOSASIGNATURASPROFESORES WHERE idal=" + aap.getAlumno().getIdal() + " AND idas=" + aap.getAsignatura().getIdas();
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            eliminar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase AlumnoAsignaturaProfesorDaoImple, método eliminar");
            e.printStackTrace();
        }
        return eliminar;
    }
    
}
