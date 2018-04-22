package app.exam.service.impl;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.domain.entities.Category;
import app.exam.domain.entities.Item;
import app.exam.parser.ValidationUtil;
import app.exam.repository.CategoryRepository;
import app.exam.repository.ItemsRepository;
import app.exam.service.api.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemsServiceImpl implements ItemsService {
    private final ItemsRepository itemsRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ItemsServiceImpl(ItemsRepository itemsRepository, CategoryRepository categoryRepository) {
        this.itemsRepository = itemsRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void create(ItemJSONImportDTO itemJSONImportDTO) {
        if(ValidationUtil.isValid(itemJSONImportDTO)){
            if(this.itemsRepository.findByName(itemJSONImportDTO.getName()) == null){
                Item item = new Item();
                item.setName(itemJSONImportDTO.getName());
                item.setPrice(itemJSONImportDTO.getPrice());
                Category category = new Category();
                if(this.categoryRepository.findByName(itemJSONImportDTO.getCategory()) == null){
                    category.setName(itemJSONImportDTO.getCategory());
                } else {
                    category = this.categoryRepository.findByName(itemJSONImportDTO.getCategory());
                }
                this.categoryRepository.save(category);
                item.setCategory(category);
                this.itemsRepository.save(item);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
