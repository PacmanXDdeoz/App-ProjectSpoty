package Model;

import java.util.Date;

public class Playlist {
    String name_playlist, description;
    Date date_release;

    public Playlist(){}

    public Playlist(String name_playlist, String description, Date date_release){
        this.name_playlist = name_playlist;
        this.description = description;
        this.date_release = date_release;
    }

    public String getName_playlist() {
        return name_playlist;
    }

    public void setName_playlist(String name_playlist) {
        this.name_playlist = name_playlist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_release() {
        return date_release;
    }

    public void setDate_release(Date date_release) {
        this.date_release = date_release;
    }

    @Override
    public String toString() {
        return "Playlist [name_playlist=" + name_playlist + ", description=" + description + ", date_release="
                + date_release + "]";
    }
}
