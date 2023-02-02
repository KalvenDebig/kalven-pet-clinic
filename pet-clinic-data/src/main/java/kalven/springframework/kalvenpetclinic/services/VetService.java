package kalven.springframework.kalvenpetclinic.services;

import kalven.springframework.kalvenpetclinic.model.Vet;

import java.util.*;
public interface VetService {
    Vet findByID(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
