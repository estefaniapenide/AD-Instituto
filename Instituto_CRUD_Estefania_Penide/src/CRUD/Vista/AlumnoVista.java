/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Vista;

import CRUD.Modelo.Alumno;
import java.util.List;

/**
 *
 * @author Estefania
 */
public class AlumnoVista {

    public void verAlumno(Alumno alumno) {
        System.out.println("Datos del ALUMNO:\n" + alumno);
    }

    public void verAlumnos(List<Alumno> alumnos) {
        for (Alumno alumno : alumnos) {
            System.out.println("Datos del ALUMNO:\n" + alumno);
        }
    }

}
