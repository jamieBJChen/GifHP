package cbj.jamiechencbj.gif.Entities;

import org.json.JSONException;
import org.json.JSONObject;

import cbj.jamiechencbj.gif.Core.Contract;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GifItem extends RealmObject {

    /**
     * Keys
     */
    @PrimaryKey
    private String id;
    private String type;
    private String url;
    private String embedUrl;
    private String source;
    private String sourceTld;
    private String sourcePostUrl;
    private String importDateTime;
    private String trendingDateTime;
    private String title;
    private double userRating;
    private double avgRating;
    private GifItemOriginal gifItemOriginal;
    private GifItemOriginalStill gifItemOriginalStill;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmbedUrl() {
        return embedUrl;
    }

    public void setEmbedUrl(String embedUrl) {
        this.embedUrl = embedUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceTld() {
        return sourceTld;
    }

    public void setSourceTld(String sourceTld) {
        this.sourceTld = sourceTld;
    }

    public String getSourcePostUrl() {
        return sourcePostUrl;
    }

    public void setSourcePostUrl(String sourcePostUrl) {
        this.sourcePostUrl = sourcePostUrl;
    }

    public String getImportDateTime() {
        return importDateTime;
    }

    public void setImportDateTime(String importDateTime) {
        this.importDateTime = importDateTime;
    }

    public String getTrendingDateTime() {
        return trendingDateTime;
    }

    public void setTrendingDateTime(String trendingDateTime) {
        this.trendingDateTime = trendingDateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public GifItemOriginal getGifItemOriginal() {
        return gifItemOriginal;
    }

    public void setGifItemOriginal(GifItemOriginal gifItemOriginal) {
        this.gifItemOriginal = gifItemOriginal;
    }

    public GifItemOriginalStill getGifItemOriginalStill() {
        return gifItemOriginalStill;
    }

    public void setGifItemOriginalStill(GifItemOriginalStill gifItemOriginalStill) {
        this.gifItemOriginalStill = gifItemOriginalStill;
    }

    /**
     * V1 API Parser
     *
     * @param jsonObject
     * @return GifItem
     * @throws JSONException
     */
    public static GifItem getGifItemFromJson(JSONObject jsonObject) throws JSONException {
        GifItem gifItem = new GifItem();

        gifItem.setId("");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_ID)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_ID)){
                gifItem.setId(jsonObject.getString(Contract.GIF_PARAMETER_KEY_ID));
            }
        }

        gifItem.setType("");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_TYPE)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_TYPE)){
                gifItem.setType(jsonObject.getString(Contract.GIF_PARAMETER_KEY_TYPE));
            }
        }

        gifItem.setUrl("");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_URL)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_URL)){
                gifItem.setUrl(jsonObject.getString(Contract.GIF_PARAMETER_KEY_URL));
            }
        }

        gifItem.setEmbedUrl("");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_EMBED_URL)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_EMBED_URL)){
                gifItem.setEmbedUrl(jsonObject.getString(Contract.GIF_PARAMETER_KEY_EMBED_URL));
            }
        }

        gifItem.setSource("");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_SOURCE)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_SOURCE)){
                gifItem.setSource(jsonObject.getString(Contract.GIF_PARAMETER_KEY_SOURCE));
            }
        }

        gifItem.setSourceTld("");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_SOURCE_TLD)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_SOURCE_TLD)){
                gifItem.setSourceTld(jsonObject.getString(Contract.GIF_PARAMETER_KEY_SOURCE_TLD));
            }
        }

        gifItem.setSourcePostUrl("");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_SOURCE_POST_URL)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_SOURCE_POST_URL)){
                gifItem.setSourcePostUrl(jsonObject.getString(Contract.GIF_PARAMETER_KEY_SOURCE_POST_URL));
            }
        }

        gifItem.setImportDateTime("");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_IMPORT_DATETIME)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_IMPORT_DATETIME)){
                gifItem.setImportDateTime(jsonObject.getString(Contract.GIF_PARAMETER_KEY_IMPORT_DATETIME));
            }
        }

        gifItem.setTrendingDateTime("");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_TRENDING_DATETIME)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_TRENDING_DATETIME)){
                gifItem.setTrendingDateTime(jsonObject.getString(Contract.GIF_PARAMETER_KEY_TRENDING_DATETIME));
            }
        }

        gifItem.setTitle("");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_TITLE)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_TITLE)){
                gifItem.setTitle(jsonObject.getString(Contract.GIF_PARAMETER_KEY_TITLE));
            }
        }

        gifItem.setUserRating(0);

        gifItem.setAvgRating(0);

        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_IMAGES)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_IMAGES)){
                JSONObject imagesJsonObject = jsonObject.getJSONObject(Contract.GIF_PARAMETER_KEY_IMAGES);
                if (imagesJsonObject.has(Contract.GIF_PARAMETER_KEY_ORIGINAL)){
                    if (!imagesJsonObject.isNull(Contract.GIF_PARAMETER_KEY_ORIGINAL)){
                        JSONObject originalJsonObject = imagesJsonObject.getJSONObject(Contract.GIF_PARAMETER_KEY_ORIGINAL);
                        gifItem.setGifItemOriginal(GifItemOriginal.getGifItemOriginalFromJson(originalJsonObject));
                    }
                }
                if (imagesJsonObject.has(Contract.GIF_PARAMETER_KEY_ORIGINAL_STILL)){
                    if (!imagesJsonObject.isNull(Contract.GIF_PARAMETER_KEY_ORIGINAL_STILL)){
                        JSONObject originalStillJsonObject = imagesJsonObject.getJSONObject(Contract.GIF_PARAMETER_KEY_ORIGINAL_STILL);
                        gifItem.setGifItemOriginalStill(GifItemOriginalStill.getGifItemOriginalStillFromJson(originalStillJsonObject));
                    }
                }
            }
        }

        return gifItem;
    }

}
