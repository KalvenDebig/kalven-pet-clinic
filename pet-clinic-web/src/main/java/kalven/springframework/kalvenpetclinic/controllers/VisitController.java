package kalven.springframework.kalvenpetclinic.controllers;

import jakarta.validation.Valid;
import kalven.springframework.kalvenpetclinic.model.Pet;
import kalven.springframework.kalvenpetclinic.model.Visit;
import kalven.springframework.kalvenpetclinic.services.PetService;
import kalven.springframework.kalvenpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * @Project kalven-pet-clinic
 * @Author kalvens on 2/12/23
 */

@Controller
public class VisitController {
    private final VisitService visitService;
    private final PetService petService;
    private static final String VIEWS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setAllowFields(WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }

    /**
     * Called before each and every @RequestMapping method.
     * 2 goals:
     * - Make sure we always have fresh data
     * - Since we do not use session scope, make sure that Pet object always has an ID
     * (Even though ID is not part of the form fields)
     *
     * @Param petId
     * @Return Pet
     */
    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable Long petId, Model model) {
        return VIEWS_CREATE_OR_UPDATE_VISIT_FORM;
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_CREATE_OR_UPDATE_VISIT_FORM;
        }

        visitService.save(visit);
        return "redirect:/owners/{ownerId}";
    }
}
