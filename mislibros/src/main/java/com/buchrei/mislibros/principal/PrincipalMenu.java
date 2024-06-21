package com.buchrei.mislibros.principal;

import com.buchrei.mislibros.modelos.Libro;
import com.buchrei.mislibros.servicios.ApiLibros;
import com.buchrei.mislibros.servicios.ServicioLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class PrincipalMenu {

    private final ApiLibros apiLibros;
    private final ServicioLibros serviciosLibros;
    private final Scanner scanner;

    @Autowired
    public PrincipalMenu(ApiLibros apiLibros, ServicioLibros serviciosLibros) {
        this.apiLibros = apiLibros;
        this.serviciosLibros = serviciosLibros;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("Bienvenido al sistema de libros.");
            System.out.println("Selecciona una opción:");
            System.out.println("1. Buscar libros por nombre");
            System.out.println("2. Listar Libros Registrados en mi DB");
            System.out.println("3. Listar Autores Registrados en mi DB y sus libros");
            System.out.println("4. Listar Autores Vivos en x Anio");
            System.out.println("5. Listar libros por Idioma");
            System.out.println("0. Salir");

            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    buscarLibroPorNombre();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivosEnAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("¡Gracias por usar mi App!");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        }

        // Cerrar Scanner al salir del menú
        scanner.close();
    }

    private int obtenerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingresa un número válido.");
            scanner.next(); // Descartar la entrada inválida
        }
        return scanner.nextInt();
    }

    private void buscarLibroPorNombre() {
        System.out.println("Ingresa el nombre del libro a buscar:");
        scanner.nextLine(); // Consumir el salto de línea
        String nombreLibro = scanner.nextLine();

        try {
            // Llamar a la API para obtener los datos en formato JSON
            String datosJson = apiLibros.obtenerDatos(nombreLibro);

            // Parsear los datos JSON para obtener libros y autores
            List<Libro> libros = apiLibros.parsearLibrosDesdeJson(datosJson);
            List<String> autores = apiLibros.parsearAutoresDesdeJson(datosJson);

            // Guardar los libros y autores en la base de datos
            for (Libro libro : libros) {
                serviciosLibros.guardarLibro(libro);
            }

            for (String autor : autores) {
                serviciosLibros.guardarAutor(autor);
            }

            System.out.println("Libros y autores guardados en la base de datos correctamente.");
        } catch (Exception e) {
            System.out.println("Error al procesar y guardar datos en la base de datos: " + e.getMessage());
        }
    }

    private void listarLibros() {
        System.out.println("Opción 2: Por implementar");
    }

    private void listarAutores() {
        System.out.println("Opción 3: Por implementar");
    }

    private void listarAutoresVivosEnAnio() {
        System.out.println("Opción 4: Por implementar");
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Opción 5: Por implementar");
    }
}
