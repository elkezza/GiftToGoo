package gifttogo.demo.controller;

import gifttogo.demo.model.Brand;
import gifttogo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/users/{userId}/brands")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand, @PathVariable Long userId) {
        Brand savedBrand = userService.createBrandForUser(brand, userId);
        return new ResponseEntity<>(savedBrand, HttpStatus.CREATED);
    }
}
