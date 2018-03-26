package entities;

import javax.persistence.*;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistics {
    @EmbeddedId
    @Column(name = "id")
    PlayerStatisticsId id;

    @OneToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Game game;

    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Player player;

    @Basic
    private int scoredGoals;

    @Basic
    private int playerAssists;

    @Basic
    private int playedMinutesDuringGame;



    public PlayerStatistics() {
    }

    public PlayerStatisticsId getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(int playerAssists) {
        this.playerAssists = playerAssists;
    }

    public int getPlayedMinutesDuringGame() {
        return playedMinutesDuringGame;
    }

    public void setPlayedMinutesDuringGame(int playedMinutesDuringGame) {
        this.playedMinutesDuringGame = playedMinutesDuringGame;
    }
}
