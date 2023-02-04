package kalven.springframework.kalvenpetclinic.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person {
    private Set<Specialty> specialties;

    public Vet() {
        this.specialties = new HashSet<>();
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }
}
