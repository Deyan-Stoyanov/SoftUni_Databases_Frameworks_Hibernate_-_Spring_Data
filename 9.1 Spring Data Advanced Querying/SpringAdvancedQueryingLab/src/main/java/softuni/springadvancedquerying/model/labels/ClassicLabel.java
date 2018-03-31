package softuni.springadvancedquerying.model.labels;

import softuni.springadvancedquerying.model.shampoos.BasicShampoo;

import javax.persistence.*;

@Entity
@Table(name = "labels")

public class ClassicLabel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String title;

    @Basic
    private String subtitle;

    @OneToOne(mappedBy = "label", targetEntity = BasicShampoo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BasicShampoo shampoo;

    public ClassicLabel(String title, String subtitle, BasicShampoo shampoo) {
        this.title = title;
        this.subtitle = subtitle;
        this.shampoo = shampoo;
    }

    public ClassicLabel(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public ClassicLabel() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public BasicShampoo getShampoo() {
        return shampoo;
    }

    public void setShampoo(BasicShampoo shampoo) {
        this.shampoo = shampoo;
    }
}
