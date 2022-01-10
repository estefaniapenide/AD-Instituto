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
                    + "(dni CHAR(10) NOT NULL,"//controlar que el dni sea correcto (trigers en la base de datos?/control cuando se pide por teclado??)
                    + "nombre VARCHAR(30) NOT NULL,"
                    + "titulacion VARCHAR(30) NOT NULL,"
                    + "PRIMARY KEY (dni)) "
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
            //sentencia.execute("DROP TABLE IF EXISTS ALUMNOSASIGNATURASPROFESORES");
            sentencia.execute("CREATE TABLE IF NOT EXISTS ALUMNOSASIGNATURASPROFESORES" //ver las otrs restricciones del enunciado
                    + "(idal INT(4) ZEROFILL NOT NULL,"
                    + "idas INT(4) ZEROFILL NOT NULL,"
                    + "dni_profesor CHAR(10) NOT NULL,"
                    + "PRIMARY KEY (idas,idal)," //mirar bien lo de la clave compuesta
                    + "FOREIGN KEY (dni_profesor) references PROFESORES(dni) ON DELETE CASCADE ON UPDATE CASCADE,"
                    + "FOREIGN KEY (idal) references ALUMNOS(idal) ON DELETE CASCADE ON UPDATE CASCADE,"
                    + "FOREIGN KEY (idas) references ASIGNATURAS(idas) ON DELETE CASCADE ON UPDATE CASCADE)"
                    + "ENGINE INNODB;");
            //sentencia.execute("DROP TABLE IF EXISTS NOTAS");
            sentencia.execute("CREATE TABLE IF NOT EXISTS NOTAS" //ver ls otras restricciones del enunciado
                    + "(idal INT(4) ZEROFILL NOT NULL,"
                    + "idas INT(4) ZEROFILL NOT NULL,"
                    + "fecha DATE NOT NULL,"
                    + "nota FLOAT(4,2) NOT NULL,"
                    + "PRIMARY KEY (idal,idas,fecha),"
                    + "FOREIGN KEY (idal) references ALUMNOS(idal) ON DELETE CASCADE ON UPDATE CASCADE,"
                    + "FOREIGN KEY (idas) references ASIGNATURAS(idas) ON DELETE CASCADE ON UPDATE CASCADE)"
                    + "ENGINE INNODB;");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(5);

        }
    }

    public static void restriccionesDNI(Statement sentencia) {

        try {
            sentencia.execute("SET GLOBAL log_bin_trust_function_creators = 1;");
            sentencia.execute("DROP FUNCTION IF EXISTS validadni");
            sentencia.execute("CREATE FUNCTION validadni(dni CHAR(10))\n"
                    + "RETURNS BOOLEAN\n"
                    + "BEGIN\n"
                    + " DECLARE letrasValidas CHAR(23);\n"
                    + " DECLARE letraInput CHAR;\n"
                    + " DECLARE numero INT;\n"
                    + " DECLARE longitud INT;\n"
                    + " DECLARE letraCorrecta CHAR;\n"
//                    + " DECLARE mensaje CHAR;\n"
//                    + " DECLARE tipo_incorrecto CONDITION FOR SQLSTATE '22P02';\n"
//                    + " DECLARE EXIT HANDLER FOR tipo_incorrecto\n"
//                    + "     BEGIN\n"
//                    + "         SELECT 'Tipo incorrecto' INTO mensaje;\n"
//                    + "     END;"
//                    + " DECLARE mensaje CHAR;\n"
//                    + " DECLARE CONTINUE HANDLER FOR SQLSTATE '22P02'\n"
//                    + "     BEGIN\n"
//                    + "          SELECT 'Tipo incorrecto' INTO mensaje;\n"
//                    + "         rollback;\n"
//                    + "     END;\n"
                    + "	IF dni = NULL THEN RETURN FALSE;\n"
                    + " END IF;\n"
                    + " SET letrasValidas := 'TRWAGMYFPDXBNJZSQVHLCKE';"
                    + "	SET longitud := length(dni);\n"
                    + "	IF longitud<9 OR longitud>9 THEN\n"
                    + "     SIGNAL SQLSTATE '45000'\n"
                    + "         SET MESSAGE_TEXT = 'No es posible añadir al profesor. EL DNI debe constar de 8 números seguidos de una letra.';\n"
                    + "     RETURN FALSE;\n"
                    + "	END IF;\n"
                    + "	SET letraInput := SUBSTR(dni, length(dni), 1); -- Extraemos el último caracter\n"
                    + "	SET numero := CONVERT(SUBSTR(dni, 1, 8), SIGNED INT); -- Casteo a INT."
                    + "	SET letraCorrecta := SUBSTR(letrasValidas, MOD(numero, 23)+1, 1);\n"
                    + " IF NOT (letraCorrecta = letraInput) THEN\n"
                    + "     SIGNAL SQLSTATE '45000'\n"
                    + "         SET MESSAGE_TEXT = 'No es posible añadir al profesor. La letra de control del DNI no es correcta.';\n"
                    + "     RETURN FALSE;\n"
                    + "	END IF;\n"
                    + " RETURN TRUE;"
                    //+ " SIGNAL sqlstate '22P02';"//Comprobar si esto funciona
                    + "	-- Captura de la excepción si falla el casteo y devolución de false\n"//Pendiente
                    + "END;");

            sentencia.execute("DROP TRIGGER IF EXISTS dnicorrectoInsert");
            sentencia.execute("CREATE TRIGGER dnicorrectoInsert\n"
                    + "BEFORE INSERT ON PROFESORES\n"
                    + "FOR EACH ROW BEGIN\n"
                    + " DECLARE prof_ CHAR;"
                    + "	IF NOT validadni(NEW.dni) THEN \n"
                    + "     SIGNAL SQLSTATE '45000'\n"
                    + "         SET MESSAGE_TEXT = 'No es posible añadir al profesor. El dni del profesor no es válido.';\n"
                    + "	END IF;\n"
                    + "	SELECT 1 INTO prof_ FROM PROFESORES WHERE dni=NEW.dni;\n"
                    + "     IF prof_ IS NOT NULL THEN\n"
                    + "         SIGNAL SQLSTATE '45000'\n"
                    + "             SET MESSAGE_TEXT = 'No es posible añadir al profesor. El dni que intenta introducir ya existe en la tabla.';\n"
                    + "     END IF;\n"
                    + "END;");

            sentencia.execute("DROP TRIGGER IF EXISTS dnicorrectoUpdate");
            sentencia.execute("CREATE TRIGGER dnicorrectoUpdate\n"
                    + "BEFORE UPDATE ON PROFESORES\n"
                    + "FOR EACH ROW BEGIN\n"
                    + " DECLARE prof_ CHAR;"
                    + "	IF NOT validadni(NEW.dni) THEN\n"
                    + "     SIGNAL SQLSTATE '45000'\n"
                    + "         SET MESSAGE_TEXT = 'No es posible añadir al profesor. El dni del profesor no es válido.';\n"
                    + "	END IF;\n"
                    + "	SELECT 1 INTO prof_ FROM PROFESORES WHERE dni=NEW.dni;\n"
                    + "     IF prof_ IS NOT NULL THEN\n"
                    + "         SIGNAL SQLSTATE '45000'\n"
                    + "             SET MESSAGE_TEXT = 'No es posible añadir al profesor. El dni que intenta introducir ya existe en la tabla.';\n"
                    + "     END IF;\n"
                    + "END;");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(5);
        }
    }

    public static void restriccionesCodigos(Statement sentencia) {
        restriccionesCodigo(sentencia, "codigo_alumno", "ALUMNOS", "Alumno","INSERT");
        restriccionesCodigo(sentencia, "codigo_alumno", "ALUMNOS", "Alumno","UPDATE");
        restriccionesCodigo(sentencia, "codigo_asignatura", "ASIGNATURAS", "Asignatura","INSERT");
        restriccionesCodigo(sentencia, "codigo_asignatura", "ASIGNATURAS", "Asignatura","UPDATE");
    }


    private static void restriccionesCodigo(Statement sentencia, String codigo, String tabla, String nombre, String tipo) {

        try {
            sentencia.execute("DROP TRIGGER IF EXISTS Codigo"+tipo+nombre);
            sentencia.execute("CREATE TRIGGER Codigo"+tipo+nombre+"\n"
                    + "BEFORE "+tipo+" ON " + tabla + "\n"
                    + "FOR EACH ROW BEGIN\n"
                    + " DECLARE cod_ CHAR;"
                    + " IF NOT NEW." + codigo + " RLIKE '.{3}[A-Z]{1}' THEN \n"//Está puesto para que acepte todo tipo de cosas en los tres primeros (igual solo hay que dejar que acepte dígitos)
                    + "         SIGNAL SQLSTATE '45000'\n"        
                    + "             SET MESSAGE_TEXT = '" + nombre + " no añadido. Su código de " + nombre + " no es válido';\n"
                    + " END IF;"
                    + "	SELECT 1 INTO cod_ FROM " + tabla + " WHERE " + codigo + "=NEW." + codigo + ";\n"
                    + "     IF cod_ IS NOT NULL THEN\n"
                    + "         SIGNAL SQLSTATE '45000'\n"
                    + "             SET MESSAGE_TEXT = '" + nombre + " no añadido. El código de " + nombre + " que intenta introducir ya existe en la tabla.';\n"
                    + "     END IF;\n"
                    + "END;");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(5);
        }

    }

}
