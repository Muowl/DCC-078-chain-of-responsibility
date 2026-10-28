package padroes.chainofresponsibility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentralSuporteTest {
    private CentralSuporte central;

    @BeforeEach
    void configurarCadeia() {
        AtendenteSuporte equipamentos = new SuporteEquipamentos(null);
        AtendenteSuporte redes = new SuporteRedes(equipamentos);
        central = new CentralSuporte(new SuporteAplicacoes(redes));
    }

    @Test
    void deveAtenderNoPrimeiroEEncerrarEncaminhamento() {
        AtendenteSuporte proximo = new AtendenteSuporte(null) {
            @Override
            protected boolean aceita(CategoriaChamado categoria) {
                fail("O pedido resolvido nao deve chegar ao proximo atendente");
                return false;
            }

            @Override
            protected String resolver(PedidoSuporte pedido) {
                throw new AssertionError("Encaminhamento indevido");
            }
        };
        CentralSuporte centralLocal = new CentralSuporte(new SuporteAplicacoes(proximo));
        assertEquals("Atendido pelo suporte de aplicacoes",
                centralLocal.abrirChamado(new PedidoSuporte(CategoriaChamado.APLICACOES)));
    }

    @Test
    void deveEncaminharAoSegundoAtendente() {
        assertEquals("Atendido pelo suporte de redes",
                central.abrirChamado(new PedidoSuporte(CategoriaChamado.REDES)));
    }

    @Test
    void deveEncaminharAteOUltimoAtendente() {
        assertEquals("Atendido pelo suporte de equipamentos",
                central.abrirChamado(new PedidoSuporte(CategoriaChamado.EQUIPAMENTOS)));
    }

    @Test
    void deveInformarCategoriaSemAtendimentoAoFinalDaCadeia() {
        assertEquals("Categoria sem atendimento",
                central.abrirChamado(new PedidoSuporte(CategoriaChamado.FINANCEIRO)));
    }

    @Test
    void devePermitirCadeiaComUmAtendente() {
        CentralSuporte centralLocal = new CentralSuporte(new SuporteRedes(null));
        assertEquals("Atendido pelo suporte de redes",
                centralLocal.abrirChamado(new PedidoSuporte(CategoriaChamado.REDES)));
        assertEquals("Categoria sem atendimento",
                centralLocal.abrirChamado(new PedidoSuporte(CategoriaChamado.APLICACOES)));
    }

    @Test
    void devePermitirOutraOrdemDeAtendentes() {
        CentralSuporte centralLocal = new CentralSuporte(
                new SuporteEquipamentos(new SuporteAplicacoes(new SuporteRedes(null))));
        assertEquals("Atendido pelo suporte de redes",
                centralLocal.abrirChamado(new PedidoSuporte(CategoriaChamado.REDES)));
    }

    @Test
    void deveRejeitarPedidoNulo() {
        assertThrows(NullPointerException.class, () -> central.abrirChamado(null));
    }

    @Test
    void deveRejeitarCategoriaNula() {
        assertThrows(NullPointerException.class, () -> new PedidoSuporte(null));
    }

    @Test
    void deveRejeitarCentralSemPrimeiroAtendente() {
        assertThrows(NullPointerException.class, () -> new CentralSuporte(null));
    }
}
