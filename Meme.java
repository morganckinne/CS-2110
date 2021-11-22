/*
 * Homework#5 Morgan Kinne (mck7py)
 */
public class Meme implements Comparable<Meme>{
    private User creator;
    private BackgroundImage backgroundImage;
    private Rating[] ratings;
    private String caption;
    private String captionVerticalAlign;
    private boolean shared;

    public Meme(BackgroundImage bg, String caption, User creator) {
        this.backgroundImage = bg;
        this.caption = caption;
        this.creator = creator;
        ratings = new Rating[10];
        captionVerticalAlign = "bottom";
        shared = false;
    }
    
    public Meme() {
        backgroundImage = new BackgroundImage();
        caption = "";
        creator = new User();
        ratings = new Rating[10];
        captionVerticalAlign = "bottom";
        shared = false;
    }

    @Override
    public String toString() {
        int positives = 0;
        int negatives = 0;
        for (int i = 0; i < ratings.length; i++) {
           if (ratings[i] != null && ratings[i].getScore() == 1) {
              positives++;
           }
           else if (ratings[i] != null && ratings[i].getScore() == -1) {
              negatives++;
           }
        }
        return backgroundImage.toString() + " '" + caption + "' " + this.calculateOverallRating() + " [+1: " + positives + ", -1: " + negatives + "] - created by " + creator.getUserName();
     }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Meme) {
            Meme m = (Meme) obj;
            if (this.getCreator().equals(m.getCreator()) && this.getCaption().equals(m.getCaption()) && this.getBackgroundImage().equals(m.getBackgroundImage()))
            {
               return true;
            }
        }
        return false;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public BackgroundImage getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(BackgroundImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Rating[] getRatings() {
        return ratings;
    }

    public void setRatings(Rating[] ratings) {
        this.ratings = ratings;
    }
    
    public boolean addRating (Rating rate) {
        if (ratings.length < 10) { 
           Rating [] newratings = new Rating[ratings.length+1];
           for (int i = 0; i < ratings.length; i++) {
              newratings[i] = ratings[i];
           }
           newratings[ratings.length] = rate;
           ratings = newratings.clone();
           return true; 
        }
        else {
           for (int i = 0; i < ratings.length; i++) {
              if (ratings[i] == null) {
                 ratings[i] = rate;
                 return true;
              }
              else {
                 continue;
              }
           }
           if (ratings.length == 10 && ratings[9] != null) {
              for (int i = 0; i < ratings.length-1; i++) {
                 ratings[i] = ratings[i+1]; 
              }
              ratings[9] = rate;
              return true;
           }
        }
     
        return false;
     }
    
    public double calculateOverallRating() {
        if (ratings.length == 0) {
           return 0.0;
        }
        else {
           double overallRating = 0;
           for (int i = 0; i < ratings.length; i++) {
              if (ratings[i] != null) {
                 overallRating += ratings[i].getScore();
              }
           }
           return overallRating;
        }
     }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCaptionVerticalAlign() {
        return captionVerticalAlign;
    }

    public boolean setCaptionVerticalAlign(String captionVA) {
        if (captionVA.equals("bottom") || captionVA.equals("top") || captionVA.equals("middle")) {
            captionVerticalAlign = captionVA;
            return true;
         }
         else {
            return false;
         }
    }

    public boolean getShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    @Override
    public int compareTo(Meme o) {
        int captionCompare = caption.compareTo(o.caption);
        if(captionCompare!=0) {
            return captionCompare;
        }
            else if(backgroundImage.compareTo(o.backgroundImage)!=0) {
                return backgroundImage.compareTo(o.backgroundImage);
            }
            else if(this.calculateOverallRating()!=o.calculateOverallRating()) {
                if (this.calculateOverallRating() < o.calculateOverallRating()) {
                    return 1;
                }
                else if(this.calculateOverallRating() >= o.calculateOverallRating()){
                    return -1;
                }
            }
            else {
                if (this.getShared() && !o.getShared()) {
                    return -1;
                }
                else if (!this.getShared() && o.getShared()) {
                    return 1;
                }
            }
        return 0;
    }
}
