package com.taskflow.taskflow.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    /**
     * Genera un hash seguro de la contraseña.
     */
    public static String encriptar(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Verifica si una contraseña coincide con el hash.
     */
    public static boolean verificar(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

}
