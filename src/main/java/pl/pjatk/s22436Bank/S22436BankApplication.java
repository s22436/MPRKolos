package pl.pjatk.s22436Bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S22436BankApplication {

	public S22436BankApplication(BankService bankService) {

	}

	public static void main(String[] args) {
		SpringApplication.run(S22436BankApplication.class, args);
	}

}
