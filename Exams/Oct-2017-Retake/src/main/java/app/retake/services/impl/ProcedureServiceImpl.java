package app.retake.services.impl;

import app.retake.domain.dto.*;
import app.retake.domain.models.Animal;
import app.retake.domain.models.AnimalAid;
import app.retake.domain.models.Procedure;
import app.retake.parser.ValidationUtil;
import app.retake.repositories.*;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final VetRepository vetRepository;
    private final AnimalRepository animalRepository;
    private final AnimalAidRepository animalAidRepository;
    private final PassportRepository passportRepository;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository, VetRepository vetRepository, AnimalRepository animalRepository, AnimalAidRepository animalAidRepository, PassportRepository passportRepository) {
        this.procedureRepository = procedureRepository;
        this.vetRepository = vetRepository;
        this.animalRepository = animalRepository;
        this.animalAidRepository = animalAidRepository;
        this.passportRepository = passportRepository;
    }

    @Override
    public void create(ProcedureXMLImportDTO dto) throws ParseException {
        if(ValidationUtil.isValid(dto)){
            Procedure procedure = new Procedure();
            if(this.vetRepository.findByName(dto.getVet()) != null){
                procedure.setVet(this.vetRepository.findByName(dto.getVet()));
            }
            if(this.passportRepository.findBySerialNumber(dto.getAnimal()) != null){
                procedure.setAnimal(this.passportRepository.findBySerialNumber(dto.getAnimal()).getAnimal());
            }
            List<AnimalAid> aids = new ArrayList<>();
            for (ProcedureAnimalAidXMLImportDTO aidDto:dto.getAnimalAids()) {
                if(this.animalAidRepository.findByName(aidDto.getVet()) != null){
                    aids.add(this.animalAidRepository.findByName(aidDto.getVet()));
                }
            }
            procedure.setServices(aids);
            procedure.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDate()));
            this.procedureRepository.saveAndFlush(procedure);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {
        List<Procedure> allProcedures = this.procedureRepository.getAll();
        List<ProcedureXMLExportDTO> allDtos = new ArrayList<>();
        for (Procedure p:allProcedures) {
            ProcedureXMLExportDTO dto = new ProcedureXMLExportDTO();
            dto.setAnimalPassport(p.getAnimal().getPassport().getSerialNumber());
            dto.setOwnerPhoneNumber(p.getAnimal().getPassport().getOwnerPhoneNumber());
            List<ProcedureAnimalAidXMLExportDTO> animalAidsDtos = this.animalAidRepository.getAllAnimalAidsByProcedureId(p.getId());
            dto.setAnimalAids(animalAidsDtos);
            allDtos.add(dto);
        }
        allDtos = allDtos.stream().sorted((x, y) -> {
            BigDecimal totalPriceOfX = BigDecimal.ZERO;
            BigDecimal totalPriceOfY = BigDecimal.ZERO;
            for (ProcedureAnimalAidXMLExportDTO aid:x.getAnimalAids()) {
                totalPriceOfX = totalPriceOfX.add(aid.getPrice());
            }
            for (ProcedureAnimalAidXMLExportDTO aid:y.getAnimalAids()) {
                totalPriceOfY = totalPriceOfY.add(aid.getPrice());
            }
            return totalPriceOfX.compareTo(totalPriceOfY);
        }).collect(Collectors.toList());
        ProcedureWrapperXMLExportDTO wrapper = new ProcedureWrapperXMLExportDTO(allDtos);
        return wrapper;
    }
}

