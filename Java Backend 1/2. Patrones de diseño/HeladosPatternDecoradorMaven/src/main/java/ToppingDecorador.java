public class ToppingDecorador implements Helado{

    private Helado helado;

    public ToppingDecorador(Helado helado){
        this.helado = helado;
    }
    @Override
    public String getDescription() {
        return helado.getDescription() + ", con Topping extra";
    }

    @Override
    public int getPrice() {
        return helado.getPrice() + 20;
    }
}
