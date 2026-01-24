package me.mynsc.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import me.mynsc.literalura.mains.Main;
import me.mynsc.literalura.repository.BookRepository;

@SpringBootApplication
public class LiteraluraProjectApplication implements CommandLineRunner {
	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(bookRepository);
		main.showMenu();
	}

}
