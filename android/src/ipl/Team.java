package ipl;

import java.util.ArrayList;
import java.util.List;

public class Team {
    String name;
    String homeGround;
    List<Player> players = new ArrayList<>();
    int points = 0;
    int maxPlayer;


    public Team(String name, String homeGround,TournamentType tournamentType) {
        this.name = name;
        this.homeGround = homeGround;
        this.maxPlayer = (tournamentType==TournamentType.IPL)?12:11;
    }

    /** add player in a team
     *
     * @param player
     */
    public void addPlayer(Player player) throws TeamSizeException {
        if(players.size()<maxPlayer){
            players.add(player);
        }
        else{
            throw new TeamSizeException();
        }

    }

    /** to update the points of a team
     *
     * @param points points scored by the team
     */
    public void updatePoint(int points){
        this.points += points;

    }
}
