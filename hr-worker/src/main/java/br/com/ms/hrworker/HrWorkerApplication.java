package br.com.ms.hrworker;

import br.com.ms.hrworker.entities.Worker;
import br.com.ms.hrworker.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrWorkerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HrWorkerApplication.class, args);
	}

	@Autowired
	WorkerRepository repository;
	@Override
	public void run(String... args) throws Exception {
		this.repository.save(new Worker(null, "Bob", 200.0));
		this.repository.save(new Worker(null, "Maria", 300.0));
		this.repository.save(new Worker(null, "Alex", 250.0));
	}
}
