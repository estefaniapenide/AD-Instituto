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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Estefania
 */
public class NotaDaoImpl implements INotaDao {

    @Override
    public boolean existe(Nota nota) {
        boolean existe = false;

        Statement sentencia = null;
        Connection conexion = null;
        ResultSet rstAux = null;

        String sql = "SELECT * FROM NOTAS";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                if (nota.getAlumno().getIdal() == rstAux.getInt("idal") && nota.getAsignatura().getIdas() == rstAux.getInt("idas") && nota.getFecha().toString().equals(rstAux.getString("fecha"))) {
                    existe = true;
                }
            }
            sentencia.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error: Clase NotaDaoImple, método existe(Nota nota)");
            e.printStackTrace();
        }
        return existe;
    }

    @Override
    public boolean existe(Alumno alumno, Asignatura asignatura) {
        boolean existe = false;

        Statement sentencia = null;
        Connection conexion = null;
        ResultSet rstAux = null;

        String sql = "SELECT * FROM NOTAS";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                if (alumno.getIdal() == rstAux.getInt("idal") && asignatura.getIdas() == rstAux.getInt("idas")) {
                    existe = true;
                }
            }
            sentencia.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error: Clase NotaDaoImple, método existe(Alumno alumno, Asignatura asignatura)");
            e.printStackTrace();
        }
        return existe;

    }

    @Override
    public boolean registrar(Nota nota) {
        boolean registrar = false;

        Statement sentencia = null;
        Connection conexion = null;

        String sql = "INSERT INTO NOTAS (idal, idas, fecha, nota) VALUES (" + nota.getAlumno().getIdal() + "," + nota.getAsignatura().getIdas() + ",'" + nota.getFecha().toString() + "'," + nota.getNota() + ")";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            sentencia.execute(sql);
            registrar = true;

            System.out.println("\nNOTA AÑADIDA.");

            sentencia.close();
            conexion.close();

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("\nERROR.No es posible añadir la nota.\nEl alumno " + nota.getAlumno().getCodigo_alumno() + " ya tiene una nota asignada en la asignatura " + nota.getAsignatura().getCodigo_asignatura() + " a fecha de " + nota.getFecha().toString() + ".");
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
    public List<Nota> obtener(Alumno alumno) {
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        String sql = "SELECT * FROM NOTAS WHERE idal=" + alumno.getIdal() + " ORDER BY idas";

        List<Nota> listaNotasAlumno = new ArrayList<Nota>();

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {
                Nota n = new Nota();
                //Alumno a = new Alumno();
                Asignatura ag = new Asignatura();

                //n.setAlumno(new Alumno().setIdal(rstAux.getInt(1)));//Lo intenté así pero no se puede
                n.setAlumno(alumno);
                //a.setIdal(rstAux.getInt(1));
                n.setAsignatura(ag);//Igual hay que cambiar esto para que almacene la asignatura entera y no solo el idal
                ag.setIdas(rstAux.getInt(2));
                n.setFecha(rstAux.getDate(3));
                n.setNota(rstAux.getFloat(4));
                listaNotasAlumno.add(n);
            }
            sentencia.close();
            rstAux.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase NotasDaoImple, método obtener(Alumno alumno)");
            e.printStackTrace();
        }

        return listaNotasAlumno;
    }

    @Override
    public Nota obtener(Alumno alumno, Asignatura asignatura, Date fecha) {

        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        Nota nota = new Nota();

        nota.setAlumno(alumno);
        nota.setAsignatura(asignatura);
        nota.setFecha(fecha);

        String sql = "SELECT * FROM NOTAS WHERE idal=" + alumno.getIdal() + " AND idas=" + asignatura.getIdas() + " AND fecha='" + fecha + "';";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {

                nota.setNota(rstAux.getFloat(4));

            }
            sentencia.close();
            rstAux.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase NotaDaoImple, método obtener(Alumno alumno, Asignatura asignatura, String fecha)");
            e.printStackTrace();
        }

        return nota;

    }

    @Override
    public Nota obtener(Nota nota) {

        Connection conexion = null;
        Statement sentencia = null;
        ResultSet rstAux = null;

        Nota n = new Nota();

        n.setAlumno(nota.getAlumno());
        n.setAsignatura(nota.getAsignatura());
        n.setFecha(nota.getFecha());

        String sql = "SELECT * FROM NOTAS WHERE idal=" + nota.getAlumno().getIdal() + " AND idas=" + nota.getAsignatura().getIdas() + " AND fecha='" + nota.getFecha() + "';";

        try {
            conexion = Conexion.conexionMySQL();
            sentencia = conexion.createStatement();
            sentencia.execute("USE BDINSTITUTO");
            rstAux = sentencia.executeQuery(sql);
            while (rstAux.next()) {

                n.setNota(rstAux.getFloat(4));

            }
            sentencia.close();
            rstAux.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase NotaDaoImple, método obtener(Nota nota)");
            e.printStackTrace();
        }

        return n;

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
            System.out.println("\nLA NOTA HA SIDO MODIFICADA CORRECTAMENTE.");
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
            System.out.println("\nNOTA ELIMINADA.");
        } catch (SQLException e) {
            System.out.println("Error: Clase NotaDaoImple, método eliminar");
            e.printStackTrace();
        }
        return eliminar;
    }

}
