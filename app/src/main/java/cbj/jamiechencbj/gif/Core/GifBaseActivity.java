package cbj.jamiechencbj.gif.Core;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidnetworking.common.ANRequest;

import java.util.TimeZone;
import java.util.concurrent.Executors;

import cbj.jamiechencbj.gif.R;

public class GifBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_base);
    }

    /**
     * Fast Android Networking Methods
     * Standard post request builder
     *
     * @param urlString
     * @param isHeaderRequired
     * @param isExecutorRequired
     * @return New PostRequestBuilder Instance
     * @throws Exception
     */
    public ANRequest.PostRequestBuilder standardPostRequestBuilder(String urlString,
                                                                   boolean isHeaderRequired,
                                                                   boolean isExecutorRequired) throws Exception {
        ANRequest.PostRequestBuilder postRequestBuilder = new ANRequest.PostRequestBuilder(urlString);
        if (isHeaderRequired) {
            postRequestBuilder.addHeaders(Contract.REQUEST_HEADER_KEY_CONTENT_TYPE, "application/json");
            postRequestBuilder.addHeaders(Contract.REQUEST_HEADER_KEY_ACCEPT, "application/json");
            postRequestBuilder.addHeaders(Contract.REQUEST_HEADER_KEY_TIMEZONE, TimeZone.getDefault().getID());
        }
        if (isExecutorRequired){
            postRequestBuilder.setExecutor(Executors.newSingleThreadExecutor());
        }
        return postRequestBuilder;
    }

    /**
     * Fast Android Networking Methods
     * Standard multi part builder
     *
     * @param urlString
     * @param isHeaderRequired
     * @param isExecutorRequired
     * @return New MultiPartBuilder Instance
     * @throws Exception
     */
    public ANRequest.MultiPartBuilder standardMultiPartBuilder(String urlString,
                                                               boolean isHeaderRequired,
                                                               boolean isExecutorRequired) throws Exception {
        ANRequest.MultiPartBuilder multiPartBuilder = new ANRequest.MultiPartBuilder(urlString);
        if (isHeaderRequired) {
            multiPartBuilder.addHeaders(Contract.REQUEST_HEADER_KEY_CONTENT_TYPE, "application/json");
            multiPartBuilder.addHeaders(Contract.REQUEST_HEADER_KEY_ACCEPT, "application/json");
            multiPartBuilder.addHeaders(Contract.REQUEST_HEADER_KEY_TIMEZONE, TimeZone.getDefault().getID());
        }
        if (isExecutorRequired){
            multiPartBuilder.setExecutor(Executors.newSingleThreadExecutor());
        }
        return multiPartBuilder;
    }

    /**
     * Fast Android Networking Methods
     * Standard put request builder
     *
     * @param urlString
     * @param isHeaderRequired
     * @param isExecutorRequired
     * @return New PutRequestBuilder Instance
     * @throws Exception
     */
    public ANRequest.PutRequestBuilder standardPutRequestBuilder(String urlString,
                                                                 boolean isHeaderRequired,
                                                                 boolean isExecutorRequired) throws Exception {
        ANRequest.PutRequestBuilder putRequestBuilder = new ANRequest.PutRequestBuilder(urlString);
        if (isHeaderRequired) {
            putRequestBuilder.addHeaders(Contract.REQUEST_HEADER_KEY_CONTENT_TYPE, "application/json");
            putRequestBuilder.addHeaders(Contract.REQUEST_HEADER_KEY_ACCEPT, "application/json");
            putRequestBuilder.addHeaders(Contract.REQUEST_HEADER_KEY_TIMEZONE, TimeZone.getDefault().getID());
        }
        if (isExecutorRequired){
            putRequestBuilder.setExecutor(Executors.newSingleThreadExecutor());
        }
        return putRequestBuilder;
    }

    /**
     * Fast Android Networking Methods
     * Standard get request builder
     *
     * @param urlString
     * @param isHeaderRequired
     * @param isExecutorRequired
     * @return New GetRequestBuilder Instance
     * @throws Exception
     */
    public ANRequest.GetRequestBuilder standardGetRequestBuilder(String urlString,
                                                                 boolean isHeaderRequired,
                                                                 boolean isExecutorRequired) throws Exception {
        ANRequest.GetRequestBuilder getRequestBuilder = new ANRequest.GetRequestBuilder(urlString);
        if (isHeaderRequired) {
            getRequestBuilder.addHeaders(Contract.REQUEST_HEADER_KEY_CONTENT_TYPE, "application/json");
            getRequestBuilder.addHeaders(Contract.REQUEST_HEADER_KEY_ACCEPT, "application/json");
            getRequestBuilder.addHeaders(Contract.REQUEST_HEADER_KEY_TIMEZONE, TimeZone.getDefault().getID());
        }
        if (isExecutorRequired){
            getRequestBuilder.setExecutor(Executors.newSingleThreadExecutor());
        }
        return getRequestBuilder;
    }

}
