package pl.sda.pol122.auctionservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AuctionServiceApplication {

	public static void main(String[] args) {
        new SpringApplicationBuilder(AuctionServiceApplication.class)
                .headless(false)
                .run(args);
	}

}
