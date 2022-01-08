/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instituto_estefania_penide;

import controldata.ControlData;
import java.util.Scanner;

/**
 *
 * @author Estefania
 */
public class Altas {

    public static void altas(Scanner input) {
        
        //Scanner input = new Scanner(System.in);//Igual es mejor ponerlo aquí en lugar de que pase como parámetro en altas (comprobar diferencias)
        
        byte op = 0;
        do {
            
            Menus.menuAltas();
            op=ControlData.leerByte(input);
            
            switch(op){
                case 1:
                    profesores();
                    break;
                case 2:
                    alumnos();
                    break;
                case 3:
                    asignaturas();
                    break;
                case 4:
                    notas();
                    break;
                case 5:
                    profesorAlumnoAsignatura();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("El valor introducido no se corresponde con ninguna de las opciones.\n");
                    break;
            }
            
        } while (op != 0);

    }

    private static void profesores() {

    }

    private static void alumnos() {

    }

    private static void asignaturas() {

    }

    private static void notas() {

    }

    private static void profesorAlumnoAsignatura() {

    }

}
