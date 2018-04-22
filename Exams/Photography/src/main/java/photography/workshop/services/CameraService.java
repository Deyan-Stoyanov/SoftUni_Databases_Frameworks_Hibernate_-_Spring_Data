package photography.workshop.services;

import javafx.scene.Camera;
import photography.workshop.model.entity.BasicCamera;

import java.util.List;
import java.util.Optional;

public interface CameraService {
    List<BasicCamera> getAll();
    void save(BasicCamera basicCamera);
    void save(List<BasicCamera> basicCameras);
    Optional<BasicCamera> getBasicCameraById(Long id);
}
