package me.mynsc.literalura.mains;

import java.util.Scanner;

public class Main {
    private Scanner inpScanner = new Scanner(System.in);

    public void showMenu() {
        int option = -1;

        while (option != 0) {
            System.out.println("""
                    \t¡Bienvenido a Literalura! Escoja una opción
                    1. Buscar libro por título
                    2. Listar libros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos en un determinado año
                    5. Listar libros por idioma
                    0. Salir
                    """);

            option = inpScanner.nextInt();
            inpScanner.nextLine(); // register new line of the buffer

            switch (option) {
                case 1: {
                    // buscar por titulo
                    break;
                }
                case 2: {
                    // listar libros registrados
                    break;
                }
                case 3: {
                    // listar autores registrados
                    break;
                }
                case 4: {
                    // listar autores vivos en un determinado año
                    break;
                }
                case 5: {
                    // listar libros por idioma
                    break;
                }
                case 0: {
                   System.out.println("Saliendo de la aplicación...");
                   break;
                }
                default: {

                }
            }
        }
        // (Validaciones: Opción 1: No insertar el mismo libro más de una vez. Mostrar mensaje si no se encuentra el libro en la API. Opciones 2-5: Mostrar un mensaje si no hay datos en la base de datos.)
    }

    
}
