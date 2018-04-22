package app.exam.repository;

import app.exam.domain.dto.xml.CategoryExportDTO;
import app.exam.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
    @Query(value = "SELECT c FROM Category AS c WHERE c.name IN :names")
    List<Category> getCategoriesByListOfNames(@Param("names") List<String> names);
}
