/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Vista;

import CRUD.Modelo.Profesor;
import java.util.List;

/**
 *
 * @author Estefania
 */
public class ProfesorVista {
    
    public void verProfesor(Profesor profesor) {
        System.out.println("Datos del PROFESOR:\n" + profesor);
    }

    public void verProfesores(List<Profesor> profesores) {
        for (Profesor profesor : profesores) {
            System.out.println("Datos del PROFESOR:\n" + profesor);
        }
    }
}
