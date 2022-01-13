/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Vista;

import CRUD.Modelo.AlumnoAsignaturaProfesor;
import java.util.List;

/**
 *
 * @author a20estefaniapc
 */
public class AlumnoAsignaturaProfesorVista {
    
    public void verAlumnoAsignaturaProfesor(AlumnoAsignaturaProfesor aap) {
        System.out.println("Datos del ALUMNO + ASIGNATURA + PROFESOR:\n" + aap);
    }

    public void verTodosAlumnoAsignaturaProfesor(List<AlumnoAsignaturaProfesor> aaps) {
        for (AlumnoAsignaturaProfesor aap : aaps) {
            System.out.println("Datos del ALUMNO + ASIGNATURA + PROFESOR:\n" + aap);
        }
    }
    
}
