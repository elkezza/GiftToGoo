package gifttogo.demo.service;

import gifttogo.demo.model.Offer;
import gifttogo.demo.repository.OfferRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> getAllItems() {
        return offerRepository.findAll();
    }

    public Offer findById(Long id){
        return offerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Highway was not found"));
    }

    public Offer save(Offer highway){
        return this.offerRepository.save(highway);
    }

    public void deleteById(Long id){
        this.offerRepository.deleteById(id);
    }
}
