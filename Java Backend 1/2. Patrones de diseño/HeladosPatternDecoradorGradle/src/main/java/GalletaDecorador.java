public class GalletaDecorador implements  Helado{

    private Helado helado;

    public GalletaDecorador(Helado helado){
        this.helado = helado;
    }

    @Override
    public String getDescription() {
        return helado.getDescription() + ", con Galleta extra";
    }

    @Override
    public int getPrice() {
        return helado.getPrice() + 15;
    }
}
