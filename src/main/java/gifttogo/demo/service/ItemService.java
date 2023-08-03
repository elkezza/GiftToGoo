package gifttogo.demo.service;

import gifttogo.demo.model.Item;
import gifttogo.demo.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item findById(Long id){
        return itemRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Highway was not found"));
    }

    public Item save(Item highway){
        return this.itemRepository.save(highway);
    }



    public void deleteById(Long id){
        this.itemRepository.deleteById(id);
    }
}
