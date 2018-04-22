package com.masdefect.terminal;

import com.masdefect.config.Config;
import com.masdefect.controller.*;
import com.masdefect.io.interfaces.ConsoleIO;
import com.masdefect.io.interfaces.FileIO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {
    private final SolarSystemController solarSystemController;
    private final StarsController starsController;
    private final PersonsController personsController;
    private final PlanetsController planetsController;
    private final AnomalyController anomalyController;
    private final AnomalyVictimsController anomalyVictimsController;
    private final ConsoleIO consoleIO;
    private final FileIO fileIO;

    public Terminal(SolarSystemController solarSystemController, StarsController starsController, PersonsController personsController, PlanetsController planetsController, AnomalyController anomalyController, AnomalyVictimsController anomalyVictimsController, ConsoleIO consoleIO, FileIO fileIO) {
        this.solarSystemController = solarSystemController;
        this.starsController = starsController;
        this.personsController = personsController;
        this.planetsController = planetsController;
        this.anomalyController = anomalyController;
        this.anomalyVictimsController = anomalyVictimsController;
        this.consoleIO = consoleIO;
        this.fileIO = fileIO;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.solarSystemController.importDataFromJSON(this.fileIO.read(Config.SOLAR_SYSTEM_IMPORT_JSON)));
        System.out.println(this.starsController.importDataFromJSON(this.fileIO.read(Config.STARS_IMPORT_JSON)));
        System.out.println(this.planetsController.importDataFromJSON(this.fileIO.read(Config.PLANETS_IMPORT_JSON)));
        System.out.println(this.personsController.importDataFromJSON(this.fileIO.read(Config.PERSONS_IMPORT_JSON)));

        System.out.println(this.anomalyVictimsController.importDataFromJSON(this.fileIO.read(Config.ANOMALY_VICTIMS_IMPORT_JSON)));
        System.out.println(this.anomalyController.importDataFromXML(this.fileIO.read(Config.ANOMALIES_IMPORT_XML)));

        System.out.println(this.planetsController.planetsWithNoPeopleTeleportedToThem());
        System.out.println(this.anomalyController.findAnomalyWithMostVictims());
        System.out.println(this.anomalyController.exportAnomaliesOrdered());

    }
}
