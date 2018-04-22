package photography.workshop.model.entity;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue(value = "Mirrorless")
public class MirrorlessCamera extends BasicCamera{

    @Column(columnDefinition = "TEXT")
    private String maxVideoResolution;

    @Basic
    private Integer maxFrameRate;

    public MirrorlessCamera(String make, String model, boolean isFullFrame, Integer minIso, Integer maxIso, String maxVideoResolution, Integer maxFrameRate) {
        super(make, model, isFullFrame, minIso, maxIso);
        this.maxVideoResolution = maxVideoResolution;
        this.maxFrameRate = maxFrameRate;
    }

    public MirrorlessCamera(String maxVideoResolution, Integer maxFrameRate) {
        this.maxVideoResolution = maxVideoResolution;
        this.maxFrameRate = maxFrameRate;
    }

    public MirrorlessCamera() {
    }

    public String getMaxVideoResolution() {
        return maxVideoResolution;
    }

    public void setMaxVideoResolution(String maxVideoResolution) {
        this.maxVideoResolution = maxVideoResolution;
    }

    public Integer getMaxFrameRate() {
        return maxFrameRate;
    }

    public void setMaxFrameRate(Integer maxFrameRate) {
        this.maxFrameRate = maxFrameRate;
    }
}
