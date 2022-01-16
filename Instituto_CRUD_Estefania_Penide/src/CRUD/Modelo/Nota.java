/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Modelo;

import java.sql.Date;

/**
 *
 * @author Estefania
 */
public class Nota {

    private Alumno alumno;
    private Asignatura asignatura;
    //date - milliseconds since January 1, 1970, 00:00:00 GMT
    private Date fecha;
    private float nota;

    public Nota() {

    }

    public Nota(Alumno alumno, Asignatura asignatura, Date fecha) {
        this.alumno = alumno;
        this.asignatura = asignatura;
        this.fecha = fecha;
    }

    public Nota(Alumno alumno, Asignatura asignatura, Date fecha, float nota) {
        this.alumno = alumno;
        this.asignatura = asignatura;
        this.fecha = fecha;
        this.nota = nota;
    }

    /**
     * @return the alumno
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * @param alumno the alumno to set
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    /**
     * @return the asignatura
     */
    public Asignatura getAsignatura() {
        return asignatura;
    }

    /**
     * @param asignatura the asignatura to set
     */
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the nota
     */
    public float getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(float nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        String nota
                = "\tAlumno: " + alumno.getCodigo_alumno()+" "+alumno.getNombre() + "\n"
                + "\tAsignatura: " + asignatura.getCodigo_asignatura()+" "+asignatura.getNombre_ciclo() + "\n"
                + "\tFecha: " + fecha + "\n"
                + "\tNota: " + this.nota;
        return nota;
    }

}
