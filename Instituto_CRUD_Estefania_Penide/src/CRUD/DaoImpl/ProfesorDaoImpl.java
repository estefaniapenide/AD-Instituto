/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.DaoImpl;

import CRUD.Conexion.Conexion;
import CRUD.IDao.IProfesorDao;
import CRUD.Modelo.Profesor;
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
public class ProfesorDaoImpl implements IProfesorDao{
    
    @Override
    public boolean registrar(Profesor profesor) {
        boolean registrar = false;

        Statement sentencia = null;
        Connection conexion = null;

        String sql = "INSERT INTO PROFESORES (dni, nombre, titulacion) VALUES ('" + profesor.getDni() + "','" + profesor.getNombre() + "','" + profesor.getTitulacion() + "')";

        try {
            conexion = Conexion.conexionBD();
            sentencia = conexion.createStatement();
            sentencia.execute(sql);
            registrar = true;
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase ProfesorDaoImple, método registrar");
            e.printStackTrace();
        }
        return registrar;
    }

    @Override
    public List<Profesor> obtener() {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        String sql = "SELECT * FROM PROFESORES ORDER BY dni";

        List<Profesor> listaProfesores = new ArrayList<Profesor>();

        try {
            conexion = Conexion.conexionBD();
            sentencia = conexion.createStatement();
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
            System.out.println("Error: Clase ProfesorDaoImple, método obtener");
            e.printStackTrace();
        }

        return listaProfesores;
    }
    
    @Override
	public boolean actualizar(Profesor profesor) {
		Connection conexion= null;
		Statement sentencia= null;
		
		boolean actualizar=false;
				
		String sql="UPDATE PROFESORES SET nombre='"+profesor.getNombre()+"', titulacion='"+profesor.getTitulacion()+"' WHERE dni='"+profesor.getDni()+"'";
		try {
			conexion=Conexion.conexionBD();
			sentencia=conexion.createStatement();
			sentencia.execute(sql);
			actualizar=true;
		} catch (SQLException e) {
			System.out.println("Error: Clase ProfesorDaoImple, método actualizar");
			e.printStackTrace();
		}		
		return actualizar;
	}
        
        @Override
	public boolean eliminar(Profesor profesor) {
		Connection conexion= null;
		Statement sentencia= null;
		
		boolean eliminar=false;
				
		String sql="DELETE FROM PROFESORES WHERE dni='"+profesor.getDni()+"'";
		try {
			conexion=Conexion.conexionBD();
			sentencia=conexion.createStatement();
			sentencia.execute(sql);
			eliminar=true;
		} catch (SQLException e) {
			System.out.println("Error: Clase ProfesorDaoImple, método eliminar");
			e.printStackTrace();
		}		
		return eliminar;
	}
    
}
