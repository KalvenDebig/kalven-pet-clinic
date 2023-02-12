package kalven.springframework.kalvenpetclinic.controllers;

import kalven.springframework.kalvenpetclinic.model.Owner;
import kalven.springframework.kalvenpetclinic.model.Pet;
import kalven.springframework.kalvenpetclinic.model.PetType;
import kalven.springframework.kalvenpetclinic.services.OwnerService;
import kalven.springframework.kalvenpetclinic.services.PetService;
import kalven.springframework.kalvenpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
    PetTypeService petTypeService;
    @Mock
    OwnerService ownerService;
    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    Owner owner;

    Set<PetType> petTypes;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).build();
        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).name("Dog").build());
        petTypes.add(PetType.builder().id(2L).name("Cat").build());

        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void initCreationForm() throws Exception {
        Mockito.when(ownerService.findById(ArgumentMatchers.anyLong())).thenReturn(owner);
        Mockito.when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("pet"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
    }

    @Test
    void processCreationForm() throws Exception {
        Mockito.when(ownerService.findById(ArgumentMatchers.anyLong())).thenReturn(owner);
        Mockito.when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/new"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"));
        Mockito.verify(petService).save(ArgumentMatchers.any());
    }

    @Test
    void initUpdateForm() throws Exception {
        Mockito.when(ownerService.findById(ArgumentMatchers.anyLong())).thenReturn(owner);
        Mockito.when(petTypeService.findAll()).thenReturn(petTypes);
        Mockito.when(petService.findById(ArgumentMatchers.anyLong())).thenReturn(Pet.builder().id(1L).build());
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/1/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("pet"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
    }

    @Test
    void processUpdateForm() throws Exception {
        Mockito.when(ownerService.findById(ArgumentMatchers.anyLong())).thenReturn(owner);
        Mockito.when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/1/edit"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"));

        Mockito.verify(petService).save(ArgumentMatchers.any());
    }
}