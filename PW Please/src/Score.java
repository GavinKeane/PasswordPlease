
public class Score {

    float score = 0; //User score begins at 0
    int streak = 1; //Default to 1
    float bonusTime = 30; //30 Seconds for total bonus time
    final float scoreCorrect = 100; // addition of minimal 100 pts for correct select

    public Score() {
    }

    public void updateStreak(boolean choice) {
        if (choice == true) {
            if (this.streak < 6) {
                this.streak++;
            }
        } else {
            this.streak = 1;
        }
    }

    public float updateScore(int timeTaken) {
        if (timeTaken < 30) {
            this.score += (((this.bonusTime - timeTaken) / this.bonusTime)
                    * this.scoreCorrect * this.streak) + this.scoreCorrect;
        } else {
            this.score += this.scoreCorrect * this.streak;
        }
        return this.score;
    }
}
