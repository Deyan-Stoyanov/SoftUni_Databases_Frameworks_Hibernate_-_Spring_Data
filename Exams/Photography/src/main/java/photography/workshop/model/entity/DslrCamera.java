package photography.workshop.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue(value = "DSLR")
public class DslrCamera extends BasicCamera{

    @Basic
    private Integer maxShutterSpeed;

    public DslrCamera(String make, String model, boolean isFullFrame, Integer minIso, Integer maxIso, Integer maxShutterSpeed) {
        super(make, model, isFullFrame, minIso, maxIso);
        this.maxShutterSpeed = maxShutterSpeed;
    }

    public DslrCamera(String make, String model, boolean isFullFrame, Integer minIso, Integer maxIso) {
        super(make, model, isFullFrame, minIso, maxIso);
    }

    public DslrCamera() {
    }

    public DslrCamera(Integer maxShutterSpeed) {
        this.maxShutterSpeed = maxShutterSpeed;
    }

    public Integer getMaxShutterSpeed() {
        return maxShutterSpeed;
    }

    public void setMaxShutterSpeed(Integer maxShutterSpeed) {
        this.maxShutterSpeed = maxShutterSpeed;
    }
}
