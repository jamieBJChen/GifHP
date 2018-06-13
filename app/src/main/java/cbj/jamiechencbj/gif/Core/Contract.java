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
     * Debug
     */
    public static boolean GIF_SHOULD_SHOW_DEBUG_LOG = true;

}
