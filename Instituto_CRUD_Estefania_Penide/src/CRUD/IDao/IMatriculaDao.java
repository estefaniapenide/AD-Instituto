/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CRUD.IDao;

import CRUD.Modelo.Alumno;
import CRUD.Modelo.Matricula;
import CRUD.Modelo.Asignatura;
import CRUD.Modelo.Profesor;
import java.util.List;

/**
 *
 * @author a20estefaniapc
 */
public interface IMatriculaDao {
    
    public boolean existe(Matricula aap);
    
    public boolean existe(Asignatura asignatura);
    
    public boolean existe(Asignatura asignatura, Profesor profesor);
    
    public boolean registrar(Matricula aap);

    public List<Matricula> obtenerTodos();
    
//    public List<Matricula> obtener(Alumno alumno);
//    
//    public List<Matricula> obtener(Asignatura asignatura);
//    
//    public List<Matricula> obtener(Profesor profesor);
//    
//    public Matricula obtener(Alumno alumno, Asignatura asignatura, Profesor profesor);
    
    public Matricula obtener(Matricula aap);
    
    public Profesor obtenerProfesor(Asignatura asignatura);

    public boolean actualizar(Matricula aap);

    public boolean eliminar(Matricula aap);
    
}
