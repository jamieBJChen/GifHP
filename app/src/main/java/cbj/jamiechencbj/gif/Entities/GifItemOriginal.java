package cbj.jamiechencbj.gif.Entities;

import org.json.JSONException;
import org.json.JSONObject;

import cbj.jamiechencbj.gif.Core.Contract;
import io.realm.RealmObject;

public class GifItemOriginal extends RealmObject {

    /**
     * Keys
     */
    private String url;
    private String width;
    private String height;
    private String size;
    private String frames;
    private String mp4Url;
    private String mp4Size;
    private String webpUrl;
    private String webpSize;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFrames() {
        return frames;
    }

    public void setFrames(String frames) {
        this.frames = frames;
    }

    public String getMp4Url() {
        return mp4Url;
    }

    public void setMp4Url(String mp4Url) {
        this.mp4Url = mp4Url;
    }

    public String getMp4Size() {
        return mp4Size;
    }

    public void setMp4Size(String mp4Size) {
        this.mp4Size = mp4Size;
    }

    public String getWebpUrl() {
        return webpUrl;
    }

    public void setWebpUrl(String webpUrl) {
        this.webpUrl = webpUrl;
    }

    public String getWebpSize() {
        return webpSize;
    }

    public void setWebpSize(String webpSize) {
        this.webpSize = webpSize;
    }

    public static GifItemOriginal getGifItemOriginalFromJson(JSONObject jsonObject) throws JSONException {
        GifItemOriginal gifItemOriginal = new GifItemOriginal();

        gifItemOriginal.setUrl("");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_URL)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_URL)){
                gifItemOriginal.setUrl(jsonObject.getString(Contract.GIF_PARAMETER_KEY_URL));
            }
        }

        gifItemOriginal.setWidth("0");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_WIDTH)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_WIDTH)){
                gifItemOriginal.setWidth(jsonObject.getString(Contract.GIF_PARAMETER_KEY_WIDTH));
            }
        }

        gifItemOriginal.setHeight("0");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_HEIGHT)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_HEIGHT)){
                gifItemOriginal.setHeight(jsonObject.getString(Contract.GIF_PARAMETER_KEY_HEIGHT));
            }
        }

        gifItemOriginal.setSize("0");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_SIZE)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_SIZE)){
                gifItemOriginal.setSize(jsonObject.getString(Contract.GIF_PARAMETER_KEY_SIZE));
            }
        }

        gifItemOriginal.setFrames("0");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_FRAMES)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_FRAMES)){
                gifItemOriginal.setFrames(jsonObject.getString(Contract.GIF_PARAMETER_KEY_FRAMES));
            }
        }

        gifItemOriginal.setMp4Url("");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_MP4)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_MP4)){
                gifItemOriginal.setMp4Url(jsonObject.getString(Contract.GIF_PARAMETER_KEY_MP4));
            }
        }

        gifItemOriginal.setMp4Size("0");


        return gifItemOriginal;
    }

}
