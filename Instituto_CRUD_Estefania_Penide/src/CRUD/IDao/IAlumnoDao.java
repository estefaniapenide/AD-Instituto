/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CRUD.IDao;

import CRUD.Modelo.Alumno;
import java.util.List;

/**
 *
 * @author Estefania
 */
public interface IAlumnoDao {
    
    public boolean existe(Alumno alumno);

    public boolean registrar(Alumno alumno);

    public List<Alumno> obtenerTodos();
    
    public Alumno obtener(Alumno alumno);

    public boolean actualizar(Alumno alumno);

    public boolean eliminar(Alumno alumno);
    
    public void verNotas(Alumno alumno);

}
