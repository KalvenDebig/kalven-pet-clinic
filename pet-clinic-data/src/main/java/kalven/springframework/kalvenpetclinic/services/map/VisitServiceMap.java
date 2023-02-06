package kalven.springframework.kalvenpetclinic.services.map;

import kalven.springframework.kalvenpetclinic.model.Visit;
import kalven.springframework.kalvenpetclinic.services.VisitService;

import java.util.Set;

/**
 * @Project kalven-pet-clinic
 * @Author kalvens on 2/6/23
 */
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService {
    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit visit) {
        if (visit.getPet() == null || visit.getPet().getOwner() == null || visit.getPet().getId() == null
                || visit.getPet().getOwner().getId() == null) {
            throw new RuntimeException("Invalid Visit");
        }
        return super.save(visit);
    }

    @Override
    public void delete(Visit visit) {
        super.delete(visit);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
