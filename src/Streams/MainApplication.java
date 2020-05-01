/**
----------------------------Muy importante-------------------------------
Predicado => expresión lambda que devuelve booleano
Consumer => expresión lambda de tipo Void (sin retorno)
Function => expresión lambda con retorno (pendiente ver si incluye booleano...) 
**/

//A recordar
//Funciones matematicas y estadisticas
//Recordar Math y para funciones estadisticas apache commons math

package Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.IntSummaryStatistics;
import java.util.Set;
import java.util.Map;

public class MainApplication {
	
	private static ArrayList <Usuario> users;
	
	public static void main (String args []) {
		
		setUp();
		
		//las siguientes dos formas pueden servir para usar el Stream de la lista users
		
//		Stream stream = Stream.of(users); //Forma de crear un Stream con la lista
//		users.stream(); //Forma de referenciar al Stream por defecto de la lista
//		
//		users.stream().forEach(user -> user.setNombre(user.getNombre() + " un apellido"));
//		imprimirLista();
//		
//		//Profundizar mas en collect
//		List<String> listaStrings = users.stream().map(user -> user.getNombre()).collect(Collectors.toList());
		
		
		//Map: funciona parecido a filter con la diferencia que filter recibe un predicado
		//(retorna booleano) por lo tanto es un filtro y map "combierte el stream en uno nuevo"
		
		System.out.println("------------------MAP-------------------");
		setUp();

		Double Value = users.stream()
				.map(user -> user.getEdad())
				.collect(Collectors.averagingDouble(num -> (double) num));
		System.out.println(Value);
		
		
		
		//Filter: filtra un stream y debuelve un stream filtrado, para devolver lista usar collect
		System.out.println("-----------------FILTER----------------");
		setUp();
		
		List<Usuario> ListUserFiltered = users.stream()
				.filter(user -> user.getNombre() != "Juan")
				.collect(Collectors.toList());
		ListUserFiltered.forEach(user -> System.out.println(user));
			
		
		
		//Find first: encuentra el primer valor valor de un stream, combinado con filter, encuentra el primer valor
		//que cumpla una condición, findFirst devuelve optional
		
		System.out.println("-----------------FIND FIRST----------------");
		setUp();
		
		Usuario usuario = users.stream()
				.filter(user -> user.getEdad() == 23)
				.findFirst()
				.orElse(null);
		
		if (usuario == null) {
			System.out.println("No hay un usuario con 23 años");
		}
		else
		{
			System.out.println("El usuario es: " + usuario);
		}
		
		
		//flatMap: permite combinar una cantidad n de streams en uno solo, un ejemplo de esto puede ser 
		//tener una lista de listas donde queremos convertirla en una sola por facilidad
		
		System.out.println("-----------------FLAT MAP----------------");
		List<List<Usuario>> matrizDeUsuario = new ArrayList<List<Usuario>> ();
		
		matrizDeUsuario = Arrays.asList(
				new ArrayList<Usuario> (Arrays.asList(new Usuario (1,"Juan",22), new Usuario (2,"Pedro",23))),
				new ArrayList<Usuario> (Arrays.asList(new Usuario (3,"Maria",25)))
				);
		
		List<Usuario> listaUnica = matrizDeUsuario.stream()
				.flatMap(lista -> lista.stream())
				.collect(Collectors.toList());
		
		listaUnica.forEach(u -> System.out.println(u));
		
		
		//Peek: permite hacer un "Update" sobre el stream, difiere del map, ya que el map recibe una expresión lambda
		//que tenga un tipo de retorno distinto de void, una funcion
				
		//Este ejemplo tiene error de compilación ya que 
		//map recibe como argumento una function (retorna un valor), es decir No void
		
		//setUp();
		//List <Usuario> nuevaListaConvertida1 = users.stream()
			//.map(user -> user.setNombre(user.getNombre() + "un apellido"))
			//.collect(Collectors.toList());
		
		
		//Este ejemplo funciona ya que peek recibe como parametro un consumer
		//una expresión que no retornar valor
		
		
		System.out.println("-----------------PEEK-----------------");
		setUp();
		List <Usuario> nuevaListaConvertida2 = users.stream()
				.peek(user -> user.setNombre(user.getNombre() + " un apellido"))
				.collect(Collectors.toList());
		
		nuevaListaConvertida2.forEach(user -> System.out.println(user));
		
		System.out.println("-----------------COUNT-----------------");
		setUp();
		long countOfElements = users.stream()
				.filter(n -> n.getNombre() == "Juan")
				.count();
		
		System.out.println("El numero de elementos es: " + countOfElements);
		
		
		//Skip salta la cantidad n de elementos que queremos
		System.out.println("-----------------Skip-----------------");
		setUp();
		List <Usuario> newList = users.stream()
				.skip(2)
				.collect(Collectors.toList());
		
		newList.forEach(user -> System.out.println(user));
		
		//limit, limita el numero de elementos que queremos obtener
		System.out.println("-----------------limit-----------------");
		setUp();
		List <Usuario> newList1 = users.stream()
				.limit(2)
				.collect(Collectors.toList());
		
		newList1.forEach(user -> System.out.println(user));
		
		System.out.println("-----------------limit-----------------");
		
		
		//Sorted, ordena los elementos segun un criterio especificado
		System.out.println("-----------------Sorted-----------------");
		setUp();
		
		List<Usuario> newListSorted = users.stream()
				.sorted(Comparator.comparing(Usuario::getNombre))
				.collect(Collectors.toList());
				
		newListSorted.forEach(user -> System.out.println(user));
		
		
		//++++++++++++++++++++++++++++++Min y Max++++++++++++++++++++++++++++++++++
		System.out.println("-----------------Min y Max-----------------");
		setUp();
		
		Usuario minUser = new Usuario ();
		Usuario maxUser = new Usuario ();
		
		minUser = users.stream()
		.min(Comparator.comparing(Usuario::getId))
		.orElse(null);
		
		maxUser = users.stream()
		.max(Comparator.comparing(Usuario::getId))
		.orElse(null);
		
		System.out.println("min -->" + minUser);
		System.out.println("max -->" + maxUser);
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
		//++++++++++++++++++++++allMatch anyMatch noneMatch+++++++++++++++++++++++++
		System.out.println("-----------------allMatch anyMatch noneMatch-----------------");
		setUp();
		
		boolean allMatch = users.stream().allMatch(user -> user.getId() > 10);
		System.out.println(allMatch);
		boolean anyMatch = users.stream().anyMatch(user -> user.getId() > 5);
		System.out.println(anyMatch);
		boolean noneMatch = users.stream().noneMatch(user -> user.getId() > 10);
		System.out.println(noneMatch);
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		
		//++++++++++++++++++++++++++++++ Sum y Average ++++++++++++++++++++++++++++++
		System.out.println("-----------------Sum, Average y Range-----------------");
		setUp();
		
		double sum = users.stream()
				.mapToInt(user -> user.getId())
				.sum();
		System.out.println("sum: " + sum);
		
		double average = users.stream()
				.mapToInt(user -> user.getId())
				.average()
				.orElse(0);
		System.out.println("sum: " + average);
		
		//+++++++++++++++++++++++++++++++++ Reduce ++++++++++++++++++++++++++++++++++
		System.out.println("----------------- Reduce -----------------");
		setUp();
		int suma = users.stream()
				.map(user -> user.getId())
				.reduce(0, Integer::sum);
		System.out.println(suma);
		
		//+++++++++++++++++++++++++++++++++ Joining ++++++++++++++++++++++++++++++++++
		System.out.println("----------------- Joining -----------------");
		setUp();
		String concatenado = users.stream()
				.map(user -> user.getNombre())
				.collect(Collectors.joining("-"));
		System.out.println(concatenado);
		
		//+++++++++++++++++++++++++++++++++ toSet ++++++++++++++++++++++++++++++++++
		System.out.println("----------------- toSet -----------------");
		setUp();
		Set<String> sinDuplicados = users.stream() //Un Set es una colletion sin duplicados
				.map(Usuario::getNombre)
				.collect(Collectors.toSet());
		sinDuplicados.forEach(nombre -> System.out.println(nombre));
				
		//++++++++++++++++++++++ SummarizingDouble ++++++++++++++++++++++++
		System.out.println("-------------- SummarizingDouble --------------");
		IntSummaryStatistics statistics = users.stream()
				.mapToInt(Usuario::getId)
				.summaryStatistics();
		
		
//		//++++++++++++++++++++++ GroupingBy ++++++++++++++++++++++++
//		//Permite agrupar los elementos del stream
//		System.out.println("-------------- GroupingBy --------------");
//		setUp ();
//		Usuario u = new Usuario();
//		users.stream().collect(Collectors.groupingBy(Usuario::getNombre()));
		
		
		//El Stream pararel, es un stream que crea hilos de ejecucion para así optimizar los tiempos
		//de procesamiento
		
		//++++++++++++++++++++++ StreamPararel +++++++++++++++++++++++++
		System.out.println("-------------- StreamPararel --------------");
		setUp ();
		users.parallelStream().forEach(u -> System.out.println(u.getNombre()));
	}
	
