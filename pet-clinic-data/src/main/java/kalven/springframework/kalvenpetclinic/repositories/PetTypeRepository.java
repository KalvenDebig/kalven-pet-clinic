package kalven.springframework.kalvenpetclinic.repositories;

import kalven.springframework.kalvenpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

/**
 * @Project kalven-pet-clinic
 * @Author kalvens on 2/6/23
 */
public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
