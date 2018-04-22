package app.retake;

import app.retake.controllers.AnimalAidController;
import app.retake.controllers.AnimalController;
import app.retake.controllers.ProcedureController;
import app.retake.controllers.VetController;
import app.retake.io.api.ConsoleIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {
    private final AnimalAidController animalAidController;
    private final AnimalController animalController;
    private final ProcedureController procedureController;
    private final VetController vetController;

    private final ConsoleIO consoleIO;

    @Autowired
    public Terminal(AnimalAidController animalAidController, AnimalController animalController, ProcedureController procedureController, VetController vetController, ConsoleIO consoleIO) {
        this.animalAidController = animalAidController;
        this.animalController = animalController;
        this.procedureController = procedureController;
        this.vetController = vetController;
        this.consoleIO = consoleIO;
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(this.animalAidController.importDataFromJSON(Config.ANIMAL_AIDS_IMPORT_JSON));
        System.out.println(this.animalController.importDataFromJSON(Config.ANIMALS_IMPORT_JSON));
        System.out.println(this.vetController.importDataFromXML(Config.VETS_IMPORT_XML));
        System.out.println(this.procedureController.importDataFromXML(Config.PROCEDURES_IMPORT_XML));

        this.consoleIO.write(this.animalController.exportAnimalsByOwnerPhoneNumber("0887446123"));
        this.consoleIO.write(this.procedureController.exportProcedures());
    }
}
