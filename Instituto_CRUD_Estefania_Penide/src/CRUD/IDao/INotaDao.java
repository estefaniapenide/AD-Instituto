/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CRUD.IDao;

import CRUD.Modelo.Alumno;
import CRUD.Modelo.Asignatura;
import CRUD.Modelo.Nota;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Estefania
 */
public interface INotaDao {

    public boolean registrar(Nota nota);

    public List<Nota> obtenerTodos();
    
//    public Nota obtener(Alumno alumno);
//    
//    public Nota obtener(Asignatura asignatura);
//    
//    public Nota obtener(Date fecha);
//    
//    public Nota obtener(Alumno alumno, Asignatura asignatura, Date fecha);

    public boolean actualizar(Nota nota);

    public boolean eliminar(Nota nota);

}
