public class CoberturaDecorador implements Helado{

    private Helado helado;

    public CoberturaDecorador(Helado obj){
        this.helado = obj;
    }

    @Override
    public String getDescription() {
        return helado.getDescription() + " , con Cobertura extra";
    }

    @Override
    public int getPrice() {
        return helado.getPrice() + 20;
    }
}
