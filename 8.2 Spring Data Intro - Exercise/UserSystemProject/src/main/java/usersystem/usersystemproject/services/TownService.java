package usersystem.usersystemproject.services;

import usersystem.usersystemproject.models.entites.Town;

import java.util.List;

public interface TownService {
    void saveTownToDb(Town town);
    List<Town> getAllTowns();
}
