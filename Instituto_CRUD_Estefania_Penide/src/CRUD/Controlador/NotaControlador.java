
package CRUD.Controlador;

import CRUD.DaoImpl.NotaDaoImpl;
import CRUD.IDao.INotaDao;
import CRUD.Modelo.Nota;
import CRUD.Vista.NotaVista;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estefania
 */
public class NotaControlador {
    
    private NotaVista vista = new NotaVista();

    public NotaControlador() {
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

    //llama al DAO para obtener todas las notas y luego los muestra en la vista
    public void verNotas() {
        List<Nota> notas = new ArrayList<Nota>();
        INotaDao dao = new NotaDaoImpl();
        notas = dao.obtener();
        vista.verNotas(notas);
    }
    
}
