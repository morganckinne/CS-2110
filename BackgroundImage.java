/**
 * Homework #5 Morgan Kinne (mck7py)
 */

public class BackgroundImage implements Comparable<BackgroundImage>{
    private String imageFileName;
    private String title;
    private String description;
    
    public BackgroundImage() {
        imageFileName = "";
        title = "";
        description = "";
    }
    
    public BackgroundImage(String imageFileName, String title, String description) {
        this.imageFileName = imageFileName;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return title + " <" + description + ">";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BackgroundImage) {
            BackgroundImage backgroundImage = (BackgroundImage) obj;
            if(this.getDescription().equals(backgroundImage.getDescription()) && this.getTitle().equals(backgroundImage.getTitle()) && this.getImageFileName().equals(backgroundImage.getImageFileName()))
            {
                return true;
             }
        }
        return false;
    }
    
    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(BackgroundImage o) {
        int nameCompare = imageFileName.compareTo(o.imageFileName);
        if(nameCompare!=0) {
            return nameCompare;
        }
        int titleCompare = title.compareTo(o.title);
        if(titleCompare!=0) {
            return titleCompare;
        }
        else {
            return description.compareTo(o.description);
        }
    }

}