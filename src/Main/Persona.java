package Main;

public class Persona {
	
	String nombre;
	
	public Persona() {};
	
	public Persona(String nombre) {
		this.nombre = nombre;
	}
	
	public static void MetodoStatico () {
		System.out.println("Soy un metodo estatico");
	}
	
	public void MetodoNormal() {
		System.out.println("Soy un metodo normal");
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
