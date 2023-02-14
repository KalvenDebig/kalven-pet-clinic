package kalven.springframework.kalvenpetclinic.controllers;

import kalven.springframework.kalvenpetclinic.model.Vet;
import kalven.springframework.kalvenpetclinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
 * @Author kalvens on 2/14/23
 */
@ExtendWith(MockitoExtension.class)
class VetControllerTest {
    @Mock
    VetService vetService;

    @InjectMocks
    VetController vetController;

    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();
    }

    @Test
    void listVets() throws Exception {
        Set<Vet> vetSet = new HashSet<>();
        Vet vet1 = new Vet();
        vet1.setId(1L);
        Vet vet2 = new Vet();
        vet2.setId(2L);
        vetSet.add(vet1);
        vetSet.add(vet2);

        Mockito.when(vetService.findAll()).thenReturn(vetSet);
        mockMvc.perform(MockMvcRequestBuilders.get("/vets"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("vets/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("vets"));
    }
}