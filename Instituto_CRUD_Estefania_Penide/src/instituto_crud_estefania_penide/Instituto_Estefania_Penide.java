/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instituto_crud_estefania_penide;

import CRUD.Conexion.Conexion;
import static CRUD.Conexion.Conexion.conexionBDInstituto;
import controldata.ControlData;
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

        Principal();

    }

    public static void Principal() {

        Scanner input = new Scanner(System.in);

        byte op = 0;
        do {
            Menus.menuPrincipal();
            op = ControlData.leerByte(input);

            switch (op) {
                case 1:
                    Conexion.conectarseInstitutoBD(input);
                    break;
                case 2:
                    if (baseDatosConectada()) {
                        Altas.altas(input);
                    } else {
                        System.out.println("ERROR. NO se ha CONECTADO a ninguna base de datos.\nPrimero debe seleccionar en MENÚ PRINCIPAL la OPCIÓN 1 de 'Creación/Conexión a BD'");
                    }
                    break;
                case 3:
                    if (baseDatosConectada()) {
                        Bajas.bajas(input);
                    } else {
                        System.out.println("ERROR. NO se ha CONECTADO a ninguna base de datos.\nPrimero debe seleccionar en MENÚ PRINCIPAL la OPCIÓN 1 de 'Creación/Conexión a BD'");
                    }
                    break;
                case 4:
                    if (baseDatosConectada()) {
                        Modificaciones.notaAlumno(input);
                    } else {
                        System.out.println("ERROR. NO se ha CONECTADO a ninguna base de datos.\nPrimero debe seleccionar en MENÚ PRINCIPAL la OPCIÓN 1 de 'Creación/Conexión a BD'");
                    }
                    break;
                case 5:
                    if (baseDatosConectada()) {
                        Listados.consultas(input);
                    } else {
                        System.out.println("ERROR. NO se ha CONECTADO a ninguna base de datos.\nPrimero debe seleccionar en MENÚ PRINCIPAL la OPCIÓN 1 de 'Creación/Conexión a BD'");
                    }
                    break;
                case 6:
                    if (baseDatosConectada()) {
                        ResetDataBase.EliminarDatosBDInstituto(input);
                    } else {
                        System.out.println("ERROR. NO se ha CONECTADO a ninguna base de datos.\nPrimero debe seleccionar en MENÚ PRINCIPAL la OPCIÓN 1 de 'Creación/Conexión a BD'");
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

    public static boolean baseDatosConectada() {
        boolean lista = false;
        if (conexionBDInstituto) {
            lista = true;
        } else {
            lista = false;
        }
        return lista;

    }

}
