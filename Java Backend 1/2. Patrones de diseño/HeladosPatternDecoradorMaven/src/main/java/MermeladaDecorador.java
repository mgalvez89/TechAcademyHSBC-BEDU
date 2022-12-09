public class MermeladaDecorador implements Helado{

    private Helado helado;

    public MermeladaDecorador(Helado helado){
        this.helado = helado;
    }


    @Override
    public String getDescription() {
        return helado.getDescription() + ", con Mermelada extra";
    }

    @Override
    public int getPrice() {
        return helado.getPrice() + 10;
    }
}
