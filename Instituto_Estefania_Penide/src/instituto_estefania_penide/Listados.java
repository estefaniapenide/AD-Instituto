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
public class Listados {
    
    public static void consultas(Scanner input){
        
        //Scanner input = new Scanner(System.in);
        
        byte op=0;
        do{
            Menus.menuListados();
            op=ControlData.leerByte(input);
            
            switch(op){
                case 1:
                    listadoProfesor();
                    break;
                case 2:
                    listadoAlumno();
                    break;
                case 3:
                    listadoAsignatura();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("El valor introducido no se corresponde con ninguna de las opciones.\n");
                    break;
            }
        
        }while(op!=0);
        //input.close();
    }
    
    private static void listadoProfesor(){
    
    }
    
    private static void listadoAlumno(){
    
    }
    
    private static void listadoAsignatura(){
    
    }
    
}
