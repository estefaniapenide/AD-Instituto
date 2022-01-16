/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Controlador;

import CRUD.DaoImpl.AlumnoDaoImpl;
import CRUD.IDao.IAlumnoDao;
import CRUD.Modelo.Alumno;
import CRUD.Vista.AlumnoVista;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estefania
 */
public class AlumnoControlador {

    private AlumnoVista vista = new AlumnoVista();

    public AlumnoControlador() {
    }

    //llama al DAO para comprobar si existe un alumno
    public boolean existe(Alumno alumno) {
        IAlumnoDao dao = new AlumnoDaoImpl();
        return dao.existe(alumno);
    }

    //llama al DAO para guardar un alumno
    public void registrar(Alumno alumno) {
        IAlumnoDao dao = new AlumnoDaoImpl();
        dao.registrar(alumno);
    }

    //llama al DAO para actualizar un alumno
    public void actualizar(Alumno alumno) {
        IAlumnoDao dao = new AlumnoDaoImpl();
        dao.actualizar(alumno);
    }

    //llama al DAO para eliminar un alumno
    public void eliminar(Alumno alumno) {
        IAlumnoDao dao = new AlumnoDaoImpl();
        dao.eliminar(alumno);
    }

    //llama al DAO para obtener un alumno a partir de su código de alumno
    public Alumno obtener(Alumno alumno) {
        Alumno a = new Alumno();
        IAlumnoDao dao = new AlumnoDaoImpl();
        a = dao.obtener(alumno);
        return a;
    }

    //llama al DAO para ver un alumno a partir de su código de alumno
    public void ver(Alumno alumno) {
        Alumno a = new Alumno();
        IAlumnoDao dao = new AlumnoDaoImpl();
        a = dao.obtener(alumno);
        vista.verAlumno(a);
    }

    //llama al DAO para ver todos los alumnos y luego los muestra en la vista
    public void verAlumnos() {
        List<Alumno> alumnos = new ArrayList<Alumno>();
        IAlumnoDao dao = new AlumnoDaoImpl();
        alumnos = dao.obtenerTodos();
        vista.verAlumnos(alumnos);
    }

    public void verNotas(Alumno alumno) {
        IAlumnoDao dao = new AlumnoDaoImpl();
        dao.verNotas(alumno);
    }

}
