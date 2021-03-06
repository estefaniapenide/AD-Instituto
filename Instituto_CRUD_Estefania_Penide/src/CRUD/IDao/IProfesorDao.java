/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CRUD.IDao;

import CRUD.Modelo.Profesor;
import java.util.List;

/**
 *
 * @author Estefania
 */
public interface IProfesorDao {
    
    public boolean existe(Profesor profesor);

    public boolean registrar(Profesor profesor);

    public List<Profesor> obtenerTodos();
    
    public Profesor obtener(Profesor profesor);

    public boolean actualizar(Profesor profesor);

    public boolean eliminar(Profesor profesor);
    
    public void verAsignaturas(Profesor profesor);

}
