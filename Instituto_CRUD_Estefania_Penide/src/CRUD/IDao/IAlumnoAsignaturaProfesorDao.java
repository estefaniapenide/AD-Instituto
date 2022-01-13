/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CRUD.IDao;

import CRUD.Modelo.Alumno;
import CRUD.Modelo.AlumnoAsignaturaProfesor;
import CRUD.Modelo.Asignatura;
import CRUD.Modelo.Profesor;
import java.util.List;

/**
 *
 * @author a20estefaniapc
 */
public interface IAlumnoAsignaturaProfesorDao {
    
    public boolean registrar(AlumnoAsignaturaProfesor aap);

    public List<AlumnoAsignaturaProfesor> obtenerTodos();
    
//    public AlumnoAsignaturaProfesor obtener(Alumno alumno);
//    
//    public AlumnoAsignaturaProfesor obtener(Asignatura asignatura);
//    
//    public AlumnoAsignaturaProfesor obtener(Profesor profesor);
//    
//    public AlumnoAsignaturaProfesor obtener(Alumno alumno, Asignatura asignatura, Profesor profesor);

    public boolean actualizar(AlumnoAsignaturaProfesor aap);

    public boolean eliminar(AlumnoAsignaturaProfesor aap);
    
}
