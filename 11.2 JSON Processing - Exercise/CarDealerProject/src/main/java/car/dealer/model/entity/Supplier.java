package car.dealer.model.entity;


import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Expose
    private String name;

    @Basic
    @Expose
    private boolean isImporter;

    public Supplier(String name, boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    public Supplier() {
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

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        this.isImporter = importer;
    }
}
