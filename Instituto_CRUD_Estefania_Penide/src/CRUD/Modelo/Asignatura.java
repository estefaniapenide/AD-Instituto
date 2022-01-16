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
public class Asignatura {

    private int idas;
    private String codigo_asignatura;
    private String nombre_ciclo;

//    private Profesor profesor;
//    private ArrayList<Alumno> alumnos;
    public Asignatura() {

    }

    public Asignatura(String codigo_asignatura) {
        this.codigo_asignatura = codigo_asignatura;
    }

    public Asignatura(String codigo_asignatura, String nombre_ciclo) {
        this.codigo_asignatura = codigo_asignatura;
        this.nombre_ciclo = nombre_ciclo;
    }

    public Asignatura(int idas, String codigo_asignatura, String nombre_ciclo) {
        this.idas = idas;
        this.codigo_asignatura = codigo_asignatura;
        this.nombre_ciclo = nombre_ciclo;
    }

    /**
     * @return the idas
     */
    public int getIdas() {
        return idas;
    }

    /**
     * @param idas the idas to set
     */
    public void setIdas(int idas) {
        this.idas = idas;
    }

    /**
     * @return the codigo_asignatura
     */
    public String getCodigo_asignatura() {
        return codigo_asignatura;
    }

    /**
     * @param codigo_asignatura the codigo_asignatura to set
     */
    public void setCodigo_asignatura(String codigo_asignatura) {
        this.codigo_asignatura = codigo_asignatura;
    }

    /**
     * @return the nombre_ciclo
     */
    public String getNombre_ciclo() {
        return nombre_ciclo;
    }

    /**
     * @param nombre_ciclo the nombre_ciclo to set
     */
    public void setNombre_ciclo(String nombre_ciclo) {
        this.nombre_ciclo = nombre_ciclo;
    }

//    /**
//     * @return the profesor
//     */
//    public Profesor getProfesor() {
//        return profesor;
//    }
//
//    /**
//     * @param profesor the profesor to set
//     */
//    public void setProfesor(Profesor profesor) {
//        this.profesor = profesor;
//    }
//
//    /**
//     * @return the alumnos
//     */
//    public ArrayList<Alumno> getAlumnos() {
//        return alumnos;
//    }
//
//    /**
//     * @param alumnos the alumnos to set
//     */
//    public void setAlumnos(ArrayList<Alumno> alumnos) {
//        this.alumnos = alumnos;
//    }
    @Override
    public String toString() {
        String asignatura
                = "\tIdas: " + idas + "\n"
                + "\tCódigo_asignatura: " + codigo_asignatura + "\n"
                + "\tNombre: " + nombre_ciclo;

        return asignatura;
    }
}
