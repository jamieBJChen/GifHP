package cbj.jamiechencbj.gif.Utils;

import android.util.Log;

import cbj.jamiechencbj.gif.Core.Contract;

public class GifLogger {

    private static final String TAG = "GifLogger";

    private GifLogger() {}

    /**
     * Send a VERBOSE log message.
     *
     * @param logMessageString
     */
    public static void v(String logMessageString) {
        if (Contract.GIF_SHOULD_SHOW_DEBUG_LOG) {
            Log.v(TAG, logMessageString);
        }
    }

    /**
     * Send a DEBUG log message.
     *
     * @param logMessageString
     */
    public static void d(String logMessageString) {
        if (Contract.GIF_SHOULD_SHOW_DEBUG_LOG) {
            Log.d(TAG, logMessageString);
        }
    }

    /**
     * Send an ERROR log message.
     *
     * @param logMessageString
     */
    public static void e(String logMessageString) {
        if (Contract.GIF_SHOULD_SHOW_DEBUG_LOG) {
            Log.e(TAG, logMessageString);
        }
    }

    /**
     * Send an INFO log message.
     *
     * @param logMessageString
     */
    public static void i(String logMessageString) {
        if (Contract.GIF_SHOULD_SHOW_DEBUG_LOG) {
            Log.i(TAG, logMessageString);
        }
    }

    /**
     * Send a WARN log message.
     *
     * @param logMessageString
     */
    public static void w(String logMessageString) {
        if (Contract.GIF_SHOULD_SHOW_DEBUG_LOG) {
            Log.w(TAG, logMessageString);
        }
    }

    /**
     * What a Terrible Failure: Report a condition that should never happen.
     *
     * @param logMessageString
     */
    public static void wtf(String logMessageString) {
        if (Contract.GIF_SHOULD_SHOW_DEBUG_LOG) {
            Log.wtf(TAG, logMessageString);
        }
    }

}
