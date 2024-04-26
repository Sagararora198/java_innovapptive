package ipl;

import java.util.ArrayList;
import java.util.List;

public class Player {
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
    public void recordPerformance(int runs, int wickets, Match match) throws PerformanceRecordedException {
        if (match.hasRecordedPerformance(this)){
            throw new PerformanceRecordedException();
        }
        else{
            PlayerStats stats = new PlayerStats(runs,wickets);
            matchPerformances.add(stats);
            match.recordStats(this,stats);
        }


    }
    public int getTotalRuns(){
        int totalRuns =0;
        for(PlayerStats stats:matchPerformances){
            totalRuns += stats.runs;
        }
        return totalRuns;
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
