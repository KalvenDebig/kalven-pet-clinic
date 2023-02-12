package kalven.springframework.kalvenpetclinic.formatters;

import kalven.springframework.kalvenpetclinic.model.PetType;
import kalven.springframework.kalvenpetclinic.repositories.PetRepository;
import kalven.springframework.kalvenpetclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

/**
 * @Project kalven-pet-clinic
 * @Author kalvens on 2/12/23
 */
@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();
        for (PetType petType : findPetTypes) {
            if (petType.getName().equals(text)) {
                return petType;
            }
        }
        throw new ParseException("Pet Type Not Found" + text, 0 );
    }
}
