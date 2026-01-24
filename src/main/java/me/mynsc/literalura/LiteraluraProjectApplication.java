package me.mynsc.literalura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import me.mynsc.literalura.mains.Main;

@SpringBootApplication
public class LiteraluraProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.showMenu();
	}

}
