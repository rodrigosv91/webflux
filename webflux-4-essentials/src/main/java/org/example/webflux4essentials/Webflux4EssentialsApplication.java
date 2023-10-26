package org.example.webflux4essentials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class Webflux4EssentialsApplication {
	static {
		BlockHound.install(
				builder -> builder.allowBlockingCallsInside("java.util.UUID", "randomUUID")
		);
	}
	public static void main(String[] args) {
		//System.out.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("senha123"));
		SpringApplication.run(Webflux4EssentialsApplication.class, args);
	}

}
