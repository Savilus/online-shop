package pl.sda.pol122.auctionservice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AuctionServiceApplication {

	public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(AuctionServiceApplication.class)
                .headless(false)
                .run(args);
	}

}
