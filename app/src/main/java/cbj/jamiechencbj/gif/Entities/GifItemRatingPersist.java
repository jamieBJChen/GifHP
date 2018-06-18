package cbj.jamiechencbj.gif.Entities;

import org.json.JSONException;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GifItemRatingPersist extends RealmObject {

    /**
     * Keys
     */
    @PrimaryKey
    private String id;
    private double userRating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    /**
     * V1 API Parser
     *
     * @param gifId
     * @param gifUserRating
     * @return GifItemRatingPersist
     * @throws JSONException
     */
    public static GifItemRatingPersist getGifItemRatingPersistWith(String gifId, double gifUserRating) throws JSONException {
        GifItemRatingPersist gifItemRatingPersist = new GifItemRatingPersist();

        if (gifId != null){
            gifItemRatingPersist.setId(gifId);
        }

        gifItemRatingPersist.setUserRating(gifUserRating);

        return gifItemRatingPersist;
    }

}
