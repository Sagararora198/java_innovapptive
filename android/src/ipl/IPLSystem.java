package ipl;

import java.util.List;
import java.util.Scanner;

public class IPLSystem {
    public static void main(String[] args) {

        CricketTournament ipl = new CricketTournament(TournamentType.IPL,MatchType.T20);

        // Creating teams
        Team teamA = new Team("Mumbai Indians", "Wankhede Stadium",TournamentType.IPL);
        Team teamB = new Team("Chennai Super Kings", "M. A. Chidambaram Stadium",TournamentType.IPL);

        // Adding players to teams
        try {
            teamA.addPlayer(new Player("Rohit Sharma"));
            teamA.addPlayer(new Player("Hardik Pandya"));
            teamA.addPlayer(new Player("Kieron Pollard"));
            teamA.addPlayer(new Player("Jasprit Bumrah"));
            teamA.addPlayer(new Player("Ishan Kishan"));
            teamA.addPlayer(new Player("Suryakumar Yadav"));
            teamA.addPlayer(new Player("Trent Boult"));
            teamA.addPlayer(new Player("Rahul Chahar"));
            teamA.addPlayer(new Player("Quinton de Kock"));
            teamA.addPlayer(new Player("Chris Lynn"));
            teamA.addPlayer(new Player("Anmolpreet Singh"));
            teamA.addPlayer(new Player("Aditya Tare"));
        } catch (TeamSizeException e) {
            System.out.println(e.getMessage());
        }


            Player MS_DHONI = new Player("MS Dhoni");
        try {
            teamB.addPlayer(MS_DHONI);
        } catch (TeamSizeException e) {
            e.printStackTrace();
        }
        Player Suresh_Raina = new Player("Suresh Raina");
        try {
            teamB.addPlayer(Suresh_Raina);
        } catch (TeamSizeException e) {
            e.printStackTrace();
        }
        Player Ravindra_Jadeja = new Player("Ravindra Jadeja");
        try {
            teamB.addPlayer(Ravindra_Jadeja);
        } catch (TeamSizeException e) {
            e.printStackTrace();
        }
        Player Faf = new Player("Faf");
        try {
            teamB.addPlayer(Faf);
        } catch (TeamSizeException e) {
            e.printStackTrace();
        }
        Player Shane = new Player("Shane");
        try {
            teamB.addPlayer(Shane);
        } catch (TeamSizeException e) {
            e.printStackTrace();
        }
        Player Dwayne = new Player("Dwayne");
        try {
            teamB.addPlayer(Dwayne);
        } catch (TeamSizeException e) {
            e.printStackTrace();
        }
        Player Sam = new Player("Sam");
        try {
            teamB.addPlayer(Sam);
        } catch (TeamSizeException e) {
            e.printStackTrace();
        }
        Player Karn = new Player("Karn");
        try {
            teamB.addPlayer(Karn);
        } catch (TeamSizeException e) {
            e.printStackTrace();
        }
        Player Ambati = new Player("Ambati");
        try {
            teamB.addPlayer(Ambati);
        } catch (TeamSizeException e) {
            e.printStackTrace();
        }
        Player Deepak = new Player("Deepak");
        try {
            teamB.addPlayer(Deepak);
        } catch (TeamSizeException e) {
            e.printStackTrace();
        }
        Player Piyush = new Player("Piyush");
        try {
            teamB.addPlayer(Piyush);
        } catch (TeamSizeException e) {
            e.printStackTrace();
        }


        // Adding teams to tournament
        ipl.addTeam(teamA);
        ipl.addTeam(teamB);

        // creating a match
        Match match1 = new Match(teamA, teamB,MatchType.T20);
        Match match2 = new Match(teamA,teamB,MatchType.T20);

        //sheduling match to tournment
        ipl.scheduleMatch(match1);
        ipl.scheduleMatch(match2);


        // adding player performance in match

        try {
            MS_DHONI.recordPerformance(60, 2,match1); // 1 fifty
        } catch (PerformanceRecordedException e) {
            e.printStackTrace();
        }
        try {
            Suresh_Raina.recordPerformance(55, 5,match2); // 5 wickets in a match
        } catch (PerformanceRecordedException e) {
            e.printStackTrace();
        }


        try {
            Suresh_Raina.recordPerformance(80, 1,match1); // 1 fifty
        } catch (PerformanceRecordedException e) {
            e.printStackTrace();
        }
        try {
            MS_DHONI.recordPerformance(10, 7,match2); // 7 wickets in a match
        } catch (PerformanceRecordedException e) {
            e.printStackTrace();
        }
        match1.setWinner(teamA);
        match2.setWinner(teamA);



        // Thread for finding the highest wicket-taker
        Thread highestWicketTakerThread = new Thread(() -> {
            Player highestWicketTaker = ipl.getHighestWicketTaker();
            System.out.println("Highest Wicket Taker: " + highestWicketTaker + ", No of wickets: " + highestWicketTaker.getTotalWickets());
        });

        // Thread for finding the player with maximum fifties
        Thread maxFiftiesPlayerThread = new Thread(() -> {
            Player maxFiftiesPlayer = ipl.getMaximumFiftiesPlayer();
            System.out.println("Player with Maximum 50s: " + maxFiftiesPlayer + ", Number of 50s: " + maxFiftiesPlayer.getFifties());
        });

        // Thread for listing semi-finalists
        Thread semiFinalistsThread = new Thread(() -> {
            List<Team> semiFinalists = ipl.getSemiFinalists();

            semiFinalists.forEach(team -> System.out.println("Teams in Semi-Finals: "+team.name));
        });

        // Thread for listing top 5 run scorers
        Thread topRunScorersThread = new Thread(() -> {
            List<Player> topRunScorers = ipl.getTopRunScorers();

            topRunScorers.forEach(player -> System.out.println("Top 5 Run Scorers: "+player + ": " + player.getTotalRuns() + " runs"));
        });

        // Thread for printing the tournament winner
        Thread tournamentWinnerThread = new Thread(() -> {
            Team winner = ipl.getTournamentWinner(); // This method needs to be properly implemented as per the tournament logic
            System.out.println("Tournament Winner: " + winner.name);
        });

        // Start all threads
        highestWicketTakerThread.start();
        maxFiftiesPlayerThread.start();
        semiFinalistsThread.start();
        topRunScorersThread.start();
        tournamentWinnerThread.start();

        // Join all threads to ensure main thread waits for them to finish
        try {
            highestWicketTakerThread.join();
            maxFiftiesPlayerThread.join();
            semiFinalistsThread.join();
            topRunScorersThread.join();
            tournamentWinnerThread.join();
        } catch (InterruptedException e) {
            System.out.println("A thread was interrupted.");
        }
    }



}

