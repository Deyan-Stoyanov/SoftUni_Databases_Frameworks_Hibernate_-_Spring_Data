package entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String name;

    @Basic
    private String initials;

    @ManyToOne
    @JoinColumn(name = "primary_color_id", referencedColumnName = "id")
    private Color primaryKitColor;

    @ManyToOne
    @JoinColumn(name = "secondary_color_id", referencedColumnName = "id")
    private Color secondaryKitColor;

    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town town;

    @Basic
    private BigDecimal budget;

    public Team(String name, String initials, Color primaryKitColor, Color secondaryKitColor, Town town, BigDecimal budget) {
        this.name = name;
        this.initials = initials;
        this.primaryKitColor = primaryKitColor;
        this.secondaryKitColor = secondaryKitColor;
        this.town = town;
        this.budget = budget;
    }

    public Team() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Color getPrimaryKitColor() {
        return primaryKitColor;
    }

    public void setPrimaryKitColor(Color primaryKitColor) {
        this.primaryKitColor = primaryKitColor;
    }

    public Color getSecondaryKitColor() {
        return secondaryKitColor;
    }

    public void setSecondaryKitColor(Color secondaryKitColor) {
        this.secondaryKitColor = secondaryKitColor;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
