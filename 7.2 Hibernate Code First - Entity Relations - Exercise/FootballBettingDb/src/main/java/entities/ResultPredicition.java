package entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "result_predictions")
public class ResultPredicition {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    private String prediction;

    public ResultPredicition(String prediction) {
        this.prediction = prediction;
    }

    public ResultPredicition() {
    }

    public int getId() {
        return id;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
}
