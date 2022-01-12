/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instituto_estefania_penide;

/**
 *
 * @author Estefania
 */
public class Menus {
    

    public static void menuPrincipal() {
        
        ConectarConBD.indicadorConexion();

        System.out.println("---------------------------------------------------------------------------\n"
                + "\t\t\tMENÚ PRINCIPAL ("+ConectarConBD.indicador+")\n"
                + "1.-Creación/Conexión a BDInstituto\n"
                + "2.-Altas\n"
                + "3.-Bajas\n"
                + "4.-Modificaciones\n"
                + "5.-Listados/Consultas\n\n"
                + "6.-Eliminar todos los datos (reset database)\n\n"
                + "0.-SALIR\n"
                + "---------------------------------------------------------------------------");

    }
    
    public static void menuAltas(){
        
        System.out.println("---------------------------------------------------------\n"
                + "\t\t\tALTAS\n"
                + "1.-Profesores\n"
                + "2.-Alumnos\n"
                + "3.-Asignaturas\n"
                + "4.-Notas\n"
                + "5.-Tabla: alumno + asignatura + profesor\n\n"
                + "0.-SALIR\n"
                + "---------------------------------------------------------");
    
    }
    
    public static void menuBajas(){
        
        System.out.println("---------------------------------------------------------\n"
                + "\t\t\tBAJAS\n"
                + "1.-Profesores\n"
                + "2.-Alumnos\n\n"
                + "0.-SALIR\n"
                + "---------------------------------------------------------");
    }
    
    public static void menuListados(){
    
    System.out.println("---------------------------------------------------------------------\n"
                + "\t\t\tLISTADOS/CONSULTAS\n\n"
                + "1.-Listado de un profesor (asignaturas que imparte)\n"
                + "2.-Listado de un alumno (notas que tiene en cada asignatura)\n"
                + "3.-Listado de una asignatura (con los profesores que la imparten)\n\n"
                + "0.-SALIR\n"
                + "---------------------------------------------------------------------");
    }

}
