package ipl;

public class TeamSizeException extends Exception{
    TeamSizeException(){
        super("Team is already full. Cannot add more players.");
    }
}

