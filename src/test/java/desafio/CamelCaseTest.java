package desafio;

import desafio.exceptions.PalavraComCaracteresEspeciaisException;
import desafio.exceptions.PalavraComNumeroNoInicioException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CamelCaseTest {

    @Test
    @DisplayName("deve retornar palavra original quando a mesma é minúscula")
    public void testePalavraMinuscula() {
        List<String> palavraExperada = Arrays.asList("nome");
        List<String> resultado = CamelCase.converterCamelCase("nome");

        assertEquals(palavraExperada, resultado);
    }

    @Test
    @DisplayName("deve retornar palavra minúscula quando a primera letra é maiúscula")
    public void testePrimeiraLetraMaiuscula() {
        List<String> palavraExperada = Arrays.asList("nome");
        List<String> resultado = CamelCase.converterCamelCase("Nome");

        assertEquals(palavraExperada, resultado);
    }


    @Test
    @DisplayName("deve separar strings com camelcase")
    public void testeSeparaCamelCase() {
        String palavra1 = "nomeComposto";
        String palavra2 = "NomeComposto";

        List<String> palavraExperada = Arrays.asList("nome", "composto");
        List<String> resultado1 = CamelCase.converterCamelCase(palavra1);
        List<String> resultado2 = CamelCase.converterCamelCase(palavra2);

        assertEquals(palavraExperada, resultado1);
        assertEquals(palavraExperada, resultado2);
    }

    @Test
    @DisplayName("deve retornar palavra original quando a mesma é maiúscula")
    public void testePalavraMaiuscula() {
        List<String> palavraExperada = Arrays.asList("CPF");
        List<String> resultado = CamelCase.converterCamelCase("CPF");

        assertEquals(palavraExperada, resultado);
    }

    @Test
    @DisplayName("deve separar palavra quando conter sigla/acronimo")
    public void testePalavraComSigla() {
        String palavra1 = "numeroCPF";
        String palavra2 = "numeroCPFContribuinte";

        List<String> palavraExperada1 = Arrays.asList("numero", "CPF");
        List<String> palavraExperada2 = Arrays.asList("numero", "CPF", "contribuinte");
        List<String> resultado1 = CamelCase.converterCamelCase("numeroCPF");
        List<String> resultado2 = CamelCase.converterCamelCase("numeroCPFContribuinte");

        assertEquals(palavraExperada1, resultado1);
        assertEquals(palavraExperada2, resultado2);
    }

    @Test
    @DisplayName("deve separar palavra quando conter números")
    public void testePalavraComNumero() {
        List<String> palavraExperada = Arrays.asList("recupera", "10", "primeiros");
        List<String> resultado = CamelCase.converterCamelCase("recupera10Primeiros");

        assertEquals(palavraExperada, resultado);
    }

    @Test
    @DisplayName("deve lançar uma exceção quando a palavra iniciar com número")
    public void testeIniciaComNumero() {
        PalavraComNumeroNoInicioException exception = assertThrows(PalavraComNumeroNoInicioException.class, () -> {
            CamelCase.converterCamelCase("10Primeiros");
        });
        assertEquals("Não deve começar com números", exception.getMessage());
    }

    @Test
    @DisplayName("deve lançar uma exceção quando a palavra conter caracteres especiais")
    public void testeCaracteresEspeciais() {
        PalavraComCaracteresEspeciaisException exception = assertThrows(PalavraComCaracteresEspeciaisException.class, () -> {
            CamelCase.converterCamelCase("nome#Composto");
        });
        assertEquals("Caracteres especiais não são permitidos, somente letras e números", exception.getMessage());
    }
}