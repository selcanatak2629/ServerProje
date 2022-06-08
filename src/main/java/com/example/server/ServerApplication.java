package com.example.server;

import com.example.server.enumeration.Status;
import com.example.server.model.Server;
import com.example.server.repo.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.server.enumeration.Status.SERVER_DOWN;
import static com.example.server.enumeration.Status.SERVER_UP;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);
	}
	@Bean
	CommandLineRunner run(ServerRepository serverRepository){
		return args -> {
			serverRepository.save(new Server(null, "192.168.1.160", "Windows", "16 GB", "Personal PC", "http://localhost:8080/server/image/image1.png", SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.58", "Ubuntu Linux", "16 GB", "Dell Tower", "http://localhost:8080/server/image/image2.png", SERVER_DOWN));
			serverRepository.save(new Server(null, "192.168.1.21", "MS 2008", "32 GB", "Web Server", "http://localhost:8080/server/image/image3.png", SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.14", "Red Hat Ecterprise Linux", "64 GB", "Mail Server", "http://localhost:8080/server/image/image4.png", SERVER_DOWN));
		};
	}

}
