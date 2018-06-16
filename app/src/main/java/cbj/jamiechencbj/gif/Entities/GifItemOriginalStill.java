package cbj.jamiechencbj.gif.Entities;

import org.json.JSONException;
import org.json.JSONObject;

import cbj.jamiechencbj.gif.Core.Contract;
import io.realm.RealmObject;

public class GifItemOriginalStill extends RealmObject {

    /**
     * Keys
     */
    private String url;
    private String width;
    private String height;

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

    /**
     * V1 API Parser
     *
     * @param jsonObject
     * @return GifItemOriginalStill
     * @throws JSONException
     */
    public static GifItemOriginalStill getGifItemOriginalStillFromJson(JSONObject jsonObject) throws JSONException {
        GifItemOriginalStill gifItemOriginalStill = new GifItemOriginalStill();

        gifItemOriginalStill.setUrl("");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_URL)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_URL)){
                gifItemOriginalStill.setUrl(jsonObject.getString(Contract.GIF_PARAMETER_KEY_URL));
            }
        }

        gifItemOriginalStill.setWidth("0");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_WIDTH)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_WIDTH)){
                gifItemOriginalStill.setWidth(jsonObject.getString(Contract.GIF_PARAMETER_KEY_WIDTH));
            }
        }

        gifItemOriginalStill.setHeight("0");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_HEIGHT)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_HEIGHT)){
                gifItemOriginalStill.setHeight(jsonObject.getString(Contract.GIF_PARAMETER_KEY_HEIGHT));
            }
        }

        return gifItemOriginalStill;
    }

}
