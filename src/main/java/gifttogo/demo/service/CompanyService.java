package gifttogo.demo.service;


import gifttogo.demo.model.Company;
import gifttogo.demo.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    public Company findById(Long id){
        return companyRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Highway was not found"));
    }

    public Company save(Company highway){
        return this.companyRepository.save(highway);
    }

    public void deleteById(Long id){
        this.companyRepository.deleteById(id);
    }
}
