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
public class Alumno {

    private int idal;
    private String codigo_alumno;
    private String nombre;

    private ArrayList<Asignatura> asignaturas;

    public Alumno() {

    }

    public Alumno(int idal, String codigo_alumno, String nombre) {
        this.idal = idal;
        this.codigo_alumno = codigo_alumno;
        this.nombre = nombre;
    }

    /**
     * @return the idal
     */
    public int getIdal() {
        return idal;
    }

    /**
     * @param idal the idal to set
     */
    public void setIdal(int idal) {
        this.idal = idal;
    }

    /**
     * @return the codigo_alumno
     */
    public String getCodigo_alumno() {
        return codigo_alumno;
    }

    /**
     * @param codigo_alumno the codigo_alumno to set
     */
    public void setCodigo_alumno(String codigo_alumno) {
        this.codigo_alumno = codigo_alumno;
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
     * @return the asignaturas
     */
    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    /**
     * @param asignaturas the asignaturas to set
     */
    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

}
