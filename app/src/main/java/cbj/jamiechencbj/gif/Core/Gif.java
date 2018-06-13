package cbj.jamiechencbj.gif.Core;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.androidnetworking.AndroidNetworking;

import java.util.concurrent.TimeUnit;

import cbj.jamiechencbj.gif.Utils.GifLogger;
import cbj.jamiechencbj.gif.Utils.GifNetworkLoggingInterceptor;
import okhttp3.OkHttpClient;

public class Gif extends Application {

    private static Context context;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static final String SHARE_PREFERENCES_NAME = "GifHPSharePreferencesNameFile";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        setUpSharePreferences();
        setAppropriateDebugOptions();
        setAppropriateFastAndroidNetworking();
    }

    private void setUpSharePreferences() {
        try {
            sharedPreferences = context.getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void setAppropriateDebugOptions() {
        Contract.GIF_SHOULD_SHOW_DEBUG_LOG = true;
    }

    private void setAppropriateFastAndroidNetworking() {
        try {
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(new GifNetworkLoggingInterceptor())
                    .build();
            AndroidNetworking.initialize(getApplicationContext(), okHttpClient);
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

}
