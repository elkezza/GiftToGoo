package gifttogo.demo.controller;



import gifttogo.demo.model.Item;
import gifttogo.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Fetch all items as JSON
    @GetMapping("/api")
    @ResponseBody
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    // Fetch all items and display on a web page
    @GetMapping
    public String viewAllItems(Model model) {
        List<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        return "items/list";  // This refers to the Thymeleaf template "items/list.html"
    }

    // Create a new item (RESTful)
    @PostMapping("/api")
    @ResponseBody
    public Item createItem(@RequestBody Item item) {
        return itemService.save(item);
    }

    // Display form to create a new item (web page)
    @GetMapping("/new")
    public String showNewItemForm(Model model) {
        model.addAttribute("item", new Item());
        return "items/new";  // This refers to the Thymeleaf template "items/new.html"
    }

    // Process form submission to create a new item (web page)
    @PostMapping
    public String processNewItemForm(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        itemService.save(item);
        redirectAttributes.addFlashAttribute("successMessage", "Item created successfully");
        return "redirect:/items";
    }

    // Display form to update an item (web page)
    @GetMapping("/{id}/edit")
    public String showEditItemForm(@PathVariable Long id, Model model) {
        Item item = itemService.findById(id);
        model.addAttribute("item", item);
        return "items/edit";  // This refers to the Thymeleaf template "items/edit.html"
    }

    // Process form submission to update an item (web page)
    @PostMapping("/{id}")
    public String processEditItemForm(@PathVariable Long id, @ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        item.setId(id); // Set the ID from path variable to ensure we're updating the correct item
        itemService.save(item);
        redirectAttributes.addFlashAttribute("successMessage", "Item updated successfully");
        return "redirect:/items";
    }

    // Delete an item (RESTful)
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Delete an item (web page)
    @GetMapping("/{id}/delete")
    public String deleteItemAndRedirect(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        itemService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Item deleted successfully");
        return "redirect:/items";
    }
}
