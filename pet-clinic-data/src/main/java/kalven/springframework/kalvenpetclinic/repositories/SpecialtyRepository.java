package kalven.springframework.kalvenpetclinic.repositories;

import kalven.springframework.kalvenpetclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;

/**
 * @Project kalven-pet-clinic
 * @Author kalvens on 2/6/23
 */
public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
