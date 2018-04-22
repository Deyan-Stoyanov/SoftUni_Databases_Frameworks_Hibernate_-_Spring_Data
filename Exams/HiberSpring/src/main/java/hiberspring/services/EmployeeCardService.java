package hiberspring.services;

import hiberspring.dtos.imports.EmployeeCardsImportFromJsonDto;
import hiberspring.dtos.views.EmployeeCardUnusedViewDto;
import hiberspring.entities.EmployeeCard;

import java.util.List;

public interface EmployeeCardService {

    void createOne(EmployeeCardsImportFromJsonDto employeeCardsImportFromJsonDto);

    List<EmployeeCardUnusedViewDto> getUnusedCards();
}
