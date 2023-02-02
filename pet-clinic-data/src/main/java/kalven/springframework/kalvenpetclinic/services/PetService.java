package kalven.springframework.kalvenpetclinic.services;

import kalven.springframework.kalvenpetclinic.model.Pet;
import java.util.*;

public interface PetService {
    Pet findByName(String name);

    Pet findByID(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
