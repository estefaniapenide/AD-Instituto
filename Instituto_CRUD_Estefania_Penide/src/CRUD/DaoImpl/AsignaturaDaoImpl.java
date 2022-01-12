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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estefania
 */
public class AsignaturaDaoImpl implements IAsignaturaDao{
    
    @Override
    public boolean registrar(Asignatura asignatura) {
        boolean registrar = false;

        Statement sentencia = null;
        Connection conexion = null;

        String sql = "INSERT INTO ASIGNATURAS (idas, codigo_asignatura, nombre_ciclo) VALUES (" + asignatura.getIdas() + ",'" + asignatura.getCodigo_asignatura() + "','" + asignatura.getNombre_ciclo() + "')";

        try {
            conexion = Conexion.conexionBD();
            sentencia = conexion.createStatement();
            sentencia.execute(sql);
            registrar = true;
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase AsignaturaDaoImple, método registrar");
            e.printStackTrace();
        }
        return registrar;
    }

    @Override
    public List<Asignatura> obtener() {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        String sql = "SELECT * FROM ASIGNATURAS ORDER BY idas";

        List<Asignatura> listaAsignaturas = new ArrayList<Asignatura>();

        try {
            conexion = Conexion.conexionBD();
            sentencia = conexion.createStatement();
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
            System.out.println("Error: Clase AsignaturaDaoImple, método obtener");
            e.printStackTrace();
        }

        return listaAsignaturas;
    }
    
    @Override
	public boolean actualizar(Asignatura asignatura) {
		Connection conexion= null;
		Statement sentencia= null;
		
		boolean actualizar=false;
				
		String sql="UPDATE ASIGNATURAS SET codigo_asignatura='"+asignatura.getCodigo_asignatura()+"', nombre_ciclo='"+asignatura.getNombre_ciclo()+"' WHERE idas="+asignatura.getIdas();
		try {
			conexion=Conexion.conexionBD();
			sentencia=conexion.createStatement();
			sentencia.execute(sql);
			actualizar=true;
		} catch (SQLException e) {
			System.out.println("Error: Clase AsignaturaDaoImple, método actualizar");
			e.printStackTrace();
		}		
		return actualizar;
	}
        
        @Override
	public boolean eliminar(Asignatura asignatura) {
		Connection conexion= null;
		Statement sentencia= null;
		
		boolean eliminar=false;
				
		String sql="DELETE FROM ASIGNATURAS WHERE idas="+asignatura.getIdas();
		try {
			conexion=Conexion.conexionBD();
			sentencia=conexion.createStatement();
			sentencia.execute(sql);
			eliminar=true;
		} catch (SQLException e) {
			System.out.println("Error: Clase AsignaturaDaoImple, método eliminar");
			e.printStackTrace();
		}		
		return eliminar;
	}

    
}
