package cbj.jamiechencbj.gif.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import cbj.jamiechencbj.gif.Core.Contract;
import cbj.jamiechencbj.gif.Core.GifBaseActivity;
import cbj.jamiechencbj.gif.R;
import cbj.jamiechencbj.gif.Utils.GifLogger;

public class GifMainListActivity extends GifBaseActivity {

    private RelativeLayout rootRelativeLayout, innerRelativeLayout;
    private RelativeLayout innerTopPlaceHolderRelativeLayout, innerBottomRootRelativeLayout;
    private CoordinatorLayout rootCoordinatorLayout;
    private RecyclerView bottomRecyclerView;
    private AppBarLayout topAppBarLayout;
    private CollapsingToolbarLayout innerCollapsingToolbarLayout;
    private AppCompatTextView innerBottomTagsTextView;
    private AppCompatImageView innerBottomRankingImageView, innerBottomSearchImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_main_list);
        initVariablesAndLayouts();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        testing();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initVariablesAndLayouts() {
        // Set up layouts
        rootRelativeLayout                = (RelativeLayout)findViewById(R.id.gif_main_list_root_relative_layout);
        innerRelativeLayout               = (RelativeLayout)findViewById(R.id.gif_main_list_top_app_bar_inner_relative_layout);
        innerTopPlaceHolderRelativeLayout = (RelativeLayout)findViewById(R.id.gif_main_list_top_app_bar_inner_top_place_holder_relative_layout);
        innerBottomRootRelativeLayout     = (RelativeLayout)findViewById(R.id.gif_main_list_top_app_bar_inner_bottom_root_relative_layout);
        rootCoordinatorLayout             = (CoordinatorLayout)findViewById(R.id.gif_main_list_root_coordinator_layout);
        bottomRecyclerView                = (RecyclerView)findViewById(R.id.gif_main_list_bottom_recycler_view);
        topAppBarLayout                   = (AppBarLayout)findViewById(R.id.gif_main_list_top_app_bar_layout);
        innerCollapsingToolbarLayout      = (CollapsingToolbarLayout)findViewById(R.id.gif_main_list_top_app_bar_inner_collapsing_tool_bar_layout);
        innerBottomTagsTextView           = (AppCompatTextView)findViewById(R.id.gif_main_list_top_app_bar_inner_bottom_tags_text_view);
        innerBottomRankingImageView       = (AppCompatImageView)findViewById(R.id.gif_main_list_top_app_bar_inner_bottom_ranking_image_view);
        innerBottomSearchImageView        = (AppCompatImageView)findViewById(R.id.gif_main_list_top_app_bar_inner_bottom_search_image_view);

        // Set up methods
        setUpInnerBottomRankingImageViewListeners();
        setUpInnerBottomSearchImageViewListeners();
        setUpTopAppBarLayoutListeners();
        setUpBottomRecyclerViewListeners();
    }

    private void setUpInnerBottomRankingImageViewListeners() {
        try {
            innerBottomRankingImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Sort the list
                }
            });
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void setUpInnerBottomSearchImageViewListeners() {
        try {
            innerBottomSearchImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Show search view
                }
            });
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void setUpTopAppBarLayoutListeners() {
        try {
            topAppBarLayout.getViewTreeObserver()
                    .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            topAppBarLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            try {
                                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    topAppBarLayout.setElevation(0);
                                }
                            } catch (Exception e){
                                GifLogger.e(e.getLocalizedMessage());
                            }
                        }
                    });
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void setUpBottomRecyclerViewListeners() {
        try {
//            bottomRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//                @Override
//                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//                }
//            });
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void testing() {
        try {
            if (isNetworkConnected()){
                /////////////////////////\ Debug \///////////////////////////////
                GifLogger.d(Contract.GIPHY_SEARCH_ENDPOINT_API_TAG+" Request body:\nnone");
                /////////////////////////////////////////////////////////////////
                String urlString = String.format(Contract.GIPHY_SEARCH_ENDPOINT_URL_COMPONENT, "funny+cat", Contract.GIPHY_API_KEY);
                ANRequest.GetRequestBuilder getRequestBuilder = standardGetRequestBuilder(urlString, true, false);
                getRequestBuilder.setTag(Contract.GIPHY_SEARCH_ENDPOINT_API_TAG)
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    /////////////////////////\ Debug \///////////////////////////////
                                    GifLogger.d(Contract.GIPHY_SEARCH_ENDPOINT_API_TAG+" Response:\n"+ response.toString());
                                    /////////////////////////////////////////////////////////////////
                                } catch (Exception e){
                                    GifLogger.e(e.getLocalizedMessage());
                                    showAlertDialogWithTwoButton(GifMainListActivity.this, "", e.getLocalizedMessage(), "retry", "cancel", new Runnable() {
                                        @Override
                                        public void run() {
                                            testing();
                                        }
                                    }, null, true);
                                }
                            }
                            @Override
                            public void onError(ANError anError) {
                                String errorMessageString = "Please try again.";
                                try {
                                    if (anError != null) {
                                        errorMessageString = "Error code: " + anError.getErrorCode() + "\nError Details: " + anError.getErrorDetail();
                                        if (anError.getErrorCode() == 0) {
                                            GifLogger.e("Error message: " + errorMessageString);
                                            return;
                                        }
                                    }
                                } catch (Exception e){
                                    GifLogger.e(e.getMessage());
                                    errorMessageString = e.getMessage();
                                }
                                showAlertDialogWithTwoButton(GifMainListActivity.this, "", errorMessageString, "retry", "cancel", new Runnable() {
                                    @Override
                                    public void run() {
                                        testing();
                                    }
                                }, null, true);
                            }
                        });
            }
            else {
                showAlertDialogWithOneButton(GifMainListActivity.this, "Network Connection Problem", "Network connection appears to be offline. Please check your network connection.", "Settings", new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }, true);
            }
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
            showAlertDialogWithTwoButton(GifMainListActivity.this, "", e.getLocalizedMessage(), "retry", "cancel", new Runnable() {
                @Override
                public void run() {
                    testing();
                }
            }, null, true);
        }
    }

}
