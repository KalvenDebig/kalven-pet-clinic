package kalven.springframework.kalvenpetclinic.repositories;

import kalven.springframework.kalvenpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Project kalven-pet-clinic
 * @Author kalvens on 2/6/23
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
