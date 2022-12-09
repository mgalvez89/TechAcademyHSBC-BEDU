public class GranolaDecorador implements Helado{

    private Helado helado;

    public GranolaDecorador(Helado helado){
        this.helado = helado;
    }

    @Override
    public String getDescription() {
        return helado.getDescription() + ", con Granola extra" ;
    }

    @Override
    public int getPrice() {
        return helado.getPrice() +10;
    }

}
