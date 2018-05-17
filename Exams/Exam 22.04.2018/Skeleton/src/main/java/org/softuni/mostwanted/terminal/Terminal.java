package org.softuni.mostwanted.terminal;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.controlers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class Terminal implements CommandLineRunner {
    private final TownController townController;
    private final DistrictController districtController;
    private final RacerController racerController;
    private final CarController carController;
    private final RaceController raceController;
    private final RaceEntryController raceEntryController;

    @Autowired
    public Terminal(TownController townController, DistrictController districtController, RacerController racerController, CarController carController, RaceController raceController, RaceEntryController raceEntryController) {
        this.townController = townController;
        this.districtController = districtController;
        this.racerController = racerController;
        this.carController = carController;
        this.raceController = raceController;
        this.raceEntryController = raceEntryController;
    }

    @Override
    public void run(String... args) throws Exception {

        importData();
        exportData();
    }

    private void exportData() {
        this.townController.exportDataToJSON();
        this.racerController.exportDataToJSON();
        this.racerController.exportDataToXML();
    }

    private void importData(){
        this.townController.importDataFromJSON(Config.TOWNS_IMPORT_JSON);
        this.districtController.importDataFromJSON(Config.DISTRICTS_IMPORT_JSON);
        this.racerController.importDataFromJSON(Config.RACERS_IMPORT_JSON);
        this.carController.importDataFromJSON(Config.CARS_IMPORT_JSON);
        this.raceEntryController.importDataFromXML(Config.RACE_ENTRIES_IMPORT_XML);
        this.raceController.importDataFromXML(Config.RACES_IMPORT_XML);
    }
}
