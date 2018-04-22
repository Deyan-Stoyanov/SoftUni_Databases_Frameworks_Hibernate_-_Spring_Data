package photography.workshop.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "workshops")
public class Workshop {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String name;

    @Basic
    private Date startDate;

    @Basic
    private Date endDate;

    @Basic(optional = false)
    private String location;

    @Basic(optional = false)
    private BigDecimal pricePerParticipant;

    @ManyToOne(targetEntity = Photographer.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    private Photographer trainer;

    @ManyToMany(targetEntity = Photographer.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Photographer> participants;

    public Workshop(String name, Date startDate, Date endDate, String location, BigDecimal pricePerParticipant, Photographer trainer, List<Photographer> participants) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.pricePerParticipant = pricePerParticipant;
        this.trainer = trainer;
        this.participants = participants;
    }

    public Workshop() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPricePerParticipant() {
        return pricePerParticipant;
    }

    public void setPricePerParticipant(BigDecimal pricePerParticipant) {
        this.pricePerParticipant = pricePerParticipant;
    }

    public Photographer getTrainer() {
        return trainer;
    }

    public void setTrainer(Photographer trainer) {
        this.trainer = trainer;
    }

    public List<Photographer> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Photographer> participants) {
        this.participants = participants;
    }
}

