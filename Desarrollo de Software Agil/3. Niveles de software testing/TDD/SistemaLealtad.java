package tdd;

public class SistemaLealtad {
	
	private boolean estatusTajeta;
	private int saldoPuntos;
	private boolean estatusCupon;
	

	public boolean getEstatusTarjeta() {
		return this.estatusTajeta;
	}
	
	public void setEstatusTarjeta(boolean estatusTarjeta) {
		this.estatusTajeta = estatusTarjeta;
	}

	public int getSaldoPuntos() {
		return saldoPuntos;
	}

	public void setSaldoPuntos(int saldoPuntos) {
		this.saldoPuntos = saldoPuntos;
	}
	
	public boolean getEstatusCupon() {
		return estatusCupon;
	}

	public void setEstatusCupon(boolean estatusCupon) {
		this.estatusCupon = estatusCupon;
	}	
	
	public String mostrarProductosExtras() {
		if(this.saldoPuntos < 50) {
			return "";
		}
		if(this.saldoPuntos > 50 && this.saldoPuntos < 100) {
			return "Espagueti";
		}
		if(this.saldoPuntos > 100 && this.saldoPuntos < 150) {
			return "Espagueti,Queso Extra";
		}
		if(this.saldoPuntos > 150) {
			return "Espagueti,Queso Extra,Cupon Pizza Gratis";
		}
		return "error";
	}

	public void usarCupon(String respuesta) {

		if (respuesta.equals("Y"))
		{
			setEstatusCupon(false);
		}
		else if (respuesta.equals("N")) 
		{
			setEstatusCupon(true);
		} 
		else {
			System.out.println("Saldo Puntos: " + this.saldoPuntos);
		}
	}
	
}