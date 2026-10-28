package padroes.chainofresponsibility;

import java.util.Objects;

public abstract class AtendenteSuporte {
    private final AtendenteSuporte proximo;

    protected AtendenteSuporte(AtendenteSuporte proximo) {
        this.proximo = proximo;
    }

    public String atender(PedidoSuporte pedido) {
        Objects.requireNonNull(pedido, "Pedido obrigatorio");
        if (aceita(pedido.getCategoria())) {
            return resolver(pedido);
        }
        if (proximo != null) {
            return proximo.atender(pedido);
        }
        return "Categoria sem atendimento";
    }

    protected abstract boolean aceita(CategoriaChamado categoria);

    protected abstract String resolver(PedidoSuporte pedido);
}
