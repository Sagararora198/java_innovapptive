package ipl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class CricketTournament {
    private List<Team> teams = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();
    private TournamentType tournamentType;
    private MatchType matchType;

    public CricketTournament(TournamentType tournamentType, MatchType matchType) {
        this.tournamentType = tournamentType;
        this.matchType = matchType;
    }

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

    /** to set the match winner
     *
     * @param winner winner of the match
     * @param match
     */
    public void setMatchWinner(Team winner,Match match){
        match.setWinner(winner);
        winner.updatePoint(2);

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

    /** to get the list of semifinalists by calculating the maximum four points from team
     *
     * @return list of semifinalists
     */
    public List<Team> getSemiFinalists() {
        // Sort teams by points and return the top 4
        return teams.stream()
                .sorted((team1, team2) -> Integer.compare(team2.points, team1.points))
                .limit(4)
                .collect(Collectors.toList());
    }

    /** to get the top scorer in the tournament
     *
     * @return player with maximum score
     */
    public List<Player> getTopRunScorers() {

        PriorityQueue<Player> playersQueue = new PriorityQueue<>((player1, player2) -> Integer.compare(player2.getTotalRuns(), player1.getTotalRuns()));

        // Add all players to the priority queue
        for (Team team : teams) {
            for (Player player : team.players) {
                playersQueue.add(player);
            }
        }

        // Retrieve the top 5 run scorers
        List<Player> topRunScorers = new ArrayList<>();
        for (int i = 0; i < 5 && !playersQueue.isEmpty(); i++) {
            topRunScorers.add(playersQueue.poll());
        }

        return topRunScorers;
    }

    /** to get the tournament winner by comparing the maximum points from the tournament
     *
     * @return Team with maximum score
     */
    public Team getTournamentWinner() {
        return teams.stream()
                .max(Comparator.comparingInt(team -> team.points))
                .orElse(null); // In case there are no teams
    }



}
