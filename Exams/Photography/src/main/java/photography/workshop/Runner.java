package photography.workshop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import photography.workshop.io.JSON.JSONParser;
import photography.workshop.io.JSON.JsonParserImpl;
import photography.workshop.io.parser.XmlParser;
import photography.workshop.io.reader.Reader;
import photography.workshop.io.validator.ValidationUtil;
import photography.workshop.io.writer.Writer;
import photography.workshop.model.dto.binding.CameraBindingModel;
import photography.workshop.model.dto.binding.PhotographerBindingModel;
import photography.workshop.model.entity.*;
import photography.workshop.services.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class Runner implements CommandLineRunner {
    private final AccessoryService accessoryService;
    private final WorkshopService workshopService;
    private final PhotographerService photographerService;
    private final LensService lensService;
    private final CameraService cameraService;

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final JSONParser jsonParser;
    private final XmlParser xmlParser;
    private final Reader reader;
    private final Writer writer;

    @Autowired
    public Runner(AccessoryService accessoryService, WorkshopService workshopService, PhotographerService photographerService, LensService lensService, CameraService cameraService,JSONParser jsonParser, XmlParser xmlParser, Reader reader, Writer writer) {
        this.accessoryService = accessoryService;
        this.workshopService = workshopService;
        this.photographerService = photographerService;
        this.lensService = lensService;
        this.cameraService = cameraService;
        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .setPrettyPrinting()
                        .create();
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedLenses();
//        seedCameras();
        seedPhotographers();
    }

    private void seedPhotographers() throws IOException {
        InputStream photographerStream = jsonParser.loadData("/input/photographers.json");
        String photographerData = jsonParser.readAllData(photographerStream);
        Type listType = new TypeToken<List<PhotographerBindingModel>>(){}.getType();
        List<PhotographerBindingModel> models = this.gson.fromJson(photographerData, listType);
        Random random = new Random();
        for (PhotographerBindingModel m: models) {
            if(m.getFirstName() == null || m.getLastName() == null){
                System.out.println("Error. Invalid data provided");
            } else {
                    Photographer photographer = new Photographer();
                try {
                    photographer.setFirstName(m.getFirstName());
                    photographer.setLastName(m.getLastName());
                    photographer.setPhone(m.getPhone());
                }catch (Exception e) {
                    exceptionHandler();
                    continue;
                }
                    long firstCameraRandomNum = random.nextInt(328);
                    long secondCameraRandomNum = random.nextInt(328);
                    if (cameraService.getBasicCameraById(firstCameraRandomNum).isPresent()) {
                        photographer.setPrimaryCamera(cameraService.getBasicCameraById(firstCameraRandomNum).get());
                    }
                    if (cameraService.getBasicCameraById(secondCameraRandomNum).isPresent()) {
                        photographer.setSecondaryCamera(cameraService.getBasicCameraById(secondCameraRandomNum).get());
                    }
                    photographer.setLenses(new ArrayList<>());
                    for (Long l : m.getLenses()) {
                        if (this.lensService.getById(l).isPresent() && photographer.getPrimaryCamera() != null && photographer.getSecondaryCamera() != null) {
                            if (this.lensService.getById(l).get().getCompatibleWith().equalsIgnoreCase(photographer.getPrimaryCamera().getMake()) || this.lensService.getById(l).get().getCompatibleWith().equalsIgnoreCase(photographer.getSecondaryCamera().getMake())) {
                                photographer.getLenses().add(this.lensService.getById(l).get());
                            }
                        }
                    }
                    if(ValidationUtil.isValid(photographer)){
                        this.photographerService.save(photographer);
                        System.out.printf("Successfully imported %s %s | Lenses: %d", photographer.getFirstName(), photographer.getLastName(), photographer.getLenses().size());
                    }
            }
        }
    }

    @ExceptionHandler
    private void exceptionHandler() {
        System.out.println("Error");
    }

    private void seedCameras() throws IOException {
        InputStream cameraStream = jsonParser.loadData("/input/cameras.json");
        String cameraData = jsonParser.readAllData(cameraStream);
        Type listType = new TypeToken<List<CameraBindingModel>>(){}.getType();
        List<CameraBindingModel> models = this.gson.fromJson(cameraData, listType);
        for (CameraBindingModel m:models) {
            if(m.getType() == null || m.getMake() == null || m.getModel() == null || m.getMinIso() == null){
                System.out.println("Error. Invalid data provided");
            } else {
                if (m.getType().equalsIgnoreCase("DSLR")) {
                    DslrCamera dslrCamera = this.modelMapper.map(m, DslrCamera.class);
                    this.cameraService.save(dslrCamera);
                } else if (m.getType().equalsIgnoreCase("Mirrorless")) {
                    MirrorlessCamera mirrorlessCamera = this.modelMapper.map(m, MirrorlessCamera.class);
                    this.cameraService.save(mirrorlessCamera);
                }
                System.out.printf("Successfully imported %s %s %s%n", m.getType(), m.getMake(), m.getModel());
            }
        }
    }

    private void seedLenses() throws IOException {
        InputStream lensStream = jsonParser.loadData("/input/lenses.json");
        String lensData = jsonParser.readAllData(lensStream);
        Type listType = new TypeToken<List<Lens>>(){}.getType();
        List<Lens> lenses = this.gson.fromJson(lensData, listType);
        for (Lens l:lenses) {
            System.out.printf("Successfully imported %s %dmm f%.1f%n", l.getMake(), l.getFocalLength(), l.getMaxAperture());
            this.lensService.save(l);
        }
    }
}
