package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;

@Embeddable
public class BetGameId implements Serializable{
    @Column(name="game_id")
    private int gameId;

    @Column(name="bet_id")
    private int betId;

    public BetGameId(int gameId, int betId) {
        this.gameId = gameId;
        this.betId = betId;
    }

    public BetGameId() {
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getBetId() {
        return betId;
    }

    public void setBetId(int betId) {
        this.betId = betId;
    }
}
