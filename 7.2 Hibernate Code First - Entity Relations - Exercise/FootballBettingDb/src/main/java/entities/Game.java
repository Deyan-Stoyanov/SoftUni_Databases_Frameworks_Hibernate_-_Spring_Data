package entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "home_team_id", referencedColumnName = "id")
    private Team homeTeam;

    @OneToOne
    @JoinColumn(name = "away_team_id", referencedColumnName = "id")
    private Team awayTeam;

    @Basic
    private int homeGoals;

    @Basic
    private int awayGoals;

    @Basic
    private Timestamp dateTime;

    @Basic
    private double homeTeamWinBetRate;

    @Basic
    private double awayTeamWinBetRate;

    @Basic
    private double drawWinBetRate;

    @ManyToOne
    @JoinColumn(name = "round_id", referencedColumnName = "id")
    private Round round;

    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;

    public Game(Team homeTeam, Team awayTeam, int homeGoals, int awayGoals, Timestamp dateTime, double homeTeamWinBetRate, double awayTeamWinBetRate, double drawWinBetRate, Round round, Competition competition) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.dateTime = dateTime;
        this.homeTeamWinBetRate = homeTeamWinBetRate;
        this.awayTeamWinBetRate = awayTeamWinBetRate;
        this.drawWinBetRate = drawWinBetRate;
        this.round = round;
        this.competition = competition;
    }

    public Game() {
    }

    public int getId() {
        return id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public double getHomeTeamWinBetRate() {
        return homeTeamWinBetRate;
    }

    public void setHomeTeamWinBetRate(double homeTeamWinBetRate) {
        this.homeTeamWinBetRate = homeTeamWinBetRate;
    }

    public double getAwayTeamWinBetRate() {
        return awayTeamWinBetRate;
    }

    public void setAwayTeamWinBetRate(double awayTeamWinBetRate) {
        this.awayTeamWinBetRate = awayTeamWinBetRate;
    }

    public double getDrawWinBetRate() {
        return drawWinBetRate;
    }

    public void setDrawWinBetRate(double drawWinBetRate) {
        this.drawWinBetRate = drawWinBetRate;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
