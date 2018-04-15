package app.terminal;


import app.domain.dto.json.*;
import app.domain.model.Person;
import app.domain.model.PhoneNumber;
import app.serialize.Serializer;
import app.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;

import static app.domain.dto.DTOConvertUtil.convert;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    private PersonService personService;

    private static final String PERSON_INPUT_JSON = "E:\\Програмиране\\СофтУни\\Java DB Fundamentals\\Databases Frameworks - Hibernate & Spring Data\\11.1 JSON Processing\\people\\src\\main\\resources\\files\\input\\person.json";
    private static final String PERSONS_INPUT_JSON = "E:\\Програмиране\\СофтУни\\Java DB Fundamentals\\Databases Frameworks - Hibernate & Spring Data\\11.1 JSON Processing\\people\\src\\main\\resources\\files\\input\\persons.json";
    private static final String PERSONS_OUTPUT_JSON = "src/main/resources/files/output/json/persons.json";

    private static final String PERSON_INPUT_DIRECTORY = "/files/input/json/";

    private  String fileName = "";



    @Autowired
    @Qualifier(value = "JsonSerializer")
    private Serializer serializer;

    @Autowired
    @Qualifier(value="XMLSerializer")
    private Serializer serializerXMl;


    PhoneNumber phoneNumber;

    Validator validator = Validation
            .buildDefaultValidatorFactory()
            .getValidator();




    @Override
    public void run(String... args) throws Exception {



//        exportPeopleByCountry("Germany",
//                "src/main/resources/files/output/json/personsByCountry.json");


//        exportPersonsByCountry("Germany",
//                "src/main/resources/files/output/json/peopleByCountry.json");

        //  importPersonJSON("peopleByCountry.json");
        // exportByIdXML(1, "testExport.xml");


//        importPersonsJson();
        exportAllXML("testExport.xml");
        //importPersonXML("testImport.xml");

//        importPersonJSON();
//       importPersonsJson();

        //exportPersonByCountry();


    }


    private void importPersonJSON(String fileName) throws IOException, JAXBException {
        PersonExportDto personDto;
        personDto = serializer.importFromFile(PersonExportDto.class, fileName);
        importExportPerson(personDto);

    }

    private void importExportPerson(PersonExportDto personDto) throws IOException {

        Person person = convert(personDto,Person.class);

        personService.create(person);
        System.out.println(personDto.toString());
    }



    private void exportByCountry(String country, String fileName) throws IOException, JAXBException {
        List<Person> bulgarians = personService.findAllByAddressCountry(country);
        List<PersonExportDto> personExportDtos =  convert(bulgarians, PersonExportDto.class);

        serializer.<List<PersonExportDto>>exportToFile(personExportDtos, fileName);
        //serializer.serializeList(personExportDtos,PERSONS_OUTPUT_JSON);


    }



    private void exportPersonsByCountry(String country, String fileName) throws IOException {
        List<Person> bulgarians = personService.findAllByAddressCountry(country);
        List<PersonExportDto> personExportDtos =  convert(bulgarians, PersonExportDto.class);

        serializer.serialize(personExportDtos,fileName);
        //serializer.serializeList(personExportDtos,PERSONS_OUTPUT_JSON);


    }



    private void exportAllPeople()throws IOException{
        List<Person> persons = personService.findAll();
        List<PersonExportDto> personExportDtos =  convert(persons, PersonExportDto.class);
        serializer.serialize(personExportDtos,PERSONS_OUTPUT_JSON);
    }



    private void exportPeopleByCountry(String country, String fileName) throws IOException {
        List<Person> bulgarians = personService.findByCountry(country);
        List<PersonExportDto> personExportDtos =  convert(bulgarians, PersonExportDto.class);

        serializer.serialize(personExportDtos,fileName);
        //serializer.serializeList(personExportDtos,PERSONS_OUTPUT_JSON);


    }

    private void exportPersonByCountry() throws IOException {
        List<Person> bulgarians = personService.findByCountry("Bulgaria");
        List<PersonExportDto> personExportDtos =  convert(bulgarians, PersonExportDto.class);

        serializer.serialize(personExportDtos,PERSONS_OUTPUT_JSON);
        //serializer.serializeList(personExportDtos,PERSONS_OUTPUT_JSON);


    }

    private void importPerson(PersonDto personDto) throws IOException {
//        PersonDto personDto = serializer.deserialize(PersonDto.class, PERSON_INPUT_JSON);
        Person person = convert(personDto,Person.class);
        PersonDto personDto01 = convert(person,PersonDto.class);
        System.out.println(personDto01.toString());
        personService.create(person);


        System.out.println(personDto.toString());
    }


    private void importPersonJSON() throws Exception {
        PersonDto personDto;
        personDto = serializer.deserialize(PersonDto.class, PERSON_INPUT_JSON);
        importPerson(personDto);

    }

    private void importPersonsJson() throws Exception {

        PersonDto[] personDtos = serializer.deserialize(PersonDto[].class,PERSONS_INPUT_JSON);
        for (PersonDto personDto : personDtos) {

//            Set<ConstraintViolation<PersonDto>> constraintViolations =
//                    validator.validate(personDto);
//
//            for (ConstraintViolation<?> violation: constraintViolations) {
//                System.out.format("%10s | %30s | is %10s%n",
//                        violation.getPropertyPath(),
//                        violation.getMessage(),
//                        violation.getInvalidValue()
//                );
//            }
//
//            Set <PhoneNumberDto> phones =  personDto.getPhoneNumberDtos();
//            for (PhoneNumberDto pnDto :phones) {
//                Set<ConstraintViolation<PhoneNumberDto>> constraintViolationsPhone =
//                        validator.validate(pnDto);
//
//                for (ConstraintViolation<?> violation: constraintViolationsPhone) {
//                    System.out.format("%10s | %30s | is %10s%n",
//                            violation.getPropertyPath(),
//                            violation.getMessage(),
//                            violation.getInvalidValue()
//                    );
//                }
//
//            }


            importPerson(personDto);
        }
    }






    private void exportByCountryXML(String country, String fileName) throws IOException, JAXBException {
        List<Person> bulgarians = personService.findAllByAddressCountry(country);
        List<PersonExportDto> personExportDtos =  convert(bulgarians, PersonExportDto.class);

        serializerXMl.<List<PersonExportDto>>exportToFile(personExportDtos, fileName);

    }


    private void exportByIdXML(long id, String fileName) throws IOException, JAXBException {
        Optional <Person> person = Optional.ofNullable(personService.findById(id));

        ModelMapper mapper = new ModelMapper();
        PersonExportDto personExportDto;
        if(person.isPresent()) {
            personExportDto =  mapper.map(person.get(), PersonExportDto.class);
            serializerXMl.exportToFile(personExportDto, fileName);
        }

    }

    private void exportAllXML(String fileName) throws IOException, JAXBException {
        List <Person> persons = personService.findAll();
        PersonsDto personsDto = new PersonsDto();
        List <PersonDto> personDtoList = new ArrayList<>();

        ModelMapper mapper = new ModelMapper();

        if (!persons.isEmpty()) {
            for (Person p : persons) {
                PersonDto personDto = mapper.map(p, PersonDto.class);
                personDto.setAddressImportDto(mapper.map(p.getAddress(), AddressDto.class));
                personDtoList.add(personDto);
            }

            personsDto.setPersonDtos(personDtoList);
            serializerXMl.exportToFile(personsDto, fileName);
        }

    }


    private void importPersonsXML(String filename) throws IOException, JAXBException {
        PersonsDto personsDto;
        personsDto = serializerXMl.importFromFile(PersonsDto.class,filename);
        List<Person> persons = convert(personsDto.getPersonDtos(),Person.class);
        for (Person p:persons) personService.create(p);

    }


    private void importPersonXML(String filename) throws IOException, JAXBException {
        PersonExportDto personDto;
        personDto = serializerXMl.importFromFile(PersonExportDto.class,filename);
        Person person = convert(personDto,Person.class);
        personService.create(person);

    }
}