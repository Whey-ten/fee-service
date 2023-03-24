package sk.fmfi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import sk.fmfi.model.Fee;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class FeeRepository implements PanacheRepository<Fee> {
    public List<Fee> listForAcno(String acno){
        return list("acno", acno);
    }
}
