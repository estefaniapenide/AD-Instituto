/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.DaoImpl;

import CRUD.Conexion.Conexion;
import CRUD.IDao.INotaDao;
import CRUD.Modelo.Alumno;
import CRUD.Modelo.Asignatura;
import CRUD.Modelo.Nota;
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
public class NotaDaoImpl implements INotaDao {

    @Override
    public boolean registrar(Nota nota) {
        boolean registrar = false;

        Statement sentencia = null;
        Connection conexion = null;

        String sql = "INSERT INTO NOTAS (idal, idas, fecha, nota) VALUES (" + nota.getAlumno().getIdal() + "," + nota.getAsignatura().getIdas() + ",'" + nota.getFecha() + "'," + nota.getNota() + ")";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            registrar = true;
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase NotaDaoImple, método registrar");
            e.printStackTrace();
        }
        return registrar;
    }

    @Override
    public List<Nota> obtenerTodos() {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        String sql = "SELECT * FROM NOTAS ORDER BY idal";

        List<Nota> listaNotas = new ArrayList<Nota>();

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                Nota n = new Nota();
                Alumno a = new Alumno();
                Asignatura ag = new Asignatura();

                //n.setAlumno(new Alumno().setIdal(rstAux.getInt(1)));//Lo intenté así pero no se puede
                n.setAlumno(a);
                a.setIdal(rstAux.getInt(1));
                n.setAsignatura(ag);
                ag.setIdas(rstAux.getInt(2));
                n.setFecha(rstAux.getDate(3));
                n.setNota(rstAux.getFloat(4));
                listaNotas.add(n);
            }
            sentencia.close();
            rstAux.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase NotasDaoImple, método obtenerTodos");
            e.printStackTrace();
        }

        return listaNotas;
    }
    
        

    @Override
    public boolean actualizar(Nota nota) {
        Connection conexion = null;
        Statement sentencia = null;

        boolean actualizar = false;

        String sql = "UPDATE NOTAS SET nota=" + nota.getNota() + " WHERE idal=" + nota.getAlumno().getIdal() + " AND idas=" + nota.getAsignatura().getIdas() + " AND fecha='" + nota.getFecha() + "'";
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            actualizar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase NotaDaoImple, método actualizar");
            e.printStackTrace();
        }
        return actualizar;
    }

    @Override
    public boolean eliminar(Nota nota) {
        Connection conexion = null;
        Statement sentencia = null;

        boolean eliminar = false;

        String sql = "DELETE FROM NOTAS WHERE idal=" + nota.getAlumno().getIdal() + " AND idas=" + nota.getAsignatura().getIdas() + " AND fecha='" + nota.getFecha() + "'";
        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            eliminar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase NotaDaoImple, método eliminar");
            e.printStackTrace();
        }
        return eliminar;
    }

}
