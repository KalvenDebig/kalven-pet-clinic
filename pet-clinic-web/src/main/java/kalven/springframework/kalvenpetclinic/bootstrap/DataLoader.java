package kalven.springframework.kalvenpetclinic.bootstrap;

import kalven.springframework.kalvenpetclinic.model.Owner;
import kalven.springframework.kalvenpetclinic.model.Pet;
import kalven.springframework.kalvenpetclinic.model.PetType;
import kalven.springframework.kalvenpetclinic.model.Vet;
import kalven.springframework.kalvenpetclinic.services.OwnerService;
import kalven.springframework.kalvenpetclinic.services.PetTypeService;
import kalven.springframework.kalvenpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    // no need for autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start adding pet types");

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Pet dog1 = new Pet();
        dog1.setName("Abby");
        dog1.setPetType(savedDogPetType);
        dog1.setBirthDate(LocalDate.now());

        Pet cat1 = new Pet();
        cat1.setName("Amy");
        cat1.setPetType(savedCatPetType);
        cat1.setBirthDate(LocalDate.now());

        System.out.println("Start loading owners");
        Owner kalven = new Owner();
        kalven.setFirstName("Kalven");
        kalven.setLastName("Shen");
        kalven.setAddress("345 Huntington Avenue");
        kalven.setCity("Boston");
        kalven.setTelephone("123123123");
        dog1.setOwner(kalven);
        kalven.getPets().add(dog1);

        Owner jim = new Owner();
        jim.setFirstName("Jim");
        jim.setLastName("Jin");
        jim.setAddress("12 BigHouse");
        jim.setCity("Shanghai");
        jim.setTelephone("321321321");
        cat1.setOwner(jim);
        jim.getPets().add(cat1);


        ownerService.save(kalven);
        ownerService.save(jim);


        System.out.println("Owner data saved");

        System.out.println("Start loading vets");
        Vet jessie = new Vet();
        jessie.setFirstName("Jessie");
        jessie.setLastName("Pink");

        Vet walter = new Vet();
        walter.setFirstName("Walter");
        walter.setLastName("White");

        vetService.save(jessie);
        vetService.save(walter);

        System.out.println("Vets data saved");
    }
}
