package kalven.springframework.kalvenpetclinic.services.springdatajpa;

import kalven.springframework.kalvenpetclinic.model.Owner;
import kalven.springframework.kalvenpetclinic.repositories.OwnerRepository;
import kalven.springframework.kalvenpetclinic.repositories.PetRepository;
import kalven.springframework.kalvenpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Project kalven-pet-clinic
 * @Author kalvens on 2/8/23
 */
@ExtendWith(MockitoExtension.class)
class OwnerSpringDataJpaServiceTest {
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    OwnerSpringDataJpaService ownerService;
    final String LAST_NAME = "Smith";
    Owner returnedOwner;
    @BeforeEach
    void setUp() {
        returnedOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        Set<Owner> returnedOwnerSet = new HashSet<>();
        returnedOwnerSet.add(Owner.builder().id(1L).build());
        returnedOwnerSet.add(Owner.builder().id(2L).build());
        Mockito.when(ownerRepository.findAll()).thenReturn(returnedOwnerSet);
        Set<Owner> owners = ownerService.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        Mockito.when(ownerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(returnedOwner));
        Owner owner = ownerService.findById(1L);
        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(ownerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        Owner owner = ownerService.findById(1L);
        assertNull(owner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();

        Mockito.when(ownerRepository.save(ArgumentMatchers.any())).thenReturn(returnedOwner);
        Owner savedOwner = ownerService.save(ownerToSave);
        assertNotNull(savedOwner);
        Mockito.verify(ownerRepository).save(ArgumentMatchers.any());
    }

    @Test
    void delete() {
        ownerService.delete(returnedOwner);
        Mockito.verify(ownerRepository).delete(ArgumentMatchers.any());
    }

    @Test
    void deleteById() {
        ownerService.deleteById(1L);
        Mockito.verify(ownerRepository, Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    void findByLastName() {
        Mockito.when(ownerRepository.findByLastName(ArgumentMatchers.any())).thenReturn(returnedOwner);
        Owner Smith = ownerService.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, Smith.getLastName());
        Mockito.verify(ownerRepository).findByLastName(ArgumentMatchers.any());
    }
}