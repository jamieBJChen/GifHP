package cbj.jamiechencbj.gif.Core;

public class Contract {

    private Contract() {
        throw new AssertionError("no instances");
    }

    /**
     * Request header key
     */
    public static final String REQUEST_HEADER_KEY_ACCEPT        = "Accept";
    public static final String REQUEST_HEADER_KEY_CONTENT_TYPE  = "Content-Type";
    public static final String REQUEST_HEADER_KEY_AUTHORIZATION = "Authorization";
    public static final String REQUEST_HEADER_KEY_TIMEZONE      = "X-CLIENT-TIMEZONE";
    public static final String REQUEST_HEADER_KEY_USER_AGENT    = "User-Agent";

    /**
     * Giphy api key (Debug)
     */
    public static final String GIPHY_API_KEY = "LaGHU8UVq9JXGlVehUuXkfvICkOWTOqT";

    /**
     * Giphy endpoint url component
     */
    public static final String GIPHY_SEARCH_ENDPOINT_URL_COMPONENT = "http://api.giphy.com/v1/gifs/search?q=%s&api_key=%s&limit=%d&offset=%d";

    /**
     * Tags
     */
    public static final String GIPHY_SEARCH_ENDPOINT_API_TAG = "giphy_search_endpoint_api_tag";

    /**
     * Realm database
     */
    //Must be incremented with each change to the database
    public static long DB_SCHEMA_VERSION     = 0;
    public static final String REALM_DB_NAME = "gifhp.realm";

    /**
     * Debug
     */
    public static boolean GIF_SHOULD_SHOW_DEBUG_LOG = true;

    /**
     * Parameters name
     */
    public static final String GIF_PARAMETER_KEY_URL               = "url";
    public static final String GIF_PARAMETER_KEY_WIDTH             = "width";
    public static final String GIF_PARAMETER_KEY_HEIGHT            = "height";
    public static final String GIF_PARAMETER_KEY_SIZE              = "size";
    public static final String GIF_PARAMETER_KEY_FRAMES            = "frames";
    public static final String GIF_PARAMETER_KEY_MP4               = "mp4";
    public static final String GIF_PARAMETER_KEY_MP4_SIZE          = "mp4_size";
    public static final String GIF_PARAMETER_KEY_WEBP              = "webp";
    public static final String GIF_PARAMETER_KEY_WEBP_SIZE         = "webp_size";
    public static final String GIF_PARAMETER_KEY_TYPE              = "type";
    public static final String GIF_PARAMETER_KEY_ID                = "id";
    public static final String GIF_PARAMETER_KEY_EMBED_URL         = "embed_url";
    public static final String GIF_PARAMETER_KEY_SOURCE            = "source";
    public static final String GIF_PARAMETER_KEY_SOURCE_TLD        = "source_tld";
    public static final String GIF_PARAMETER_KEY_SOURCE_POST_URL   = "source_post_url";
    public static final String GIF_PARAMETER_KEY_IMPORT_DATETIME   = "import_datetime";
    public static final String GIF_PARAMETER_KEY_TRENDING_DATETIME = "trending_datetime";
    public static final String GIF_PARAMETER_KEY_TITLE             = "title";
    public static final String GIF_PARAMETER_KEY_IMAGES            = "images";
    public static final String GIF_PARAMETER_KEY_ORIGINAL          = "original";
    public static final String GIF_PARAMETER_KEY_ORIGINAL_STILL    = "original_still";
    public static final String GIF_PARAMETER_KEY_META              = "meta";
    public static final String GIF_PARAMETER_KEY_STATUS            = "status";
    public static final String GIF_PARAMETER_KEY_MSG               = "msg";
    public static final String GIF_PARAMETER_KEY_PAGINATION        = "pagination";
    public static final String GIF_PARAMETER_KEY_TOTAL_COUNT       = "total_count";
    public static final String GIF_PARAMETER_KEY_COUNT             = "count";
    public static final String GIF_PARAMETER_KEY_OFFSET            = "offset";
    public static final String GIF_PARAMETER_KEY_DATA              = "data";

    /**
     * Share preferences key
     */
    public static final String GIF_TOTAL_COUNT_DOC      = "gif_total_count_doc";
    public static final String GIF_COUNT_DOC            = "gif_count_doc";
    public static final String GIF_OFFSET_DOC           = "gif_offset_doc";
    public static final String GIF_CAN_LOAD_DOC         = "gif_can_load_doc";
    public static final String GIF_SEARCH_KEYWORDS_DOC  = "gif_search_keywords_doc";
    public static final String GIF_HAS_NEW_KEYWORDS_DOC = "gif_has_new_keywords_doc";

}
