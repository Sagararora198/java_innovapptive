//This file consist of a  program to manage cricket tournament


/** approach -
 * 1] CricketTournament
 *  the cricket tournament class will handel the matches and teams
 *  one can add a team to the tournament and schedule match in that tournament
 *  performance status of a player in the overall tournament will be calculated here
 *  2] Team
 *  the team class will manage team info such as
 *  name of the team and home ground of the team
 *  a team will also have players which can be added to a team
 *
 *  3] Player
 *  Player class will manage the player info such as player name
 *  it will also contain the player match performance in which all the performance of player in different matches will be stored
 *  also the total wicket taken by a player and total fifty scored by a player will be calculated
 *
 *  4] Match
 *  Match class will have the match info such as between which team the match is fixed
 *  it will also have status record for each match for each player which will map to the player status
 *
 *  5] PlayerStats
 *  player status class will have info like number of runs a player scored adn number of wicket the player took
 *
 */

import java.util.*;

class CricketTournament {

    private List<Team> teams = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();

    /** add teams to the tournament
     *
     * @param team object of class team to add in the tournament
     */
    public void addTeam(Team team) {
        teams.add(team);
    }

    /** schedule a match in tournament
     *
     * @param match match to schedule
     */
    public void scheduleMatch(Match match) {

        matches.add(match);
    }

    /** returns the highest wicket taker of the tournament
     *
     * @return Player with highest wicket
     */
    public Player getHighestWicketTaker() {
        Player highestWicketTaker = null;
        int maxWickets = 0;
        for (Team team : teams) {
            for (Player player : team.players) {
                int wickets = player.getTotalWickets();
                if (wickets > maxWickets) {
                    maxWickets = wickets;
                    highestWicketTaker = player;
                }
            }
        }
        return highestWicketTaker;
    }

    /** to return the maximum number of fifties a player has scored
     *
     * @return Player
     */
    public Player getMaximumFiftiesPlayer() {
        Player maxFiftiesPlayer = null;
        int maxFifties = 0;
        for (Team team : teams) {
            for (Player player : team.players) {
                int fifties = player.getFifties();
                if (fifties > maxFifties) {
                    maxFifties = fifties;
                    maxFiftiesPlayer = player;
                }
            }
        }
        return maxFiftiesPlayer;
    }
}

class Team {
    String name;
    String homeGround;
    List<Player> players = new ArrayList<>();
    int points = 0;


    public Team(String name, String homeGround) {
        this.name = name;
        this.homeGround = homeGround;
    }

    /** add player in a team
     *
     * @param player
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

}

class Player {
    private String name;
    private List<PlayerStats> matchPerformances = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    /** record performance of a player
     *
     * @param runs runs of the player
     * @param wickets wickets taken by the player
     * @param match match in which the stat is recorded
     */
    public void recordPerformance(int runs, int wickets,Match match) {
        PlayerStats stats = new PlayerStats(runs,wickets);
        matchPerformances.add(stats);
        match.recordStats(this,stats);

    }

    /** to count the total wickets a player has taken
     *
     * @return total wickets by the player
     */
    public int getTotalWickets() {
        int totalWickets = 0;
        for (PlayerStats stats : matchPerformances) {
            totalWickets += stats.wickets;
        }
        return totalWickets;
    }

    /** to count the total number of fifty a player has scored
     *
     * @return total number of fifty a player has scored
     */
    public int getFifties() {
        int fifties = 0;
        for (PlayerStats stats : matchPerformances) {
            if (stats.runs >= 50 && stats.runs < 100) {
                fifties++;
            }
        }
        return fifties;
    }

    /** To return the name of the player whenever object is printed
     *
     * @return name of the player
     */
    @Override
    public String toString(){
        return name;
    }

}

class Match {
    private Team team1;
    private Team team2;
    private Map<Player, PlayerStats> playerStats = new HashMap<>();

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    /** record status of the player
     *
     * @param player player which scored
     * @param stats status of player
     */
    public void recordStats(Player player, PlayerStats stats) {
        playerStats.put(player,stats);
    }
}

class PlayerStats {
    int runs;
    int wickets;

    public PlayerStats(int runs, int wickets) {
        this.runs = runs;
        this.wickets = wickets;
    }
}

public class IPLSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CricketTournament ipl = new CricketTournament();

        // Creating teams
        Team teamA = new Team("Team A", "Ground A");
        Team teamB = new Team("Team B", "Ground B");

        // Adding players to teams
        Player player1 = new Player("Player 1");
        teamA.addPlayer(player1);

        Player player2 = new Player("Player 2");
        teamB.addPlayer(player2);


        // Adding teams to tournament
        ipl.addTeam(teamA);
        ipl.addTeam(teamB);

        // creating a match
        Match match1 = new Match(teamA, teamB);
        Match match2 = new Match(teamA,teamB);

        //sheduling match to tournment
        ipl.scheduleMatch(match1);
        ipl.scheduleMatch(match2);


        // adding player performance in match

        player1.recordPerformance(60, 2,match1); // 1 fifty
        player1.recordPerformance(55, 5,match2); // 5 wickets in a match



        player2.recordPerformance(80, 1,match1); // 1 fifty
        player2.recordPerformance(10, 7,match2); // 7 wickets in a match



        // Ask the user for the query
        System.out.println("Enter your query:");
        System.out.println("1 for Highest Wicket Taker");
        System.out.println("2 for Player with Maximum 50s");
        int query = scanner.nextInt();

        switch (query) {
            case 1:
                Player highestWicketTaker = ipl.getHighestWicketTaker();
                System.out.println("Highest Wicket Taker: " + highestWicketTaker + ", No of wickets: " + highestWicketTaker.getTotalWickets());
                break;
            case 2:
                Player maxFiftiesPlayer = ipl.getMaximumFiftiesPlayer();
                System.out.println("Player with Maximum 50s: " + maxFiftiesPlayer + ", Number of 50s: " + maxFiftiesPlayer.getFifties());
                break;
            default:
                System.out.println("Invalid query. Please enter 1 or 2.");
                break;
        }

        scanner.close();

    }
}