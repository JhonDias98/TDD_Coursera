package pilha;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pilha.exceptions.PilhaCheiaException;
import pilha.exceptions.PilhaVaziaException;

import static org.junit.jupiter.api.Assertions.*;

class PilhaTest {
    private Pilha p;

    @BeforeEach
    public void setup() {
        p = new Pilha(10);
    }

    @Test
    public void pilhaVazia() {
        assertTrue(p.estaVazia());
        assertEquals(0, p.tamanho());
    }

    @Test
    public void deveEmpilharUmElemento() {
        p.empilha("primeiro");

        assertFalse(p.estaVazia());
        assertEquals(1, p.tamanho());
        assertEquals("primeiro", p.topo());
    }

    @Test
    public void deveEmpilharEDesempilhar() {
        p.empilha("primeiro");
        p.empilha("segundo");
        assertEquals(2, p.tamanho());
        assertEquals("segundo", p.topo());

        Object desempilhado = p.desempilha();
        assertEquals(1, p.tamanho());
        assertEquals("primeiro", p.topo());
        assertEquals("segundo", desempilhado);
    }

    @Test
    public void naoDeveRemoverDaPilhaVazia() {
        PilhaVaziaException exception = assertThrows(PilhaVaziaException.class, () -> {
            p.desempilha();
        });

        assertEquals("Não é possível desempilhar", exception.getMessage());
    }

    @Test
    public void naoDeveEmpilharQuandoPilhaEstaCheia() {
        PilhaCheiaException exception =  assertThrows(PilhaCheiaException.class, () -> {
            for(int i = 0; i < 10; i++) {
                p.empilha("Elemento " + i);
            }
            p.empilha("Booommmm");
        });
        assertEquals("Não é possível empilhar mais elementos", exception.getMessage());
    }
}