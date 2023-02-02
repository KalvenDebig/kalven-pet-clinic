package kalven.springframework.kalvenpetclinic.services;

import kalven.springframework.kalvenpetclinic.model.Owner;
import java.util.*;

public interface OwnerService {
    Owner findByLastName(String lastName);

    Owner findByID(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();

}
