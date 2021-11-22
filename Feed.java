/**
 * Homework #5 Morgan Kinne (mck7py)
 */

import java.util.ArrayList;

public class Feed {
    private ArrayList<Meme> memes = new ArrayList<>();

    public Meme getNewMeme(User user) {
        for (int i = 0; i < memes.size(); i++) {
            if (!user.getMemesViewed().contains(memes.get(i)) && !user.getMemesCreated().contains(memes.get(i))) {
               return memes.get(i);
            }
            else {
               continue;
            }
         }
         return null;
    }

    @Override
    public String toString() {
        String space = "";
        for (int i = 0; i < memes.size(); i++) {
           space = space + memes.get(i).toString() + "\n";
        }
        return space;
    }

    public ArrayList<Meme> getMemes() {
        return memes;
    }

    public void setMemes(ArrayList<Meme> memes) {
        this.memes = memes;
    }

    public Feed() {}
}