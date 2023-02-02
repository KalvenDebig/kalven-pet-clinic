package kalven.springframework.kalvenpetclinic.services;

import kalven.springframework.kalvenpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);

}
