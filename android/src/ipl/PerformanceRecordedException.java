package ipl;

public class PerformanceRecordedException extends Exception{
    PerformanceRecordedException(){
        super("Performance for this player already recorded for this match.");
    }
}
