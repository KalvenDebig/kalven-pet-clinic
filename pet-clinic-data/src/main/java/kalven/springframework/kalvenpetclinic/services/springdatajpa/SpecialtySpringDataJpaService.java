package kalven.springframework.kalvenpetclinic.services.springdatajpa;

import kalven.springframework.kalvenpetclinic.model.Specialty;
import kalven.springframework.kalvenpetclinic.repositories.SpecialtyRepository;
import kalven.springframework.kalvenpetclinic.services.SpecialtyService;
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
public class SpecialtySpringDataJpaService implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtySpringDataJpaService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialties = new HashSet<>();
        specialtyRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Specialty findById(Long id) {
        return specialtyRepository.findById(id).orElse(null);
    }

    @Override
    public Specialty save(Specialty object) {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Specialty object) {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }
}
