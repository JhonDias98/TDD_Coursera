package desafio;

import desafio.exceptions.PalavraComCaracteresEspeciaisException;
import desafio.exceptions.PalavraComNumeroNoInicioException;

import java.util.Arrays;
import java.util.List;

public class CamelCase {
    private static String regexSeparador = "(?<!(^|[A-Z0-9]))(?=[A-Z0-9])|(?<!(^|[^A-Z]))(?=[0-9])|(?<!(^|[^0-9]))(?=[A-Za-z])|(?<!^)(?=[A-Z][a-z])";
    private static String regexNumeroInicio = "^\\d.*$";
    private static String regexCaracterEspecial = "^[0-9a-zA-Z]*$";

    public static List<String> converterCamelCase(String original) {
        verificarSeContemNumeroNoInicio(original);
        vereficarSeContemCaracterEspecial(original);

        List<String> listaPalavras = separarPalavras(original);
        return tonarPalavraMiniscula(listaPalavras);
    }

    public static List<String> separarPalavras(String original) {
        String palavras[] = original.split(regexSeparador);
        return Arrays.asList(palavras);
    }

    public static void verificarSeContemNumeroNoInicio(String original) {
        if(original.matches(regexNumeroInicio))
            throw new PalavraComNumeroNoInicioException("Não deve começar com números");
    }

    public static void vereficarSeContemCaracterEspecial(String original) {
        if(!original.matches(regexCaracterEspecial))
            throw new PalavraComCaracteresEspeciaisException("Caracteres especiais não são permitidos, somente letras e números");
    }

    public static boolean verificarPalavraMaiuscula(String original) {
        return original.toUpperCase() == original;
    }

    public static List<String> tonarPalavraMiniscula(List<String> palavras) {
        palavras.replaceAll(palavra -> verificarPalavraMaiuscula(palavra) ? palavra : palavra.toLowerCase());
        return palavras;
    }

}
