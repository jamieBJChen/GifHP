package cbj.jamiechencbj.gif.Core;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidnetworking.common.ANRequest;

import java.util.TimeZone;
import java.util.concurrent.Executors;

import cbj.jamiechencbj.gif.R;
import cbj.jamiechencbj.gif.Utils.GifLogger;

public class GifBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_base);
    }

    /**
     * Check network connection
     *
     * @return true if network is connected
     *         otherwise, false
     */
    public boolean isNetworkConnected() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                return true;
            }
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
        return false;
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

    /**
     * Alert dialog with one button
     *
     * @param context
     * @param title
     * @param message
     * @param buttonName
     * @param buttonTapHandler
     * @param cancelable
     */
    public void showAlertDialogWithOneButton(Context context,
                                             String title,
                                             String message,
                                             String buttonName,
                                             final Runnable buttonTapHandler,
                                             boolean cancelable) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(buttonName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (buttonTapHandler != null) {
                            buttonTapHandler.run();
                        }
                    }
                })
                .setCancelable(cancelable);
        alertDialogBuilder.show();
    }

    /**
     * Alert dialog with two buttons
     *
     * @param context
     * @param title
     * @param message
     * @param positiveButtonName
     * @param negativeButtonName
     * @param positiveButtonTapHandler
     * @param negativeButtonTapHandler
     * @param cancelable
     */
    public void showAlertDialogWithTwoButton(Context context,
                                             String title,
                                             String message,
                                             String positiveButtonName,
                                             String negativeButtonName,
                                             final Runnable positiveButtonTapHandler,
                                             final Runnable negativeButtonTapHandler,
                                             boolean cancelable) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (positiveButtonTapHandler != null) {
                            positiveButtonTapHandler.run();
                        }
                    }
                })
                .setNegativeButton(negativeButtonName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (negativeButtonTapHandler != null) {
                            negativeButtonTapHandler.run();
                        }
                    }
                })
                .setCancelable(cancelable);
        alertDialogBuilder.show();
    }

}
