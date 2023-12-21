package gifttogo.demo.controller;

import gifttogo.demo.model.Location;
import gifttogo.demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/map")
public class MapController {

    @Autowired
    LocationService locationService;
    @GetMapping("/map-page")
    public ModelAndView showMap() {
        List<Location> locations = locationService.getAllLocations(); // fetch the locations

        ModelAndView mav = new ModelAndView();
        mav.addObject("locations", locations); // add the locations to the model
        mav.setViewName("map/map-page"); // set the view name to 'map'

        return mav;
    }
}
