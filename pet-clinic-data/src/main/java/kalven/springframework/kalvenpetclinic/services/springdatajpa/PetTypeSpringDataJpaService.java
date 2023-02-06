package kalven.springframework.kalvenpetclinic.services.springdatajpa;

import kalven.springframework.kalvenpetclinic.model.PetType;
import kalven.springframework.kalvenpetclinic.repositories.PetTypeRepository;
import kalven.springframework.kalvenpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @Project kalven-pet-clinic
 * @Author kalvens on 2/6/23
 */
@Service
@Profile("springdatajpa")
public class PetTypeSpringDataJpaService implements PetTypeService {
    private final PetTypeRepository petTypeRepository;

    public PetTypeSpringDataJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long id) {
        return petTypeRepository.findById(id).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }
}
