/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CRUD.IDao;

import CRUD.Modelo.Nota;
import java.util.List;

/**
 *
 * @author Estefania
 */
public interface INotaDao {

    public boolean registrar(Nota nota);

    public List<Nota> obtener();

    public boolean actualizar(Nota nota);

    public boolean eliminar(Nota nota);

}
