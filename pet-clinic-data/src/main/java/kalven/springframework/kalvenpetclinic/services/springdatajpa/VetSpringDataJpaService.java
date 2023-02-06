package kalven.springframework.kalvenpetclinic.services.springdatajpa;

import kalven.springframework.kalvenpetclinic.model.Vet;
import kalven.springframework.kalvenpetclinic.repositories.VetRepository;
import kalven.springframework.kalvenpetclinic.services.VetService;
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
public class VetSpringDataJpaService implements VetService {
    private final VetRepository vetRepository;

    public VetSpringDataJpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet object) {
        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }
}
