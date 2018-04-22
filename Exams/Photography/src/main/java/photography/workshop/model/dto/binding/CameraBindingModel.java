package photography.workshop.model.dto.binding;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CameraBindingModel {
    @Expose
    private String type;
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private boolean isFullFrame;
    @Expose
    @SerializedName(value = "minISO")
    private Integer minIso;
    @Expose
    @SerializedName(value = "maxISO")
    private Integer maxIso;
    @Expose
    @SerializedName(value = "MaxShutterSpeed")
    private Integer maxShutterSpeed;
    @Expose
    private String maxVideoResolution;
    @Expose
    private Integer maxFrameRate;

    public CameraBindingModel(String type, String make, String model, boolean isFullFrame, Integer minIso, Integer maxIso, Integer maxShutterSpeed, String maxVideoResolution, Integer maxFrameRate) {
        this.type = type;
        this.make = make;
        this.model = model;
        this.isFullFrame = isFullFrame;
        this.minIso = minIso;
        this.maxIso = maxIso;
        this.maxShutterSpeed = maxShutterSpeed;
        this.maxVideoResolution = maxVideoResolution;
        this.maxFrameRate = maxFrameRate;
    }

    public CameraBindingModel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isFullFrame() {
        return isFullFrame;
    }

    public void setFullFrame(boolean fullFrame) {
        isFullFrame = fullFrame;
    }

    public Integer getMinIso() {
        return minIso;
    }

    public void setMinIso(Integer minIso) {
        this.minIso = minIso;
    }

    public Integer getMaxIso() {
        return maxIso;
    }

    public void setMaxIso(Integer maxIso) {
        this.maxIso = maxIso;
    }

    public Integer getMaxShutterSpeed() {
        return maxShutterSpeed;
    }

    public void setMaxShutterSpeed(Integer maxShutterSpeed) {
        this.maxShutterSpeed = maxShutterSpeed;
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
