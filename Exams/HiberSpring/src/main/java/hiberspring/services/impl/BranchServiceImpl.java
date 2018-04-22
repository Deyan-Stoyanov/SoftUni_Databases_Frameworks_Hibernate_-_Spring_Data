package hiberspring.services.impl;

import hiberspring.dtos.imports.BranchImportFromJsonDto;
import hiberspring.dtos.views.branch.BranchViewDto;
import hiberspring.dtos.views.branch.BranchesViewDto;
import hiberspring.entities.Branch;
import hiberspring.entities.Town;
import hiberspring.parser.ValidationUtil;
import hiberspring.repositories.BranchRepository;
import hiberspring.repositories.TownRepository;
import hiberspring.services.BranchService;
import hiberspring.validators.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final TownRepository townRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
    }

    @Override
    public void createOne(BranchImportFromJsonDto branchImportFromJsonDto) {
        if(ValidationUtil.isValid(branchImportFromJsonDto)){
            Branch branch = new Branch();
            branch.setName(branchImportFromJsonDto.getName());
            if(this.townRepository.findByName(branchImportFromJsonDto.getTown()) != null){
                Town town = this.townRepository.findByName(branchImportFromJsonDto.getTown());
                branch.setTown(town);
            }
            this.branchRepository.save(branch);
        } else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public BranchesViewDto getTopBranches() {
        List<BranchViewDto> branches = this.branchRepository.getBranchesBySumOfClients();
        branches.forEach(x -> {
            if(x.getTotalClients() == null){
                x.setTotalClients(0L);
            }
        });
        BranchesViewDto wrapper = new BranchesViewDto(branches);
        return wrapper;
    }
}
