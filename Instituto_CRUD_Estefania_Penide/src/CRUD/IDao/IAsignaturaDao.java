/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CRUD.IDao;

import CRUD.Modelo.Asignatura;
import java.util.List;

/**
 *
 * @author Estefania
 */
public interface IAsignaturaDao {
    
    public boolean existe(Asignatura asignatua);
    
    public boolean registrar(Asignatura asignatura);

    public List<Asignatura> obtenerTodos();
    
    public Asignatura obtener(Asignatura asignatura);

    public boolean actualizar(Asignatura asignatura);

    public boolean eliminar(Asignatura asignatura);
    
    public void verProfesor(Asignatura asignatura);

}
