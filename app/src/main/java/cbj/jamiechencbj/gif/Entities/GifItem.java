package cbj.jamiechencbj.gif.Entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GifItem extends RealmObject {

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


}
