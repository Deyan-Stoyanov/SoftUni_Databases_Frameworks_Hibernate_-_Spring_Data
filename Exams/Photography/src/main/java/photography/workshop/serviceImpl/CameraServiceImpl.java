package photography.workshop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photography.workshop.model.entity.BasicCamera;
import photography.workshop.repositories.CameraRepository;
import photography.workshop.services.CameraService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CameraServiceImpl implements CameraService {
    private final CameraRepository cameraRepository;

    @Autowired
    public CameraServiceImpl(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    @Override
    public List<BasicCamera> getAll() {
        return this.cameraRepository.findAll();
    }

    @Override
    public void save(BasicCamera basicCamera) {
        this.cameraRepository.saveAndFlush(basicCamera);
    }

    @Override
    public void save(List<BasicCamera> basicCameras) {
        this.cameraRepository.saveAll(basicCameras);
    }

    @Override
    public Optional<BasicCamera> getBasicCameraById(Long id) {
        return this.cameraRepository.findById(id);
    }
}
