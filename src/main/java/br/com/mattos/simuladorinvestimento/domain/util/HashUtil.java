package br.com.mattos.simuladorinvestimento.domain.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utilitário para geração de hashes utilizando algoritmos de criptografia.
 * Atualmente suporta SHA-256.
 */
public class HashUtil {

    /**
     * Gera o hash SHA-256 de uma string de entrada.
     *
     * <p>O hash é gerado a partir da representação UTF-8 da string. O resultado
     * é retornado como uma string hexadecimal de 64 caracteres.</p>
     *
     * @param input A string que será convertida em hash.
     * @return A representação hexadecimal do hash SHA-256.
     * @throws RuntimeException Se o algoritmo SHA-256 não estiver disponível no ambiente.
     *
     * @example
     * <pre>
     * String hash = HashUtil.sha256("senha123");
     * // hash = "ef92b778bafe771e89245b89ecbcf8a56e8c1222..."
     * </pre>
     */
    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));

            // Converter bytes para String hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash SHA-256", e);
        }
    }
}
