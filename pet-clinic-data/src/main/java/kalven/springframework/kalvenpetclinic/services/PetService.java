package kalven.springframework.kalvenpetclinic.services;

import kalven.springframework.kalvenpetclinic.model.Pet;

public interface PetService extends CrudService<Pet, Long> {
    Pet findByName(String name);

}
