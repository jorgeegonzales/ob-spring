package com.springboot.proyectspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProyectspringbootApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ProyectspringbootApplication.class, args);
		MotocicletaRepository repository = context.getBean(MotocicletaRepository.class);

		System.out.println("find");
		System.out.println("El numero de motocicletas en base de datos es: " + repository.count());

		Motocicleta honda = new Motocicleta(null,"Honda","Twister",2022);
		repository.save(honda);

		System.out.println("El numero de motocicletas en base de datos es: " + repository.count());

		System.out.println(repository.findAll());
	}

}
