/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instituto_estefania_penide;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a20estefaniapc
 */
public class CrearTablas {
    
        public static void crearTabla(Statement sentencia) {
        try {
            //sentencia.execute("DROP DATABASE IF EXISTS BDINSTITUTO");
            sentencia.execute("CREATE DATABASE IF NOT EXISTS BDINSTITUTO;");
            sentencia.execute("USE BDINSTITUTO;");
             //sentencia.execute("DROP TABLE IF EXISTS PROFESORES");
            sentencia.execute("CREATE TABLE IF NOT EXISTS PROFESORES"
                    + "(dni VARCHAR(30) NOT NULL,"//controlar que el dni sea correcto (trigers en la base de datos?/control cuando se pide por teclado??)
                    + "nombre VARCHAR(30) NOT NULL,"
                    + "titulacion VARCHAR(30) NOT NULL,"
                    + "PRIMARY KEY (dni))"
                    + "ENGINE INNODB;");
            //sentencia.execute("DROP TABLE IF EXISTS ALUMNOS");
            sentencia.execute("CREATE TABLE IF NOT EXISTS ALUMNOS"
                    + "(idal INT(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "codigo_alumno VARCHAR(30) NOT NULL," //[0-9]{3}[A-Z]{1} cambiar esto para que se ajuste a la expresión regular(se puede filtrar cuando pidamos los datos por teclado)
                    + "nombre VARCHAR(30) NOT NULL,"
                    + "PRIMARY KEY (idal))"
                    + "ENGINE INNODB;");
            //sentencia.execute("DROP TABLE IF EXISTS ASIGNATURAS");
            sentencia.execute("CREATE TABLE IF NOT EXISTS ASIGNATURAS"
                    + "(idas INT(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "codigo_asignatura VARCHAR(30) NOT NULL," //[0-9]{3}[A-Z]{1} cambiar esto para que se ajuste a la expresión regular(se puede filtrar cuando pidamos los datos por teclado)
                    + "nombre_ciclo VARCHAR(30) NOT NULL,"
                    + "PRIMARY KEY (idas))"
                    + "ENGINE INNODB;");
            //sentencia.execute("DROP TABLE IF EXISTS PROFESORESALUMNOSASIGNATURAS");
            sentencia.execute("CREATE TABLE IF NOT EXISTS PROFESORESALUMNOSASIGNATURAS" //ver las otrs restricciones del enunciado
                    + "(dni_profesor VARCHAR(30) NOT NULL,"
                    + "idas INT(4) ZEROFILL NOT NULL,"
                    + "idal INT(4) ZEROFILL NOT NULL,"
                    + "PRIMARY KEY (idas,idal)," //mirar bien lo de la clave compuesta
                    + "FOREIGN KEY (dni_profesor) references PROFESORES(dni),"
                    + "FOREIGN KEY (idas) references ASIGNATURAS(idas),"
                    + "FOREIGN KEY (idal) references ALUMNOS(idal))"
                    + "ENGINE INNODB;");
            //sentencia.execute("DROP TABLE IF EXISTS NOTAS");
            sentencia.execute("CREATE TABLE IF NOT EXISTS NOTAS" //ver ls otras restricciones del enunciado
                    + "(idas INT(4) ZEROFILL NOT NULL,"
                    + "idal INT(4) ZEROFILL NOT NULL,"
                    + "fecha DATE NOT NULL,"
                    + "nota FLOAT(2,2) NOT NULL,"
                    + "PRIMARY KEY (idas,idal,fecha),"
                    + "FOREIGN KEY (idas) references ASIGNATURAS(idas),"
                    + "FOREIGN KEY (idal) references ALUMNOS(idal))"
                    + "ENGINE INNODB;");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(5);

        }
    }
    
}
