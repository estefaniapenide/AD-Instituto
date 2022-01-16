package CRUD.Controlador;

import CRUD.DaoImpl.ProfesorDaoImpl;
import CRUD.IDao.IProfesorDao;
import CRUD.Modelo.Profesor;
import CRUD.Vista.ProfesorVista;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estefania
 */
public class ProfesorControlador {
    
    private ProfesorVista vista = new ProfesorVista();
    
    public ProfesorControlador() {
    }
    
    //llama al DAO para comprobar si existe un profesor a partir de su dni
    public boolean existe(Profesor profesor) {
        IProfesorDao dao = new ProfesorDaoImpl();
        return dao.existe(profesor);
    }

    //llama al DAO para guardar un profesor
    public void registrar(Profesor profesor) {
        IProfesorDao dao = new ProfesorDaoImpl();
        dao.registrar(profesor);
    }

    //llama al DAO para actualizar un profesor
    public void actualizar(Profesor profesor) {
        IProfesorDao dao = new ProfesorDaoImpl();
        dao.actualizar(profesor);
    }

    //llama al DAO para eliminar un profesor
    public void eliminar(Profesor profesor) {
        IProfesorDao dao = new ProfesorDaoImpl();
        dao.eliminar(profesor);
    }
    
    //llama al DAO para obtener un profesor a partir de su dni
    public Profesor obtener(Profesor profesor) {
        Profesor prof = new Profesor();
        IProfesorDao dao = new ProfesorDaoImpl();
        prof = dao.obtener(profesor);
        return prof;
    }

    //llama al DAO para ver un profesor a partir de su dni
    public void ver(Profesor profesor) {
        Profesor prof = new Profesor();
        IProfesorDao dao = new ProfesorDaoImpl();
        prof = dao.obtener(profesor);
        vista.verProfesor(prof);
    }

    //llama al DAO para ver todos los profesores y luego los muestra en la vista
    public void verProfesores() {
        List<Profesor> profesores = new ArrayList<Profesor>();
        IProfesorDao dao = new ProfesorDaoImpl();
        profesores = dao.obtenerTodos();
        vista.verProfesores(profesores);
    }
    
        public void verAsignaturas(Profesor profesor) {
        IProfesorDao dao = new ProfesorDaoImpl();
        dao.verAsignaturas(profesor);
    }
    
}
