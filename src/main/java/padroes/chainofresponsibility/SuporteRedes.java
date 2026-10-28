package padroes.chainofresponsibility;

public class SuporteRedes extends AtendenteSuporte {
    public SuporteRedes(AtendenteSuporte proximo) {
        super(proximo);
    }

    @Override
    protected boolean aceita(CategoriaChamado categoria) {
        return categoria == CategoriaChamado.REDES;
    }

    @Override
    protected String resolver(PedidoSuporte pedido) {
        return "Atendido pelo suporte de redes";
    }
}
