package padroes.chainofresponsibility;

public class SuporteEquipamentos extends AtendenteSuporte {
    public SuporteEquipamentos(AtendenteSuporte proximo) {
        super(proximo);
    }

    @Override
    protected boolean aceita(CategoriaChamado categoria) {
        return categoria == CategoriaChamado.EQUIPAMENTOS;
    }

    @Override
    protected String resolver(PedidoSuporte pedido) {
        return "Atendido pelo suporte de equipamentos";
    }
}
