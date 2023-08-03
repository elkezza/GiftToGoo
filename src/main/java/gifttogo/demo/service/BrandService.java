package gifttogo.demo.service;


import gifttogo.demo.model.Brand;
import gifttogo.demo.repository.BrandRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
    public Brand findById(Long id){
        return brandRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Highway was not found"));
    }

    public Brand save(Brand highway){
        return this.brandRepository.save(highway);
    }

    public void deleteById(Long id){
        this.brandRepository.deleteById(id);
    }
}
