/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Vista;

import CRUD.Modelo.Asignatura;
import java.util.List;

/**
 *
 * @author Estefania
 */
public class AsignaturaVista {

    public void verAsignatura(Asignatura asignatura) {
        System.out.println("Datos de la asignatura: " + asignatura);
    }

    public void verAsignaturas(List<Asignatura> asignaturas) {
        for (Asignatura asignatura : asignaturas) {
            System.out.println("Datos de la asignatura: " + asignatura);
        }
    }

}
