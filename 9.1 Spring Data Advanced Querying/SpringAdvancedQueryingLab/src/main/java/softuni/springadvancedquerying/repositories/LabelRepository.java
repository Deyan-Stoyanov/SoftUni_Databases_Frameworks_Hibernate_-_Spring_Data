package softuni.springadvancedquerying.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.springadvancedquerying.model.labels.ClassicLabel;

@Repository
public interface LabelRepository extends JpaRepository<ClassicLabel, Integer> {
}
