package me.mynsc.literalura.mains;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import me.mynsc.literalura.models.Book;
import me.mynsc.literalura.models.DataBook;
import me.mynsc.literalura.models.DataResults;
import me.mynsc.literalura.models.Person;
import me.mynsc.literalura.repository.BookRepository;
import me.mynsc.literalura.repository.PersonRepository;
import me.mynsc.literalura.services.ApiConsume;
import me.mynsc.literalura.services.DataConverter;

public class Main {
    private Scanner inpScanner = new Scanner(System.in);
    private ApiConsume apiConsume = new ApiConsume();
    private DataConverter converter = new DataConverter();
    private BookRepository bookRepository;
    private PersonRepository personRepository;
    private final String URL = "https://gutendex.com/books/?";

    public Main(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }
    
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
                    searchByTitle();
                    break;
                }
                case 2: {
                    printBooks();
                    break;
                }
                case 3: {
                    printRegisterAuthors();
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

    public Book getBook(String search) {
        String searchEncoded = URLEncoder.encode(search, StandardCharsets.UTF_8);
        String json = apiConsume.getJson(URL + "search=" + searchEncoded);

        DataResults results = converter.getData(json, DataResults.class);
        DataBook dataBook = results.results().get(0);

        return new Book(dataBook);
    }
    
    public void searchByTitle() {
        System.out.println("Ingrese el título del libro que busca");
        String search = inpScanner.nextLine();
        
        Book book = getBook(search);
        
        // verify if the book is registered
        if (bookRepository.findByTitle(book.getTitle()).isPresent()) {
            System.out.println("El libro ya ha sido registrado");
            return;
        }
        
        Person author = book.getAuthor();
        var existingAuthor = personRepository.findByName(author.getName());
        
        // verify if the author already exists
        if (existingAuthor.isPresent()) {
            // link existing author to the respective book
            book.setAuthor(existingAuthor.get());
        } else {
            // persist new author to the database
            personRepository.save(author);
        }
        
        // persist new book to the database
        bookRepository.save(book);

        System.out.println(book);
    }

    public void printBooks() {
        List<Book> bookList = bookRepository.findAll();

        bookList.stream()
            .forEach(System.out::println);
    }

    public void printRegisterAuthors() {
        List<Person> authors = personRepository.findAll();
        
        if (authors.isEmpty()) {
            System.out.println("No hay autores registrados");
            return;
        }

        authors.forEach(System.out::println);
        System.out.println();
    }
}
