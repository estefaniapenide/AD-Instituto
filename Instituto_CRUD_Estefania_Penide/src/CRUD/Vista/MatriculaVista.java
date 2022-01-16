/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Vista;

import CRUD.Modelo.Matricula;
import java.util.List;

/**
 *
 * @author a20estefaniapc
 */
public class MatriculaVista {
    
    public void verAlumnoAsignaturaProfesor(Matricula aap) {
        System.out.println("Datos del ALUMNO + ASIGNATURA + PROFESOR:\n" + aap);
    }

    public void verTodosAlumnoAsignaturaProfesor(List<Matricula> aaps) {
        for (Matricula aap : aaps) {
            System.out.println("Datos del ALUMNO + ASIGNATURA + PROFESOR:\n" + aap);
        }
    }
    
}
