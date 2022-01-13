/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Modelo;

/**
 *
 * @author a20estefaniapc
 */
public class AlumnoAsignaturaProfesor {

    private Alumno alumno;
    private Asignatura asignatura;
    private Profesor profesor;

    public AlumnoAsignaturaProfesor() {

    }

    public AlumnoAsignaturaProfesor(Alumno alumno, Asignatura asignatura, Profesor profesor) {
        this.alumno=alumno;
        this.asignatura=asignatura;
        this.profesor=profesor;
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
     * @return the profesor
     */
    public Profesor getProfesor() {
        return profesor;
    }

    /**
     * @param profesor the profesor to set
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }



}
