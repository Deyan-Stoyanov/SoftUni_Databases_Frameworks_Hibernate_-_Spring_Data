package hiberspring.services;

import hiberspring.dtos.imports.BranchImportFromJsonDto;
import hiberspring.dtos.views.branch.BranchesViewDto;
import hiberspring.entities.Branch;

public interface BranchService {

    void createOne(BranchImportFromJsonDto branchImportFromJsonDto);

    BranchesViewDto getTopBranches();
}
