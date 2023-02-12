package kalven.springframework.kalvenpetclinic.controllers;

import kalven.springframework.kalvenpetclinic.model.Owner;
import kalven.springframework.kalvenpetclinic.model.Pet;
import kalven.springframework.kalvenpetclinic.model.PetType;
import kalven.springframework.kalvenpetclinic.services.PetService;
import kalven.springframework.kalvenpetclinic.services.VisitService;
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
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


/**
 * @Project kalven-pet-clinic
 * @Author kalvens on 2/12/23
 */
@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
    @Mock
    PetService petService;
    @Mock
    VisitService visitService;
    @InjectMocks
    VisitController visitController;
    MockMvc mockMvc;
    final UriTemplate visitUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    final Map<String, String> uriVariables = new HashMap<>();
    URI visitUri;
    @BeforeEach
    void setUp() {
        Long petId = 1L;
        Long ownerId = 1L;
        Mockito.when(petService.findById(ArgumentMatchers.anyLong())).thenReturn(
                Pet.builder()
                        .id(petId)
                        .birthDate(LocalDate.now())
                        .name("PetNoOne")
                        .visits(new HashSet<>())
                        .owner(Owner.builder().id(ownerId).build())
                        .petType(PetType.builder().id(1L).name("Dpg").build())
                        .build());
        uriVariables.clear();
        uriVariables.put("ownerId", ownerId.toString());
        uriVariables.put("petId", petId.toString());
        visitUri = visitUriTemplate.expand(uriVariables);
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(visitUri))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("pet"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("visit"))
                .andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdateVisitForm"));
    }

    @Test
    void processNewVisitForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(visitUri))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/{ownerId}"));

        Mockito.verify(visitService).save(ArgumentMatchers.any());
    }
}