package padroes.chainofresponsibility;

import java.util.Objects;

public class CentralSuporte {
    private final AtendenteSuporte primeiro;

    public CentralSuporte(AtendenteSuporte primeiro) {
        this.primeiro = Objects.requireNonNull(primeiro, "Primeiro atendente obrigatorio");
    }

    public String abrirChamado(PedidoSuporte pedido) {
        return primeiro.atender(pedido);
    }
}
