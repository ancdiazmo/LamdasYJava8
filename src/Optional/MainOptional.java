package Optional;

import java.util.Optional;
import java.util.Scanner;

public class MainOptional {
	
	public static void main (String args []) {
		Scanner sc = new Scanner(System.in);
//		String texto = getStringWithOutNull(sc.nextLine());
//		String texto = getStringWithOutNull(null);
		
		
//		try {
//			String texto = orElseThrow (null);
//			System.out.println("Ingreso: " + texto);
//		}
//		catch (NullPointerException e) {
//			System.out.println(e.toString());
//		}
//		
//		System.out.println("Soy un codigo despues de la exception");
		
		isPresent("Cadena");
		isPresent(null);
	}
	
	private static String getStringWithOutNull (String cadena) {
		Optional <String> optional = Optional.ofNullable(cadena);
		String miCadena = optional.orElse("Null");
		return miCadena;
	}
	
	private static String orElseThrow (String cadena) {
		Optional <String> optional = Optional.ofNullable(cadena);
		optional.orElseThrow(NullPointerException::new);
		String miCadenaNotNull = optional.get();
		return miCadenaNotNull;
	}
	
	public static void isPresent (String nombre) {
		Optional <String> optional = Optional.ofNullable(nombre);
		System.out.println(optional.isPresent());
	}

}
