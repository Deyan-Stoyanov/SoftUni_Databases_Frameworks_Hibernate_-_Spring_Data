package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;
import com.masdefect.domain.entities.Planet;

import java.io.Serializable;

public class AnomalyExportJSONDto implements Serializable {
    @Expose
    private Integer id;
    @Expose
    private Planet originPlanet;
    @Expose
    private Planet teleportPlanet;
    @Expose
    private Long victimsCount;

    public AnomalyExportJSONDto(Integer id, Planet originPlanet, Planet teleportPlanet, Long victimsCount) {
        this.id = id;
        this.originPlanet = originPlanet;
        this.teleportPlanet = teleportPlanet;
        this.victimsCount = victimsCount;
    }

    public AnomalyExportJSONDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Planet getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(Planet originPlanet) {
        this.originPlanet = originPlanet;
    }

    public Planet getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(Planet teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }

    public Long getVictimsCount() {
        return victimsCount;
    }

    public void setVictimsCount(Long victimsCount) {
        this.victimsCount = victimsCount;
    }
}
