package hiberspring.services.impl;

import hiberspring.dtos.imports.EmployeeCardsImportFromJsonDto;
import hiberspring.dtos.views.EmployeeCardUnusedViewDto;
import hiberspring.entities.EmployeeCard;
import hiberspring.parser.ValidationUtil;
import hiberspring.repositories.EmployeeCardRepository;
import hiberspring.services.EmployeeCardService;
import hiberspring.validators.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {

    private final EmployeeCardRepository employeeCardRepository;

    @Autowired
    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository) {
        this.employeeCardRepository = employeeCardRepository;
    }

    @Override
    public void createOne(EmployeeCardsImportFromJsonDto employeeCardsImportFromJsonDto) {

        if(ValidationUtil.isValid(employeeCardsImportFromJsonDto)){
            if(this.employeeCardRepository.getEmployeeCardByNumber(employeeCardsImportFromJsonDto.getNumber()) == null){
                EmployeeCard employeeCard = new EmployeeCard();
                employeeCard.setNumber(employeeCardsImportFromJsonDto.getNumber());
                this.employeeCardRepository.save(employeeCard);
            }

        }else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<EmployeeCardUnusedViewDto> getUnusedCards() {
        List<EmployeeCardUnusedViewDto> unusedCards = this.employeeCardRepository.getAllUnusedCards();
        return unusedCards;
    }

    private boolean isUniqueCardNumber(String cardNumber) {
        return true;
    }
}
