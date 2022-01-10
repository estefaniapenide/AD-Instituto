/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instituto_estefania_penide;

import controldata.ControlData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author a20estefaniapc
 */
public class Instituto_Estefania_Penide {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        byte op = 0;
        do {

            Menus.menuPrincipal();
            op = ControlData.leerByte(input);

            switch (op) {
                case 1:
                    //Igual pido datos para crearla
                    CreacionBD.creacionBD();
                    break;
                case 2:
                    if(baseDatosLista()){
                    Altas.altas(input, CreacionBD.sentencia, CreacionBD.rstAux);
                    }else{
                    System.out.println("ERROR. No se ha conectado a ninguna base de datos.\nPrimero debe seleccionar en MENÚ PRINCIPAL la OPCIÓN 1 de 'Creación/Conexión a BD'");
                    }
                    break;
                case 3:
                    if(baseDatosLista()){
                    Bajas.bajas(input, CreacionBD.sentencia, CreacionBD.rstAux);
                    }else{
                    System.out.println("ERROR. No se ha conectado a ninguna base de datos.\nPrimero debe seleccionar en MENÚ PRINCIPAL la OPCIÓN 1 de 'Creación/Conexión a BD'");
                    }
                    break;
                case 4:
                    if(baseDatosLista()){
                    Modificaciones.notaAlumno(input, CreacionBD.sentencia, CreacionBD.rstAux);
                    }else{
                    System.out.println("ERROR. No se ha conectado a ninguna base de datos.\nPrimero debe seleccionar en MENÚ PRINCIPAL la OPCIÓN 1 de 'Creación/Conexión a BD'");
                    }
                    break;
                case 5:
                    if(baseDatosLista()){
                    Listados.consultas(input, CreacionBD.sentencia, CreacionBD.rstAux);
                    }else{
                    System.out.println("ERROR. No se ha conectado a ninguna base de datos.\nPrimero debe seleccionar en MENÚ PRINCIPAL la OPCIÓN 1 de 'Creación/Conexión a BD'");
                    }
                    break;
                case 0:
                    System.out.println("PROGRAMA FINALIZADO");
                    break;
                default:
                    System.out.println("El valor introducido no se corresponde con ninguna de las opciones.\n");
                    break;
            }

        } while (op != 0);

        input.close();

    }

    public static boolean baseDatosLista() {
        boolean lista = false;
        if (CreacionBD.sentencia == null && CreacionBD.rstAux == null) {
            lista = false;
        } else {
            lista = true;
        }
        return lista;

    }

}
