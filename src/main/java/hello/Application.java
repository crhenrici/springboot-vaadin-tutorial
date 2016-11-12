package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

    
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadData(CustomerRepository repository) {
    	return (args) -> {
        // save a couple of customers
        repository.save(new Customer("Jack", "Bauer", "Jacky", "Oslo"));
        repository.save(new Customer("Chloe", "O'Brian", "Chloey", "Miami"));
        repository.save(new Customer("Kim", "Bauer", "Kimmy", "Vancouver"));
        repository.save(new Customer("David", "Palmer", "Dave", "Zürich"));
        repository.save(new Customer("Michelle", "Dessler", "Michi", "Barcelona"));
        repository.save(new Customer("Cristian", "Henrici", "Cris", "Brüttisellen"));

        // fetch all customers
        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        for (Customer customer : repository.findAll()) {
            log.info(customer.toString());
        }
        log.info("");

        // fetch an individual customer by ID
        Customer customer = repository.findOne(1L);
        log.info("Customer found with findOne(1L):");
        log.info("--------------------------------");
        log.info(customer.toString());
        log.info("");

        // fetch customers by last name
        log.info("Customer found with findByLastNameStartsWithIgnoreCase('Henrici'):");
        log.info("--------------------------------------------");
        for (Customer henrici : repository.findByLastNameStartsWithIgnoreCase("Henricix1")) {
            log.info(henrici.toString());
        }
    };
    
    }
}
