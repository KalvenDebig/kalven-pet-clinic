package kalven.springframework.kalvenpetclinic.bootstrap;

import kalven.springframework.kalvenpetclinic.model.Owner;
import kalven.springframework.kalvenpetclinic.model.Vet;
import kalven.springframework.kalvenpetclinic.services.OwnerService;
import kalven.springframework.kalvenpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    // no need for autowired
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start loading owners");
        Owner kalven = new Owner();
        kalven.setId(1L);
        kalven.setFirstName("Kalven");
        kalven.setLastName("Shen");

        Owner jim = new Owner();
        jim.setId(2L);
        jim.setFirstName("Jim");
        jim.setLastName("Jin");

        ownerService.save(kalven);
        ownerService.save(jim);

        System.out.println("Owner data saved");

        System.out.println("Start loading vets");
        Vet jessie = new Vet();
        jessie.setId(3L);
        jessie.setFirstName("Jessie");
        jessie.setLastName("Pink");

        Vet walter = new Vet();
        walter.setId(4L);
        walter.setFirstName("Walter");
        walter.setLastName("White");

        vetService.save(jessie);
        vetService.save(walter);

        System.out.println("Vets data saved");
    }
}
