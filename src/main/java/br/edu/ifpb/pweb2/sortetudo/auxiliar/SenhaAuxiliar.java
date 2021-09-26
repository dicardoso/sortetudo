package br.edu.ifpb.pweb2.sortetudo.auxiliar;

import org.mindrot.jbcrypt.BCrypt;

public class SenhaAuxiliar {

    //Transforma a senha em hash.
    public static String hashSenha(String senha){
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public static boolean checkSenha(String senha, String senhaHash) {
        if (BCrypt.checkpw(senha, senhaHash))
            return true;
        else
            return false;
    }

}
