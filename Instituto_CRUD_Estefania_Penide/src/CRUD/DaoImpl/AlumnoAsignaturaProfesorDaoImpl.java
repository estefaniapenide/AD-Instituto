/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.DaoImpl;

import CRUD.Conexion.Conexion;
import CRUD.IDao.IAlumnoAsignaturaProfesorDao;
import CRUD.Modelo.Alumno;
import CRUD.Modelo.AlumnoAsignaturaProfesor;
import CRUD.Modelo.Asignatura;
import CRUD.Modelo.Profesor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author a20estefaniapc
 */
public class AlumnoAsignaturaProfesorDaoImpl implements IAlumnoAsignaturaProfesorDao{
    
    @Override
    public boolean registrar(AlumnoAsignaturaProfesor aap) {
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
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase AlumnoAsignaturaProfesorDaoImple, método registrar");
            e.printStackTrace();
        }
        return registrar;
    }

    @Override
    public List<AlumnoAsignaturaProfesor> obtenerTodos() {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        String sql = "SELECT * FROM ALUMNOSASIGNATURASPROFESORES ORDER BY idal";

        List<AlumnoAsignaturaProfesor> listaAAPs = new ArrayList<AlumnoAsignaturaProfesor>();

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                AlumnoAsignaturaProfesor aap = new AlumnoAsignaturaProfesor();
                
                Alumno a = new Alumno();
                a.setIdal(rstAux.getInt(1));
                Asignatura ag= new Asignatura();
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
	public boolean actualizar(AlumnoAsignaturaProfesor aap) {
		Connection conexion= null;
		Statement sentencia= null;
		
		boolean actualizar=false;
				
		String sql="UPDATE ALUMNOSASIGNATURASPROFESORES SET dni_profesor='"+aap.getProfesor().getDni()+" WHERE idal="+aap.getAlumno().getIdal()+" AND idas="+aap.getAsignatura().getIdas();
		try {
			conexion=Conexion.conexionMySQL();
			sentencia=conexion.createStatement();
                        sentencia.execute("USE BDINSTITUTO");
			sentencia.execute(sql);
			actualizar=true;
		} catch (SQLException e) {
			System.out.println("Error: Clase AlumnoAsignaturaProfesorDaoImple, método actualizar");
			e.printStackTrace();
		}		
		return actualizar;
	}
        
        @Override
	public boolean eliminar(AlumnoAsignaturaProfesor aap) {
		Connection conexion= null;
		Statement sentencia= null;
		
		boolean eliminar=false;
				
		String sql="DELETE FROM ALUMNOSASIGNATURASPROFESORES WHERE idal="+aap.getAlumno().getIdal()+" AND idas="+aap.getAsignatura().getIdas();
		try {
			conexion=Conexion.conexionMySQL();
			sentencia=conexion.createStatement();
                        sentencia.execute("USE BDINSTITUTO");
			sentencia.execute(sql);
			eliminar=true;
		} catch (SQLException e) {
			System.out.println("Error: Clase AlumnoAsignaturaProfesorDaoImple, método eliminar");
			e.printStackTrace();
		}		
		return eliminar;
	}
    
}
