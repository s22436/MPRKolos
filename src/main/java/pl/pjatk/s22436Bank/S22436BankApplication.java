package pl.pjatk.s22436Bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S22436BankApplication {

	public S22436BankApplication(CarService carService) {

//		RentalInfo testRental = carService.rentCar(
//				new User("1"), "9876",
//				LocalDate.of(2022, 11, 23),
//				LocalDate.of(2022, 11, 22)
//		);
//		System.out.println(testRental);
	}

	public static void main(String[] args) {
		SpringApplication.run(S22436BankApplication.class, args);
	}

}
