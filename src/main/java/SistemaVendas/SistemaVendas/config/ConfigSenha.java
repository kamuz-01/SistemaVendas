package SistemaVendas.SistemaVendas.config;

import org.mindrot.jbcrypt.BCrypt;

public class ConfigSenha {

    // Método para gerar o hash da senha
    public static String gerarHashSenha(String senha) {
        // Gerando o hash da senha com um custo de 12 (quanto maior o custo, mais seguro)
        return BCrypt.hashpw(senha, BCrypt.gensalt(12));
    }

    // Método para verificar se a senha informada é válida (comparar com o hash)
    public static boolean verificarSenha(String senha, String hash) {
        // Verificando se a senha informada corresponde ao hash armazenado
        return BCrypt.checkpw(senha, hash);
    }
}
