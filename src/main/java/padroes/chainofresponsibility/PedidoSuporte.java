package padroes.chainofresponsibility;

import java.util.Objects;

public class PedidoSuporte {
    private final CategoriaChamado categoria;

    public PedidoSuporte(CategoriaChamado categoria) {
        this.categoria = Objects.requireNonNull(categoria, "Categoria obrigatoria");
    }

    public CategoriaChamado getCategoria() {
        return categoria;
    }
}
