package gifttogo.demo.service;


import gifttogo.demo.model.Location;
import gifttogo.demo.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location findById(Long id){
        return locationRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Highway was not found"));
    }

    public Location save(Location highway){
        return this.locationRepository.save(highway);
    }

    public void deleteById(Long id){
        this.locationRepository.deleteById(id);
    }
}