	private static void matematicasYEstadisticos () {
		System.out.println();
	}
	
	private static void setUp () {
		users = new ArrayList<Usuario> ();
		users.add(new Usuario (1,"Juan", 22));
		users.add(new Usuario (2,"Marcos", 22));
		users.add(new Usuario (3,"Luisa", 25));
		users.add(new Usuario (4,"Juana", 60));
		users.add(new Usuario (5,"Maria", 23));
		users.add(new Usuario (6,"Juan", 30));
	}
	
	private static void imprimirLista () {
		users.stream().forEach(user -> System.out.println(user.toString()));
	}
	
	private static void imprimirLista (List list) {
		list.stream().forEach(l -> System.out.println(l));
	}
	 
	private static void Fechas () {
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//Crear una lista con la clase Arrays
		List<Integer> listaDeEnteros = Arrays.asList(100,200,300);
		List<String> listaDeString = Arrays.asList("Andres","Simon","Luisa");	
		
		Calendar calendar1 = new GregorianCalendar ();
		calendar1.set(2019, Calendar.APRIL, 01);
		Calendar calendar2 = new GregorianCalendar ();
		calendar2.set(2019, Calendar.APRIL, 02);
		Calendar calendar3 = new GregorianCalendar ();
		calendar3.set(2019, Calendar.APRIL, 03);
		Calendar calendar4 = new GregorianCalendar ();
		calendar4.set(2019, Calendar.APRIL, 04);
		List<Date> listaDeFechas = Arrays.asList(calendar1.getTime(), calendar2.getTime(),
				calendar3.getTime(), calendar4.getTime());
		listaDeFechas.forEach(fecha -> System.out.println(fecha));
	}
	
	private static void asignacionesCuriosas () {
		int y = 10;
		double x = y;
		System.out.println("Un double auto casteado: " + x);
	}
	
	private static void metodo1() {
		
	}
}
