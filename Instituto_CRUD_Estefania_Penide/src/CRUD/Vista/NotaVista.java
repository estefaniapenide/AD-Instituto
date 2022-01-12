/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD.Vista;

import CRUD.Modelo.Nota;
import java.util.List;

/**
 *
 * @author Estefania
 */
public class NotaVista {
    
        public void verNota(Nota nota) {
        System.out.println("Datos de la nota: " + nota);
    }

    public void verNotas(List<Nota> notas) {
        for (Nota nota : notas) {
            System.out.println("Datos de la nota: " + nota);
        }
    }
    
}
