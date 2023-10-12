package io.github.lucianodacunha.bank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankAppApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(BankAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.showMenu();

	}
}
