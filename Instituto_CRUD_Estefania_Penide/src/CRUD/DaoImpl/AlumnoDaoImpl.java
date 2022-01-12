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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estefania
 */
public class AlumnoDaoImpl implements IAlumnoDao {

    @Override
    public boolean registrar(Alumno alumno) {
        boolean registrar = false;

        Statement sentencia = null;
        Connection conexion = null;

        String sql = "INSERT INTO ALUMNOS (idal, codigo_alumno, nombre) VALUES (" + alumno.getIdal() + ",'" + alumno.getCodigo_alumno() + "','" + alumno.getNombre() + "')";

        try {
            conexion = Conexion.conexionBD();
            sentencia = conexion.createStatement();
            sentencia.execute(sql);
            registrar = true;
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase AlumnoDaoImple, método registrar");
            e.printStackTrace();
        }
        return registrar;
    }

    @Override
    public List<Alumno> obtener() {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        String sql = "SELECT * FROM ALUMNOS ORDER BY idal";

        List<Alumno> listaAlumnos = new ArrayList<Alumno>();

        try {
            conexion = Conexion.conexionBD();
            sentencia = conexion.createStatement();
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
            System.out.println("Error: Clase AlumnoDaoImple, método obtener");
            e.printStackTrace();
        }

        return listaAlumnos;
    }
    
    @Override
	public boolean actualizar(Alumno alumno) {
		Connection conexion= null;
		Statement sentencia= null;
		
		boolean actualizar=false;
				
		String sql="UPDATE ALUMNOS SET codigo_alumno='"+alumno.getCodigo_alumno()+"', nombre='"+alumno.getNombre()+"' WHERE idal="+alumno.getIdal();
		try {
			conexion=Conexion.conexionBD();
			sentencia=conexion.createStatement();
			sentencia.execute(sql);
			actualizar=true;
		} catch (SQLException e) {
			System.out.println("Error: Clase AlumnoDaoImple, método actualizar");
			e.printStackTrace();
		}		
		return actualizar;
	}
        
        @Override
	public boolean eliminar(Alumno alumno) {
		Connection conexion= null;
		Statement sentencia= null;
		
		boolean eliminar=false;
				
		String sql="DELETE FROM ALUMNOS WHERE idal="+alumno.getIdal();
		try {
			conexion=Conexion.conexionBD();
			sentencia=conexion.createStatement();
			sentencia.execute(sql);
			eliminar=true;
		} catch (SQLException e) {
			System.out.println("Error: Clase AlumnoDaoImple, método eliminar");
			e.printStackTrace();
		}		
		return eliminar;
	}

}
