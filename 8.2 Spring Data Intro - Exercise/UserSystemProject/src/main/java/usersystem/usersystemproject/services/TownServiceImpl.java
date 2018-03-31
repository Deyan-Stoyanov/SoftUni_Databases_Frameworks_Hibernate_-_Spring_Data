package usersystem.usersystemproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usersystem.usersystemproject.models.entites.Town;
import usersystem.usersystemproject.repositories.TownRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public void saveTownToDb(Town town) {
        this.townRepository.saveAndFlush(town);
    }

    @Override
    public List<Town> getAllTowns() {
        return this.townRepository.findAll();
    }
}
