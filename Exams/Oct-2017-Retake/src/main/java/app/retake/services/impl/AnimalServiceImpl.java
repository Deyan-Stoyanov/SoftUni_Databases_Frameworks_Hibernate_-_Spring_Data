package app.retake.services.impl;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.Passport;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.PassportRepository;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final PassportRepository passportRepository;
    private final ModelParser modelParser;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository, PassportRepository passportRepository, ModelParser modelParser) {
        this.animalRepository = animalRepository;
        this.passportRepository = passportRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(AnimalJSONImportDTO dto) throws ParseException {
        if(ValidationUtil.isValid(dto)){
            if(this.passportRepository.findBySerialNumber(dto.getPassport().getSerialNumber()) == null){
                Animal animal = new Animal();
                Passport passport = new Passport();
                passport.setOwnerName(dto.getPassport().getOwnerName());
                passport.setOwnerPhoneNumber(dto.getPassport().getOwnerPhoneNumber());
                passport.setSerialNumber(dto.getPassport().getSerialNumber());
                passport.setRegistrationDate(new SimpleDateFormat("dd-MM-yyyy").parse(dto.getPassport().getRegistrationDate()));
                animal.setName(dto.getName());
                animal.setAge(dto.getAge());
                animal.setType(dto.getType());
                this.passportRepository.saveAndFlush(passport);
                animal.setPassport(passport);
                this.animalRepository.saveAndFlush(animal);
                passport.setAnimal(animal);
                this.passportRepository.save(passport);
            }
        }else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {
        List<AnimalsJSONExportDTO> dtos = this.animalRepository.animalsByOwnerPhoneNumber(phoneNumber);
        for (AnimalsJSONExportDTO animal:dtos) {
            try {
                animal.setRegisteredOn(new SimpleDateFormat("dd-MMM-yyyy").parse(new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd-MM-yyyy").parse(animal.getRegisteredOn().toString()))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dtos;
    }
}
