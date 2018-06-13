package cbj.jamiechencbj.gif.Utils;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Logging interceptor for debugging
 */
public class GifNetworkLoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        /**
         * Getting response time
         */
        long t1 = System.nanoTime();
        GifLogger.d(String.format(Locale.CANADA, "Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        GifLogger.d(String.format(Locale.CANADA, "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }

}
