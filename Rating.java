/**
 * Homework #5 Morgan Kinne (mck7py)
 */

public class Rating {
    private int score;
    private User user;

    public Rating() {
        score = 0;
        user = new User();
     }
    
     public Rating (User newUser, int newScore) {
        if (newScore == 1 || newScore == 0 || newScore == -1) {
           score = newScore;
        }
        else {
           score = 0;
        }
        user = newUser;
     }
    
    @Override 
    public String toString() {
        if (score == 1) {
            return user.getUserName() + " rated as an upvote";
         }
         else if (score == -1) {
            return user.getUserName() + " rated as a downvote";
         }
         else {
            return user.getUserName() + " rated as a pass";
         }
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Rating) {
            Rating rate = (Rating) object;
            if (this.getScore() == rate.getScore() && this.getUser().equals(rate.getUser()))
            {
               return true;
            }
        }
        return false;
         
    }

    public int getScore() {
        return score;
    }

    public boolean setScore(int newScore) {
        if (newScore == 1 || newScore == 0 || newScore == -1) {
            score = newScore;
            return true;
         }
         else {
            return false;
         }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}