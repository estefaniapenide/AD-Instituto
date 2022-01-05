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
            sentencia.execute("DROP DATABASE IF EXISTS BDINSTITUTO");
            sentencia.execute("CREATE DATABASE IF NOT EXISTS BDINSTITUTO;");
            sentencia.execute("USE BDINSTITUTO;");
            //sentencia.execute("DROP TABLE IF EXISTS PROFESORES");
            sentencia.execute("CREATE TABLE IF NOT EXISTS PROFESORES"
                    + "(dni VARCHAR(10) NOT NULL,"//controlar que el dni sea correcto (trigers en la base de datos?/control cuando se pide por teclado??)
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
                    + "(dni_profesor VARCHAR(10) NOT NULL,"
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

    public static void restriccionesTablas(Statement sentencia) {

        try {
            sentencia.execute("DROP FUNCTION IF EXISTS validadni");
            sentencia.execute("CREATE FUNCTION validadni(dni CHAR(10))\n"
                    + "RETURNS BOOLEAN\n"
                    + "BEGIN\n"
                    + "DECLARE letrasValidas CHAR(23);\n"
                    + "DECLARE letraInput CHAR;\n"
                    + "DECLARE numero INT;\n"
                    + "DECLARE longitud INT;\n"
                    + "DECLARE letraCorrecta CHAR;\n"
                    + "	IF dni=NULL THEN RETURN FALSE;\n"
                    + " END IF;\n"
                    + " SET letrasValidas := 'TRWAGMYFPDXBNJZSQVHLCKE';"
                    + "	SET longitud := length(dni);\n"
                    + "	IF longitud<9 OR longitud>10 THEN\n"
                    + "     SIGNAL SQLSTATE '45000'\n"
                    + "         SET MESSAGE_TEXT = 'EL DNI debe constar de 8 números y una letra';\n"
                    + "     RETURN FALSE;\n"
                    + "	END IF;\n"
                    + "	SET letraInput := SUBSTR(dni, length(dni), 1); -- Extraemos el último caracter\n"  
                    + "	SET numero := CAST((SUBSTR(dni, 1, 8)) AS INTEGER); -- Casteo a INT. Si no son números, lanza una excepciÓn '22P02'\n"
                    + "    /* Se separa primero el último caracter (letra) y los primeros 8 (números). \n"
                    + "	 * Si está con algún separador (caracter 9 cuando longitud 10), lo ignora  */\n"
                    + "	letraCorrecta := SUBSTR(letrasValidas, MOD(numero, 23)+1, 1);\n"
                    + "	IF (letraCorrecta = letraInput) THEN RETURN true;\n"
                    + "		ELSE\n"
                    + "             SIGNAL SQLSTATE '45000'\n"
                    + "                 SET MESSAGE_TEXT = 'La letra de control del DNI no es correcta';\n"
                    + "             RETURN false;\n"
                    + "	END IF;\n"
                    + "	-- Captura de la excepción si falla el casteo y devolución de false\n"
                    + "	EXCEPTION WHEN sqlstate '22P02' THEN -- Para gestionar si el casteo no se lleva a cabo\n"
                    + "        RAISE WARNING 'El dni debe empezar por 8 caracteres numéricos';\n"
                    + "        RETURN false;\n"
                    + "END;");

            sentencia.execute("create or replace function fdnicorrecto() returns trigger as $$\n"
                    + "begin\n"
                    + "	if not validadni(new.dni) then \n"
                    + "		raise exception 'El dni % no es válido',new.nombre;\n"
                    + "	end if;\n"
                    + "	perform 1 from PROFESORES where dni=new.dni;\n"
                    + "		if found then \n"
                    + "			raise exception 'El dni que intenta introducir ya existe en la tabla';\n"
                    + "		end if;	\n"
                    + "	return new;\n"
                    + "end;\n"
                    + "$$\n"
                    + "language 'plpgsql';");
            sentencia.execute("create trigger dnicorrecto\n"
                    + "BEFORE INSERT OR UPDATE ON PROFESORES\n"
                    + "FOR EACH ROW EXECUTE PROCEDURE fdnicorrecto();");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(5);
        }
    }

}

