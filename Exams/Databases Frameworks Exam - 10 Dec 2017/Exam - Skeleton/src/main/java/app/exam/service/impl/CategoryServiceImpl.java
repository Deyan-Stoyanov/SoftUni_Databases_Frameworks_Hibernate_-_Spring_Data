package app.exam.service.impl;

import app.exam.domain.dto.xml.CategoriesFrequentItemsXMLExportDTO;
import app.exam.domain.dto.xml.CategoryExportDTO;
import app.exam.domain.dto.xml.MostPopularItemDTO;
import app.exam.domain.entities.Category;
import app.exam.repository.CategoryRepository;
import app.exam.repository.OrderItemRepository;
import app.exam.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, OrderItemRepository orderItemRepository) {
        this.categoryRepository = categoryRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public CategoriesFrequentItemsXMLExportDTO getCategoriesWithMostPopularItems(List<String> categoryNames) {
        CategoriesFrequentItemsXMLExportDTO wrapper = new CategoriesFrequentItemsXMLExportDTO();
        List<CategoryExportDTO> categoryExportDTOS = new ArrayList<>();
        List<Category> categories = this.categoryRepository.getCategoriesByListOfNames(categoryNames);
        for (Category c:categories) {
            CategoryExportDTO categoryExportDTO = new CategoryExportDTO();
             List<MostPopularItemDTO> mostPopularItemDTOS = this.orderItemRepository.mostPopularItemsByCategoryId(c.getId());
             categoryExportDTO.setName(c.getName());
             if(mostPopularItemDTOS.get(0) != null){
                 categoryExportDTO.setMostPopularItem(mostPopularItemDTOS.get(0));
             }
             categoryExportDTOS.add(categoryExportDTO);
        }
        categoryExportDTOS = categoryExportDTOS.stream().sorted((x, y) -> {
            if(y.getMostPopularItem().getTotalMade().compareTo(x.getMostPopularItem().getTotalMade()) == 0){
                return y.getMostPopularItem().getTimesSold().compareTo(x.getMostPopularItem().getTimesSold());
            }
            return y.getMostPopularItem().getTotalMade().compareTo(x.getMostPopularItem().getTotalMade());
        }).collect(Collectors.toList());
        wrapper.setCategories(categoryExportDTOS);
        return wrapper;
    }
}
