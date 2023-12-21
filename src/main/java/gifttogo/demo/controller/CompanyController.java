package gifttogo.demo.controller;


import gifttogo.demo.model.Company;
import gifttogo.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * A RESTful API endpoint.
     * Returns a list of all companies in JSON format
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    /**
     * Web endpoint.
     * Retrieves all companies and forwards them to a Thymeleaf template (companies/list.html) for rendering.
     * Fetch all companies and display on a web page
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String viewAllCompanies(Model model) {
        List<Company> companies = companyService.getAllCompanies();
        model.addAttribute("companies", companies);
        return "companies/list";  // This refers to the Thymeleaf template "items/list.html"
    }

    /**
     * RESTful API endpoint.
     * Create a new item (RESTful)
     * Accepts a new company in JSON format and saves it.
     * Returns the saved company object.
     * @param company
     * @return
     */

    @PostMapping("/create-new-company")
    @ResponseBody
    public Company createCompany(@RequestBody Company company) {
        return companyService.save(company);
    }

    /**
     * Web endpoint.
     * Display form to create a new company (web page)
     * Presents a form for creating a new company. This form submits data to the processNewCompanyForm endpoint.
     * Uses the Thymeleaf template (companies/new.html).
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String showNewCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        return "companies/new";  // This refers to the Thymeleaf template "companies/new.html"
    }

    /**
     * Process form submission to create a new company (web page)
     * Web endpoint.
     * Handles the form submission from showNewCompanyForm.
     * Saves the new company and redirects to the list of all companies with a success message.
     * @param company
     * @param redirectAttributes
     * @return
     */
    @PostMapping
    public String processNewCompanyForm(@ModelAttribute Company company, RedirectAttributes redirectAttributes) {
        companyService.save(company);
        redirectAttributes.addFlashAttribute("successMessage", "Company created successfully");
        return "redirect:/companies";
    }

    /**
     * Display form to update a company (web page)
     * Web endpoint.
     * Displays a form to edit an existing company. Fetches the company by its ID.
     * The form submits data to processEditCompanyForm.
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}/edit")
    public String showEditCompanyForm(@PathVariable Long id, Model model) {
        Company company = companyService.findById(id);
        model.addAttribute("company", company);
        return "companies/edit";  // This refers to the Thymeleaf template "companies/edit.html"
    }

    /**
     * Process form submission to update a company (web page)
     * Web endpoint.
     * Handles the form submission from showEditCompanyForm.
     * Updates the company details and redirects back to the list with a success message.
     * @param id
     * @param company
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/{id}")
    public String processEditCompanyForm(@PathVariable Long id, @ModelAttribute Company company, RedirectAttributes redirectAttributes) {
        company.setId(id); // Set the ID from path variable to ensure we're updating the correct item
        companyService.save(company);
        redirectAttributes.addFlashAttribute("successMessage", "Company updated successfully");
        return "redirect:/companies";
    }

    /**
     * Delete a company (RESTful)
     * RESTful API endpoint.
     * Deletes a company by its ID.
     * Returns an HTTP 204 (No Content) upon successful deletion.
     * @param id
     * @return
     */
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a Company (web page)
     * Web endpoint.
     * Deletes a company by its ID and redirects back to the company list with a success message.
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/{id}/delete")
    public String deleteCompanyAndRedirect(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        companyService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Company deleted successfully");
        return "redirect:/companies";
    }
}
