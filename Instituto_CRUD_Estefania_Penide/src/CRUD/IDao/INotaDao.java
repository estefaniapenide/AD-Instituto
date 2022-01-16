/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CRUD.IDao;

import CRUD.Modelo.Alumno;
import CRUD.Modelo.Asignatura;
import CRUD.Modelo.Nota;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Estefania
 */
public interface INotaDao {

    public boolean existe(Nota nota);
    
    public boolean existe(Alumno alumno, Asignatura asignatura);

    public boolean registrar(Nota nota);

    public List<Nota> obtenerTodos();

    public List<Nota> obtener(Alumno alumno);//Hará falta para el apartado modifiaciones
//    
//    public List<Nota> obtener(Asignatura asignatura);
//    
//    public List<Nota> obtener(Date fecha);
//    

    public Nota obtener(Alumno alumno, Asignatura asignatura, Date fecha);

    public Nota obtener(Nota nota);

    public boolean actualizar(Nota nota);

    public boolean eliminar(Nota nota);

}
