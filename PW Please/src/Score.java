
/**
 * Score tracker
 *
 * @author Jeremiah
 */
public class Score
{
    
    public static int score;
    
    public Score(){
        score = 0;
    }

    /**
     */
    public static void ScoreCorrect()
    {
        score += 10;
    }
    public static void ScoreIncorrect()
    {
        score -= 10;
    }
    public static int DisplayScore()
    {
        return score;
    }
}
