package ipl;

import java.util.HashMap;
import java.util.Map;

public class Match {
    private Team team1;
    private Team team2;
    private Map<Player, PlayerStats> playerStats = new HashMap<>();
    private Team winner;
    private MatchType matchType;

    public Match(Team team1, Team team2,MatchType matchType) {
        this.team1 = team1;
        this.team2 = team2;
        this.matchType = matchType;
    }

    /** to set the winner of match
     *
     * @param team team that won the match
     */
    public void setWinner(Team team){
        this.winner = team;
    }

    /** record status of the player
     *
     * @param player player which scored
     * @param stats status of player
     */
    public void recordStats(Player player, PlayerStats stats) throws PerformanceRecordedException {
        if(playerStats.containsKey(player)){
            throw new PerformanceRecordedException();
        }
        playerStats.put(player,stats);
    }

    /** to check if the player performance is already recorded in a match or not
     *
     * @param player player whose performance is to be recorded
     * @return true if it has already recorded else false
     */
    public boolean hasRecordedPerformance(Player player) {
        return playerStats.containsKey(player);
    }
}
