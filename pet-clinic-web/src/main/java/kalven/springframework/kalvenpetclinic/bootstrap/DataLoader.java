package kalven.springframework.kalvenpetclinic.bootstrap;

import kalven.springframework.kalvenpetclinic.model.*;
import kalven.springframework.kalvenpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;
    // no need for autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (petTypeService.findAll().size() == 0) {
            loadData();
        }
    }

    private void loadData() {
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

        System.out.println("---- add visit data");
        Visit catVisit = new Visit();
        catVisit.setData(LocalDate.now());
        catVisit.setDescription("cat visit");
        catVisit.setPet(cat1);
        visitService.save(catVisit);

        System.out.println("---- visit data added");

        System.out.println("Start loading vets");

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");

        Specialty surgery = new Specialty();
        surgery.setDescription("surgery");

        Specialty dentist = new Specialty();
        dentist.setDescription("dentist");

        specialtyService.save(radiology);
        specialtyService.save(surgery);
        specialtyService.save(dentist);

        Vet jessie = new Vet();
        jessie.setFirstName("Jessie");
        jessie.setLastName("Pink");
        jessie.getSpecialties().add(dentist);

        Vet walter = new Vet();
        walter.setFirstName("Walter");
        walter.setLastName("White");
        walter.getSpecialties().add(radiology);

        vetService.save(jessie);
        vetService.save(walter);

        System.out.println("Vets data saved");
    }
}
