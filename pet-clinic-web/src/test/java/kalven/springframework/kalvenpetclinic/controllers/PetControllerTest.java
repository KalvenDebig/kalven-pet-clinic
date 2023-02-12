package kalven.springframework.kalvenpetclinic.controllers;

import kalven.springframework.kalvenpetclinic.model.Owner;
import kalven.springframework.kalvenpetclinic.model.PetType;
import kalven.springframework.kalvenpetclinic.services.OwnerService;
import kalven.springframework.kalvenpetclinic.services.PetService;
import kalven.springframework.kalvenpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Project kalven-pet-clinic
 * @Author kalvens on 2/12/23
 */
@ExtendWith(MockitoExtension.class)
class PetControllerTest {
    @Mock
    PetService petService;
    @Mock
    OwnerService ownerService;
    @Mock
    PetTypeService petTypeService;
    @Mock
    PetController petController;

    MockMvc mockMvc;
    Owner owner;
    Set<PetType> petTypes;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).build();
        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).name("dog").build());
        petTypes.add(PetType.builder().id(2L).name("cat").build());


    }
}