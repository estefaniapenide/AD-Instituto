/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Controlador;

import CRUD.DaoImpl.AlumnoAsignaturaProfesorDaoImpl;
import CRUD.IDao.IAlumnoAsignaturaProfesorDao;
import CRUD.Modelo.AlumnoAsignaturaProfesor;
import CRUD.Vista.AlumnoAsignaturaProfesorVista;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author a20estefaniapc
 */
public class AlumnoAsignaturaProfesorControlador {
    
    private AlumnoAsignaturaProfesorVista vista = new AlumnoAsignaturaProfesorVista();

    public AlumnoAsignaturaProfesorControlador() {
    }

    //llama al DAO para guardar un aap
    public void registrar(AlumnoAsignaturaProfesor aap) {
        IAlumnoAsignaturaProfesorDao dao = new AlumnoAsignaturaProfesorDaoImpl();
        dao.registrar(aap);
    }

    //llama al DAO para actualizar un aap
    public void actualizar(AlumnoAsignaturaProfesor aap) {
        IAlumnoAsignaturaProfesorDao dao = new AlumnoAsignaturaProfesorDaoImpl();
        dao.actualizar(aap);
    }

    //llama al DAO para eliminar un aap
    public void eliminar(AlumnoAsignaturaProfesor aap) {
        IAlumnoAsignaturaProfesorDao dao = new AlumnoAsignaturaProfesorDaoImpl();
        dao.eliminar(aap);
    }

    //llama al DAO para obtener todos los aap y luego los muestra en la vista
    public void verTodosAAPs() {
        List<AlumnoAsignaturaProfesor> aaps = new ArrayList<AlumnoAsignaturaProfesor>();
        IAlumnoAsignaturaProfesorDao dao = new AlumnoAsignaturaProfesorDaoImpl();
        aaps = dao.obtenerTodos();
        vista.verTodosAlumnoAsignaturaProfesor(aaps);
    }

    
}
