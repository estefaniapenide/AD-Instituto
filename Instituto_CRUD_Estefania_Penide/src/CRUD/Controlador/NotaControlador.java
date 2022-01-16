package CRUD.Controlador;

import CRUD.DaoImpl.NotaDaoImpl;
import CRUD.IDao.INotaDao;
import CRUD.Modelo.Alumno;
import CRUD.Modelo.Asignatura;
import CRUD.Modelo.Nota;
import CRUD.Vista.NotaVista;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Estefania
 */
public class NotaControlador {

    private NotaVista vista = new NotaVista();

    public NotaControlador() {
    }

    //llama al DAO para comprobar si existe una nota a partir de su idal, idas y fecha
    public boolean existe(Nota nota) {
        INotaDao dao = new NotaDaoImpl();
        return dao.existe(nota);
    }
    
    //llama al DAO para comprobar si existe una nota a partir de su idal, idas
    public boolean existe(Alumno alumno, Asignatura asignatura) {
        INotaDao dao = new NotaDaoImpl();
        return dao.existe(alumno, asignatura);
    }

    //llama al DAO para guardar una nota
    public void registrar(Nota nota) {
        INotaDao dao = new NotaDaoImpl();
        dao.registrar(nota);
    }

    //llama al DAO para actualizar una nota
    public void actualizar(Nota nota) {
        INotaDao dao = new NotaDaoImpl();
        dao.actualizar(nota);
    }

    //llama al DAO para eliminar una nota
    public void eliminar(Nota nota) {
        INotaDao dao = new NotaDaoImpl();
        dao.eliminar(nota);
    }

    //llama al DAO para ver una nota a partir de una nota de la que se tiene el idal, el idas y la fecha
    public Nota obtener(Nota nota) {
        Nota n = new Nota();
        INotaDao dao = new NotaDaoImpl();
        n = dao.obtener(nota);
        return n;
    }

    //llama al DAO para ver una nota a partir del alumno, la asignatura y la fecha
    public Nota obtener(Alumno alumno, Asignatura asignatura, Date fecha) {
        Nota n = new Nota();
        INotaDao dao = new NotaDaoImpl();
        n = dao.obtener(alumno, asignatura, fecha);
        return n;
    }

    //llama al DAO para ver una nota a partir de una nota de la que se tiene el idal, el idas y la fecha
    public void ver(Nota nota) {
        Nota n = new Nota();
        INotaDao dao = new NotaDaoImpl();
        n = dao.obtener(nota);
        vista.verNota(n);
    }

    //llama al DAO para ver una nota a partir del alumno, la asignatura y la fecha
    public void ver(Alumno alumno, Asignatura asignatura, Date fecha) {
        Nota n = new Nota();
        INotaDao dao = new NotaDaoImpl();
        n = dao.obtener(alumno, asignatura, fecha);
        vista.verNota(n);
    }

    //llama al DAO para ver todas las notas y luego los muestra en la vista
    public void verNotas() {
        List<Nota> notas = new ArrayList<Nota>();
        INotaDao dao = new NotaDaoImpl();
        notas = dao.obtenerTodos();
        vista.verNotas(notas);
    }

    //llama al DAO para ver todas las notas de un alumno determinado y luego los muestra en la vista
    public void verNotas(Alumno alumno) {
        List<Nota> notas = new ArrayList<Nota>();
        INotaDao dao = new NotaDaoImpl();
        notas = dao.obtener(alumno);
        vista.verNotas(notas);
    }


}
