package pl.pjatk.s22436Bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S22436BankApplication {

	public S22436BankApplication(BankService bankService) {
		bankService.registerNewClient(100);
		bankService.registerNewClient(200);
		bankService.registerNewClient(300);

		System.out.println(bankService.createBankTransfer("0", 50));
		System.out.println(bankService.createBankTransfer("0", 100));
		System.out.println(bankService.createBankTransfer("0", 200));
		System.out.println(bankService.createBankTransfer("2", 50));
		System.out.println(bankService.createBankTransfer("5", 50));
		System.out.println(bankService.createBankDeposit("0", 100));
		System.out.println(bankService.createBankTransfer("5", 50));
		System.out.println(bankService.getClientData("0"));
		System.out.println(bankService.getClientData("5"));
	}

	public static void main(String[] args) {
		SpringApplication.run(S22436BankApplication.class, args);
	}

}
