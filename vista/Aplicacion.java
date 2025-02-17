
package vista;

import dao.LibroDAO;
import modelo.Libro;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author marvin
 */
public class Aplicacion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibroDAO gestor= new LibroDAO();
        
        while(true)
        {
            System.out.println("\n Bienvenido Usuario");
            System.out.println("📚Gestión de Libros");
            System.out.println("1. Agregar un libro");
            System.out.println("2. Ver lista de libros registrados");
            System.out.println("3. Elimiar un libro");
            System.out.println("4. Salir");
            System.out.println("-> Elige una opción: ");
            
            int opcion = sc.nextInt();
            sc.nextLine();
            
            switch(opcion)
            {
                case 1: 
                    System.out.println("Ingrese el isbn del libro: ");
                    String isbn = sc.nextLine();
                    //sc.nextLine();
                    System.out.println("Ingrese el titulo del libro: ");
                    String titulo = sc.nextLine();
                    System.out.println("Ingrese el autor del libro: ");
                    String autor=sc.nextLine();
                    System.out.println("Ingrese el genero del libro: ");
                    String genero = sc.nextLine();
                    System.out.println("Ingrese la editorial del libro: ");
                    String editorial = sc.nextLine();
                    System.out.println("Ingrese el año de publicación: ");
                    int anio = sc.nextInt();
                    
                    Libro libro = new Libro(isbn,titulo, autor, genero,editorial, anio);
                    gestor.agregarLibro(libro, autor, genero, editorial);
                    break;

                    
                case 2: 
                    List<Libro>libros=gestor.obtenerLibros();
                    if(libros.isEmpty())
                    {
                        System.out.println("🚨 No hay libros registrados.");
                    }else
                    {
                        System.out.println("\nLista de Libros: ");
                        for(Libro l: libros)
                        {
                            System.out.println("l");
                        }
                    }
                    break;
                case 3: 
                    System.out.println("Ingrese el ID del libro a eliminar: ");
                    int id=sc.nextInt();
                    gestor.eliminarLibro(id);
                    break;
                case 4: 
                    System.out.println("👋🏼 see you soon 💋");
                    return;
                default:
                    System.out.println("❌ Opción no valida.");
            }
        }
    }
}
