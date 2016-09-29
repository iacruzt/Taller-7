package Contest;

import java.util.*;

public class Contest
{

    // ---------------------------------------------------------------- ATTRIBUTES

    private String name;
    private ArrayList < Contestant > contestants;

    // ---------------------------------------------------------------- CONSTRUCTORS

    public Contest ()
    {
        // Default constructor
        contestants = new ArrayList < Contestant > ();
    }

    public Contest ( String newName )
    {
        contestants = new ArrayList < Contestant > ();
        this.setName( newName );
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

    public void registerContestants ()
    {
        String newContestant;

        TextIO.putln( "Welcome to " + this.getName() + ", a jumps contest! " );
        TextIO.putln( "Write the names of the contestants one at a time; introducing an empty string will take you to"
                      + "the jumping phase." );

        while ( true )
        {
            TextIO.putln( " New Contestant:  " );
            newContestant = TextIO.getln();

            if ( !newContestant.equals( "" ) )
            {
                this.contestants.add( new Contestant ( newContestant ) );
            }
            else
            {
                break;
            }

        }
    }

    public ArrayList < Contestant > getContestantsInJumpingOrder ()
    {
        ArrayList < Contestant > contestantsInJumpingOrder = this.contestants;
        Collections.sort( contestantsInJumpingOrder );

        return contestantsInJumpingOrder;
    }

    public void printContestantsInJumpingOrder ()
    {
        int position;

        position = 1;
        TextIO.putln ( "Jumping order" );
        for ( Contestant c : this.getContestantsInJumpingOrder() )
        {
            TextIO.putln ( position + "  " + c.getName() + " (" + c.getPoints() + " points)");
            position = position + 1;
        }
    }

    public int getJumpLength ()
    {
        return (int) ( Math.random() * 61 ) + 60;
    }

    public ArrayList < Integer > getJudgesScores ()
    {
        ArrayList < Integer > judgesScores = new ArrayList < Integer > ();

        for ( int i = 0 ; i < 5 ; i = i + 1 )
        {
            judgesScores.add( (int) ( Math.random() * 11 ) + 10 );
        }
        Collections.sort( judgesScores );

        return judgesScores;
    }

    public int getRoundScore ( int jumpLength , ArrayList < Integer > judgesScores )
    {
        int judgesScoresSum, score;

        judgesScoresSum = 0;
        for ( int i = 1; i < 4 ; i = i + 1 )
        {
            judgesScoresSum = judgesScoresSum + judgesScores.get(i);
        }

        score = judgesScoresSum + jumpLength;

        return score;
    }

    public void printRoundResults ( int round , HashMap < Integer , Contestant > roundResults )
    {
        ArrayList < Integer > scores = new ArrayList < Integer > ();

        scores.addAll ( roundResults.keySet() );
        Collections.sort ( scores );
        Collections.reverse ( scores );

        TextIO.putln( "\nResults of round " + round + "\n");
        for ( Integer score : scores )
        {
            TextIO.putln( roundResults.get(score).getName() );
            TextIO.putln( "  Score:" + score );
            TextIO.put( "  Judge scores: " );
            for ( Integer judgeScore : roundResults.get(score).getLastJudgesScores() )
            {
                TextIO.put( judgeScore + " " );
            }
            TextIO.putln();
            TextIO.putln();
        }
    }

    public void printFinalResults ()
    {
        int position;

        position = 1;
        Collections.sort( this.contestants );
        Collections.reverse ( this.contestants );
        TextIO.putln( "The final results: ");
        TextIO.putln( "\nPosition     " + "Name" );
        for ( Contestant c : this.contestants )
        {
            TextIO.putln( position + "            " + c.getName() + " (" + c.getPoints() + ")" );
            TextIO.put( "             Jump lengths: " );
            for ( Integer jumpLength : c.getJumps())
            {
                TextIO.put(jumpLength + " ");
            }
            TextIO.putln();
            position = position+ 1;
        }
    }

    public void jump ()
    {
        String flag;
        int round, jumpLength;
        ArrayList < Integer > judgesScores;
        HashMap < Integer , Contestant > roundResults;

        TextIO.putln( "The contest begins!" );

        TextIO.putln( "\n Write \"jump\" to jump; otherwise you quit: " );
        flag = TextIO.getln();
        round = 1;
        while ( flag.equals ("jump") )
        {
            TextIO.putln( "\nRound " + round  + "\n");
            this.printContestantsInJumpingOrder();

            roundResults = new HashMap < Integer, Contestant > ();
            for ( Contestant c : this.contestants )
            {
                jumpLength = this.getJumpLength();
                judgesScores = this.getJudgesScores();

                c.addJump(jumpLength);
                c.setLastJudgesScores( judgesScores );
                c.addPoints( this.getRoundScore( jumpLength , judgesScores ) );
                roundResults.put ( this.getRoundScore( jumpLength , judgesScores ) , c );
            }

            this.printRoundResults( round , roundResults );

            TextIO.putln( " Write \"jump\" to jump; otherwise you quit: " );
            flag = TextIO.getln();
            round = round + 1;
        }

        this.printFinalResults();

    }

    // ---------------------------------------------------------------- TEST MAIN

    public static void main ( String [] args )
    {
        Contest myContest = new Contest ( "My Test Contest" );
        myContest.registerContestants();
        myContest.jump();
    }


}
