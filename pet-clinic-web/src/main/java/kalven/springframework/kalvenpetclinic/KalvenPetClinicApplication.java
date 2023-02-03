package kalven.springframework.kalvenpetclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class KalvenPetClinicApplication {

    public static void main(String[] args) {
        System.out.println("--- application starts ---");
        SpringApplication.run(KalvenPetClinicApplication.class, args);
    }
}
