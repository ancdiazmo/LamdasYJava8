package Main;

import java.util.ArrayList;

public class MainApplication {
	
	public static void main (String args []) {
		
		FunctionalInterface1 f1 = Persona::MetodoStatico;
		f1.action();
		
		Persona p1 = new Persona ();
		FunctionalInterface1 f2 = p1::MetodoNormal;
		f2.action();
		
		FunctionalInterface2 f3 = (nombre) -> nombre.toUpperCase();
		FunctionalInterface2 f4 = String::toUpperCase;
		System.out.println(f4.accionConParametro("Juan"));
		
		ArrayList <Persona> lista = new ArrayList<Persona>();
		
		lista.add(new Persona ("Andres"));
		lista.add(new Persona ("Juan"));
		lista.add(new Persona ("Maria"));
		lista.add(new Persona ("Monica"));
		
		lista.forEach(persona -> System.out.println(persona.getNombre()));
		
	}

}
