package me.mynsc.literalura.mains;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import me.mynsc.literalura.models.Book;
import me.mynsc.literalura.models.DataBook;
import me.mynsc.literalura.models.DataResults;
import me.mynsc.literalura.models.Language;
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
                    \n\t¡Bienvenido a Literalura! Escoja una opción
                    1. Buscar libro por título
                    2. Listar libros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos en un determinado año
                    5. Listar libros por idioma
                    6. Mostrar estadísticas
                    7. Listar los 10 libros más descargados
                    8. Buscar autor por nombre
                    9. Listar autores de los 80s
                    0. Salir
                    """);

            option = inpScanner.nextInt();
            inpScanner.nextLine(); // consume newline from the input buffer

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
                    printAuthorsAliveInYear();
                    break;
                }
                case 5: {
                    printBooksByLanguage();
                    break;
                }
                case 6: {
                    showDownloadStatistics();
                    break;
                }
                case 7: {
                    break;
                }
                case 8: {
                    break;
                }
                case 9: {
                    break;
                }
                case 0: {
                   System.out.println("Saliendo de la aplicación...");
                   break;
                }
                default: {
                    System.out.println("Opción no válida");
                }
            }
        }
    }

    public Book getBook(String search) {
        String searchEncoded = URLEncoder.encode(search, StandardCharsets.UTF_8);
        String json = apiConsume.getJson(URL + "search=" + searchEncoded);

        DataResults results = converter.getData(json, DataResults.class);
        DataBook dataBook = results.results().get(0);

        return new Book(dataBook);
    }
    
    public void searchByTitle() {
        System.out.println("Ingrese el título del libro que busca: ");
        String search = inpScanner.nextLine();
        
        Book book;
        try {
            book = getBook(search);
        }  catch (IndexOutOfBoundsException e) {
            System.out.println("No se encontró el libro");
            return;
        }
        
        // verify if the book is registered
        if (bookRepository.findByTitle(book.getTitle()).isPresent()) {
            System.out.println("El libro ya ha sido registrado");
            return;
        }
        
        Person author = book.getAuthor();
        Optional<Person> existingAuthor = personRepository.findByName(author.getName());
        
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
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            System.out.println("No hay libros registrados");
            return;
        }

        books.forEach(System.out::println);
    }

    public void printRegisterAuthors() {
        List<Person> authors = personRepository.findAll();
        
        if (authors.isEmpty()) {
            System.out.println("No hay autores registrados");
            return;
        }

        authors.forEach(System.out::println);
    }
    
    public void printAuthorsAliveInYear() {
        System.out.println("Buscar autores vivos en el año: ");
        Integer year = inpScanner.nextInt();
        inpScanner.nextLine(); // consume newline from the input buffer

        List<Person> authors = personRepository.findAll();

        if (authors.isEmpty()) {
            System.out.println("No hay autores registrados");
            return;
        }

        List<Person> authorsAliveIn = authors.stream()
            .filter(a -> a.getBirthYear() <= year && a.getDeathYear() > year)
            .collect(Collectors.toList());

        if (authorsAliveIn.isEmpty()) {
            System.out.println("No hay autores registrados vivos en este año");
            return;
        }

        authorsAliveIn.forEach(System.out::println);
    }

    public void printBooksByLanguage() {
        System.out.println("Ingrese el lenguaje del libro que busca: ");
        String search = inpScanner.nextLine();
        Language language = Language.fromLanguageFullName(search);

        List<Book> booksByLanguage = bookRepository.findByLanguage(language);
        
        if (booksByLanguage.isEmpty()) {
            System.out.println("No hay libros registrados del idioma solicitado");
            return;
        }

        booksByLanguage.forEach(System.out::println);
    }

    public void showDownloadStatistics() {
        Optional<Book> leastOrMostDownloadedBook;
        String BookTitle;

        IntSummaryStatistics statistics = bookRepository.findAll()
            .stream()
            .filter(b -> b.getDownloadCount() > 0)
            .collect(Collectors.summarizingInt(Book::getDownloadCount));

        System.out.println("\nLa media de descargas es de " + statistics.getAverage());

        leastOrMostDownloadedBook = bookRepository.findLeastDownloadedBook();

        if (leastOrMostDownloadedBook.isPresent()) {
            BookTitle = leastOrMostDownloadedBook.get().getTitle();
        } else {
            BookTitle = "No hay libros registrados";
        }

        System.out.println("\nEl libro menos descargado es " + BookTitle);

        leastOrMostDownloadedBook = bookRepository.findMostDownloadedBook();

        if (leastOrMostDownloadedBook.isPresent()) {
            BookTitle = leastOrMostDownloadedBook.get().getTitle();
        }

        System.out.println("\nEl libro más descargado es " + BookTitle);
    }
}
