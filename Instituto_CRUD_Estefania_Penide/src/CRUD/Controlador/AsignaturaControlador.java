/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Controlador;

import CRUD.DaoImpl.AsignaturaDaoImpl;
import CRUD.IDao.IAsignaturaDao;
import CRUD.Modelo.Asignatura;
import CRUD.Vista.AsignaturaVista;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estefania
 */
public class AsignaturaControlador {

    private AsignaturaVista vista = new AsignaturaVista();

    public AsignaturaControlador() {
    }

    ////llama al DAO para comprobar si existe una asignatura
    public boolean existe(Asignatura asignatura) {
        IAsignaturaDao dao = new AsignaturaDaoImpl();
        return dao.existe(asignatura);
    }

    //llama al DAO para guardar una asignatura
    public void registrar(Asignatura asignatura) {
        IAsignaturaDao dao = new AsignaturaDaoImpl();
        dao.registrar(asignatura);
    }

    //llama al DAO para actualizar una asignatura
    public void actualizar(Asignatura asignatura) {
        IAsignaturaDao dao = new AsignaturaDaoImpl();
        dao.actualizar(asignatura);
    }

    //llama al DAO para eliminar una asignatura
    public void eliminar(Asignatura asignatura) {
        IAsignaturaDao dao = new AsignaturaDaoImpl();
        dao.eliminar(asignatura);
    }

    //llama al DAO para obtener una asignatura a partir de su código de asignatura
    public Asignatura obtener(Asignatura asignatura) {
        Asignatura asig = new Asignatura();
        IAsignaturaDao dao = new AsignaturaDaoImpl();
        asig = dao.obtener(asignatura);
        return asig;
    }

    //llama al DAO para ver una asignatura a partir de su código de asignatura
    public void ver(Asignatura asignatura) {
        Asignatura asig = new Asignatura();
        IAsignaturaDao dao = new AsignaturaDaoImpl();
        asig = dao.obtener(asignatura);
        vista.verAsignatura(asig);
    }

    //llama al DAO para ver todas las asignaturas y luego los muestra en la vista
    public void verAsignaturas() {
        List<Asignatura> asignaturas = new ArrayList<Asignatura>();
        IAsignaturaDao dao = new AsignaturaDaoImpl();
        asignaturas = dao.obtenerTodos();
        vista.verAsignaturas(asignaturas);
    }

    public void verProfesor(Asignatura asignatura) {
        IAsignaturaDao dao = new AsignaturaDaoImpl();
        dao.verProfesor(asignatura);
    }

}
