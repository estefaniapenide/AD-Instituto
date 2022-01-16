/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Modelo;

import java.util.ArrayList;

/**
 *
 * @author Estefania
 */
public class Profesor {

    private String dni;
    private String nombre;
    private String titulacion;

//    private ArrayList<Asignatura> asignaturas;
    public Profesor() {

    }

    public Profesor(String dni) {
        this.dni = dni;
    }

    public Profesor(String dni, String nombre, String titulacion) {
        this.dni = dni;
        this.nombre = nombre;
        this.titulacion = titulacion;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the titulacion
     */
    public String getTitulacion() {
        return titulacion;
    }

    /**
     * @param titulacion the titulacion to set
     */
    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

//    /**
//     * @return the asignaturas
//     */
//    public ArrayList<Asignatura> getAsignaturas() {
//        return asignaturas;
//    }
//
//    /**
//     * @param asignaturas the asignaturas to set
//     */
//    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
//        this.asignaturas = asignaturas;
//    }
    @Override
    public String toString() {
        String profesor
                = "\tDNI: " + dni + "\n"
                + "\tNombre: " + nombre + "\n"
                + "\tTitulación: " + titulacion;

        return profesor;
    }

}
