package com.masdefect.service;

import com.masdefect.domain.dto.json.PersonExportJSONDto;
import com.masdefect.domain.dto.json.PersonImportJSONDto;
import com.masdefect.domain.entities.Person;
import com.masdefect.parser.ValidationUtil;
import com.masdefect.repository.PersonRepository;
import com.masdefect.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    private final PlanetRepository planetRepository;
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PlanetRepository planetRepository, PersonRepository personRepository) {
        this.planetRepository = planetRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void create(PersonImportJSONDto personImportJSONDto) {
        if(ValidationUtil.isValid(personImportJSONDto)) {
            Person person = new Person();
            person.setName(personImportJSONDto.getName());
            if(this.planetRepository.findByName(personImportJSONDto.getHomePlanet()) != null){
                person.setHomePlanet(this.planetRepository.findByName(personImportJSONDto.getHomePlanet()));
                this.personRepository.save(person);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<PersonExportJSONDto> findInnocentPersons() {
        return null;
    }
}
