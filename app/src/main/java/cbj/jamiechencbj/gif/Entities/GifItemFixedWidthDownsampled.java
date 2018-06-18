package cbj.jamiechencbj.gif.Entities;

import org.json.JSONException;
import org.json.JSONObject;

import cbj.jamiechencbj.gif.Core.Contract;
import io.realm.RealmObject;

public class GifItemFixedWidthDownsampled extends RealmObject {

    /**
     * Keys
     */
    private String url;
    private String width;
    private String height;
    private String size;
    private String webp;
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

    public String getWebp() {
        return webp;
    }

    public void setWebp(String webp) {
        this.webp = webp;
    }

    public String getWebpSize() {
        return webpSize;
    }

    public void setWebpSize(String webpSize) {
        this.webpSize = webpSize;
    }

    /**
     * V1 API Parser
     *
     * @param jsonObject
     * @return GifItemFixedWidthDownsampled
     * @throws JSONException
     */
    public static GifItemFixedWidthDownsampled getGifItemFixedWidthDownsampledFromJson(JSONObject jsonObject) throws JSONException {
        GifItemFixedWidthDownsampled gifItemFixedWidthDownsampled = new GifItemFixedWidthDownsampled();

        gifItemFixedWidthDownsampled.setUrl("");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_URL)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_URL)){
                gifItemFixedWidthDownsampled.setUrl(jsonObject.getString(Contract.GIF_PARAMETER_KEY_URL));
            }
        }

        gifItemFixedWidthDownsampled.setWidth("0");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_WIDTH)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_WIDTH)){
                gifItemFixedWidthDownsampled.setWidth(jsonObject.getString(Contract.GIF_PARAMETER_KEY_WIDTH));
            }
        }

        gifItemFixedWidthDownsampled.setHeight("0");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_HEIGHT)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_HEIGHT)){
                gifItemFixedWidthDownsampled.setHeight(jsonObject.getString(Contract.GIF_PARAMETER_KEY_HEIGHT));
            }
        }

        gifItemFixedWidthDownsampled.setSize("0");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_SIZE)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_SIZE)){
                gifItemFixedWidthDownsampled.setSize(jsonObject.getString(Contract.GIF_PARAMETER_KEY_SIZE));
            }
        }

        gifItemFixedWidthDownsampled.setWebp("");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_WEBP)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_WEBP)){
                gifItemFixedWidthDownsampled.setWebp(jsonObject.getString(Contract.GIF_PARAMETER_KEY_WEBP));
            }
        }

        gifItemFixedWidthDownsampled.setWebpSize("0");
        if (jsonObject.has(Contract.GIF_PARAMETER_KEY_WEBP_SIZE)){
            if (!jsonObject.isNull(Contract.GIF_PARAMETER_KEY_WEBP_SIZE)){
                gifItemFixedWidthDownsampled.setWebpSize(jsonObject.getString(Contract.GIF_PARAMETER_KEY_WEBP_SIZE));
            }
        }

        return gifItemFixedWidthDownsampled;
    }

}
