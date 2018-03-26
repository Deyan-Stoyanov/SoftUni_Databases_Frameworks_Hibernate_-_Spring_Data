package entities;

import javax.persistence.*;

@Entity
@Table(name = "bet_game")
public class BetGame {

    @EmbeddedId
    private BetGameId id;

    @OneToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Game game;

    @OneToOne
    @JoinColumn(name = "bet_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Bet bet;

    @Basic
    @Column(name = "prediction_result", nullable = false)
    private String resultPrediction;

    public BetGame(BetGameId id, Game game, Bet bet, String resultPrediction) {
        this.id = id;
        this.game = game;
        this.bet = bet;
        this.resultPrediction = resultPrediction;
    }

    public BetGame() {
    }

    public BetGameId getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public String getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(String resultPrediction) {
        this.resultPrediction = resultPrediction;
    }
}
