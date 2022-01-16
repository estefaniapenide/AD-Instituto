/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Modelo;

/**
 *
 * @author a20estefaniapc
 */
public class Matricula {

    private Alumno alumno;
    private Asignatura asignatura;
    private Profesor profesor;

    public Matricula() {

    }

    public Matricula(Alumno alumno, Asignatura asignatura) {
        this.alumno = alumno;
        this.asignatura = asignatura;
    }

    public Matricula(Alumno alumno, Asignatura asignatura, Profesor profesor) {
        this.alumno = alumno;
        this.asignatura = asignatura;
        this.profesor = profesor;
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
    
    @Override
    public String toString() {
        String matricula
                = "\tAlumno: " + alumno.getCodigo_alumno()+" "+alumno.getNombre() + "\n"
                + "\tAsignatura: " + asignatura.getCodigo_asignatura()+" "+asignatura.getNombre_ciclo() + "\n"
                + "\tProfesor: " + profesor.getDni()+", "+profesor.getNombre();
        return matricula;
    }

}
