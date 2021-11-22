/* 
 * Homework #5 Morgan Kinne (mck7py)
 */

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class User implements Comparable<User>{
    private String userName;
    private ArrayList<Meme> memesCreated = new ArrayList<>();
    Set<Meme> memesViewed = new TreeSet<>(); // make sure all variables are in TreeSet
    
    public User(String userName) {
        this.userName = userName;
    }
    
    public User() {}
    
    @Override
    public String toString() {
        return userName + " has rated (" + memesViewed.size() + ") memes, (" + calculateReputation() + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;
            if(this.getUserName().equals(user.getUserName())) {
                return true;
            }
        }
        return false;
    }
    
    public void rateMeme(Meme meme, int rating) {
        if(!(memesCreated.contains(meme))) {
            memesViewed.add(meme);
            meme.addRating(new Rating (this, rating));
        }
    }
    
    public boolean rateNextMemeFromFeed(Feed feed, int ratingScore) {
        Meme meme = feed.getNewMeme(this);
        if (meme != null) {
           rateMeme (meme, ratingScore);
           return true;
        }
        else {
           return false;
        }
    }
    
    public Meme createMeme(BackgroundImage bg, String caption) {
        Meme meme = new Meme (bg, caption, this);
        memesCreated.add(meme);
        return meme;
    }
    
    public boolean deleteMeme(Meme meme) {
        if (this.memesCreated.contains(meme) && meme.getShared() == false) {
            memesCreated.remove(meme);
            return true;
         }
         else {
            return false;
         }
    }
    
    public void shareMeme(Meme meme, Feed feed) {
        if (meme != null) {
            meme.setShared(true);
            feed.getMemes().add(meme);
         }
    }
    
    public double calculateReputation() {
        double rep = 0.0;
        if (memesCreated.size() == 0 && memesViewed.size() == 0) {
           return 0.0;
        }
        else if (memesCreated.size() == 0) {
           return 0.0;
        }
        else {
           for (int i = 0; i < memesCreated.size(); i++) {
              rep += memesCreated.get(i).calculateOverallRating();
           }
           return (rep/memesCreated.size());
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Meme> getMemesCreated() {
        return memesCreated;
    }

    public void setMemesCreated(ArrayList<Meme> memesCreated) {
        this.memesCreated = memesCreated;
    }

    public ArrayList<Meme> getMemesViewed() {
        return (ArrayList<Meme>) memesViewed;
    }

    public void setMemesViewed(Set<Meme> memesViewed) {
        this.memesViewed = memesViewed;
    }

    @Override
    public int compareTo(User o) {
        if (!this.getUserName().equals(o.getUserName())) {
            return this.getUserName().compareTo(o.getUserName()); 
        }
        else if(this.getMemesCreated().size()!=o.getMemesCreated().size()) {
            if (this.getMemesCreated().size() < o.getMemesCreated().size()) {
                return 1;
            }
            else if (this.getMemesCreated().size() > o.getMemesCreated().size()) {
                return -1;
            }
        }
        return 0;
    }
    
}