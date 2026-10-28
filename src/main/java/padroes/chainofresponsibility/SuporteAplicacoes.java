package padroes.chainofresponsibility;

public class SuporteAplicacoes extends AtendenteSuporte {
    public SuporteAplicacoes(AtendenteSuporte proximo) {
        super(proximo);
    }

    @Override
    protected boolean aceita(CategoriaChamado categoria) {
        return categoria == CategoriaChamado.APLICACOES;
    }

    @Override
    protected String resolver(PedidoSuporte pedido) {
        return "Atendido pelo suporte de aplicacoes";
    }
}
