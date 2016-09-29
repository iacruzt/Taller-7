package Contest;

import java.util.ArrayList;

public class Contestant implements Comparable < Contestant >
{

    // ---------------------------------------------------------------- ATTRIBUTES

    private String name;
    private int points;
    private ArrayList < Integer > lastJudgesScores;
    private ArrayList < Integer > jumps;

    // ---------------------------------------------------------------- CONSTRUCTORS

    public Contestant ()
    {
        // Default constructor here!
        lastJudgesScores = new ArrayList < Integer > ();
        jumps = new ArrayList < Integer > ();
    }

    public Contestant ( String newName)
    {
        this();
        this.setName( newName );
        this.setPoints( 0 );
    }

    // ---------------------------------------------------------------- METHODS

    public String getName()
    {
        return this.name;
    }

    public void setName(String newName)
    {
        this.name = newName;
    }

    public int getPoints()
    {
        return this.points;
    }

    public void setPoints(int newPoints)
    {
        this.points = newPoints;
    }

    public void addPoints ( int pointsToAdd )
    {
        this.setPoints( this.getPoints() + pointsToAdd );
    }

    public void setLastJudgesScores ( ArrayList < Integer > scores )
    {
        this.lastJudgesScores = scores;
    }

    public ArrayList < Integer > getLastJudgesScores ()
    {
        return this.lastJudgesScores;
    }

    public void addJump ( int jumpLength )
    {
        this.jumps.add( jumpLength );
    }

    public ArrayList < Integer > getJumps ()
    {
        return this.jumps;
    }

    @Override
    public int compareTo ( Contestant otherContestant )
    {
        return this.getPoints() - otherContestant.getPoints();
    }

}
