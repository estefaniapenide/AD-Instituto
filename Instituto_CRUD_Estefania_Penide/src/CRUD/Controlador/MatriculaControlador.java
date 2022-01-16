/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Controlador;

import CRUD.DaoImpl.MatriculaDaoImpl;
import CRUD.Modelo.Matricula;
import CRUD.Vista.MatriculaVista;
import java.util.ArrayList;
import java.util.List;
import CRUD.IDao.IMatriculaDao;
import CRUD.Modelo.Asignatura;
import CRUD.Modelo.Profesor;

/**
 *
 * @author a20estefaniapc
 */
public class MatriculaControlador {
    
    private MatriculaVista vista = new MatriculaVista();

    public MatriculaControlador() {
    }
    
    //llama al DAO para comprobar si existe una matricula a partir del alumno y la asignatura
    public boolean existe(Matricula aap) {
        IMatriculaDao dao = new MatriculaDaoImpl();
        return dao.existe(aap);
    }
    
    //llama al DAO para comprobar si existe una asignatura ya está registarada en la tabla ALUMNOSASIGNATURASPROFESORES
    public boolean existe(Asignatura asignatura) {
        IMatriculaDao dao = new MatriculaDaoImpl();
        return dao.existe(asignatura);
    }
    
   //llama al DAO para comprobar si existe una asignatura ya está asociada a un profesor en la tabla ALUMNOSASIGNATURASPROFESORES
    public boolean existe(Asignatura asignatura, Profesor profesor) {
        IMatriculaDao dao = new MatriculaDaoImpl();
        return dao.existe(asignatura,profesor);
    }

    //llama al DAO para guardar un aap
    public void registrar(Matricula aap) {
        IMatriculaDao dao = new MatriculaDaoImpl();
        dao.registrar(aap);
    }

    //llama al DAO para actualizar un aap
    public void actualizar(Matricula aap) {
        IMatriculaDao dao = new MatriculaDaoImpl();
        dao.actualizar(aap);
    }

    //llama al DAO para eliminar un aap
    public void eliminar(Matricula aap) {
        IMatriculaDao dao = new MatriculaDaoImpl();
        dao.eliminar(aap);
    }
    
    //llama al DAO para obtener una matricula a partir del alumno y la asignatura
    public Matricula obtener(Matricula aap) {
        Matricula matricula = new Matricula();
        IMatriculaDao dao = new MatriculaDaoImpl();
        matricula = dao.obtener(aap);
        return matricula;
    }
    
    //llama al DAO para obtener el profesor de una asignatura
    public Profesor obteneProfesor(Asignatura asignatura) {
        Profesor profesor = new Profesor();
        IMatriculaDao dao = new MatriculaDaoImpl();
        profesor = dao.obtenerProfesor(asignatura);
        return profesor;
    }
    
    //llama al DAO para ver una aap a partir del alumno y la asignatura
    public void ver(Matricula aap) {
        Matricula matricula = new Matricula();
        IMatriculaDao dao = new MatriculaDaoImpl();
        matricula = dao.obtener(aap);
        vista.verAlumnoAsignaturaProfesor(matricula);
    }

    //llama al DAO para ver todos los aap 
    public void verTodosAAPs() {
        List<Matricula> aaps = new ArrayList<Matricula>();
        IMatriculaDao dao = new MatriculaDaoImpl();
        aaps = dao.obtenerTodos();
        vista.verTodosAlumnoAsignaturaProfesor(aaps);
    }

    
}
