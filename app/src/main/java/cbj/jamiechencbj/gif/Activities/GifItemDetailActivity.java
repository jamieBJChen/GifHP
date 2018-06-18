package cbj.jamiechencbj.gif.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import cbj.jamiechencbj.gif.Core.Contract;
import cbj.jamiechencbj.gif.Core.Gif;
import cbj.jamiechencbj.gif.Core.GifBaseActivity;
import cbj.jamiechencbj.gif.Entities.GifItem;
import cbj.jamiechencbj.gif.Entities.GifItemRatingPersist;
import cbj.jamiechencbj.gif.R;
import cbj.jamiechencbj.gif.Utils.GifLogger;
import io.realm.Realm;

public class GifItemDetailActivity extends GifBaseActivity {

    private RelativeLayout rootRelativeLayout, topAppBarInnerRelativeLayout;
    private RelativeLayout topAppBarInnerTopPlaceholderRelativeLayout;
    private RelativeLayout topAppBarInnerBottomRootRelativeLayout;
    private RelativeLayout innerGifRatingRelativeLayout, innerGifSourceRelativeLayout;
    private RelativeLayout innerGifTitleRelativeLayout, innerGifGiphyUrlRelativeLayout;
    private CoordinatorLayout rootCoordinatorLayout;
    private NestedScrollView mainNestedScrollView;
    private LinearLayout innerLinearLayout;
    private AppCompatImageView innerGifImageView, topAppBarLeftImageView;
    private AppCompatTextView innerGifTitleLeftTextView, topAppBarLeftTextView;
    private AppCompatTextView innerGifRatingTextView, innerGifSourceLeftTextView;
    private AppCompatTextView innerGifSourceRightTextView, innerGifTitleRightTextView;
    private AppCompatTextView innerGifGiphyUrlLeftTextView, innerGifGiphyUrlRightTextView;
    private AppBarLayout topAppBarLayout;
    private CollapsingToolbarLayout topAppBarInnerCollapsingToolbarLayout;
    private RatingBar innerGifRatingBar;
    private AppCompatButton innerGifRatingUpdateButton;

    private String gifId                     = "";
    private String gifTitle                  = "";
    private String gifOriginalUrlString      = "";
    private String gifOriginalStillUrlString = "";
    private String gifEmbedUrlString         = "";
    private String gifSourceString           = "";
    private String gifGiphyUrlString         = "";
    private double gifRating                 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_item_detail);
        initVariablesAndLayouts();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpGifDetail();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        rootRelativeLayout                         = (RelativeLayout)findViewById(R.id.gif_item_detail_root_relative_layout);
        topAppBarInnerRelativeLayout               = (RelativeLayout)findViewById(R.id.gif_item_detail_top_app_bar_inner_relative_layout);
        topAppBarInnerTopPlaceholderRelativeLayout = (RelativeLayout)findViewById(R.id.gif_item_detail_top_app_bar_inner_top_place_holder_relative_layout);
        topAppBarInnerBottomRootRelativeLayout     = (RelativeLayout)findViewById(R.id.gif_item_detail_top_app_bar_inner_bottom_root_relative_layout);
        innerGifRatingRelativeLayout               = (RelativeLayout)findViewById(R.id.gif_item_detail_main_nested_scroll_view_inner_gif_rating_relative_layout);
        innerGifSourceRelativeLayout               = (RelativeLayout)findViewById(R.id.gif_item_detail_main_nested_scroll_view_inner_gif_source_relative_layout);
        innerGifTitleRelativeLayout                = (RelativeLayout)findViewById(R.id.gif_item_detail_main_nested_scroll_view_inner_gif_title_relative_layout);
        innerGifGiphyUrlRelativeLayout             = (RelativeLayout)findViewById(R.id.gif_item_detail_main_nested_scroll_view_inner_gif_giphy_url_relative_layout);
        rootCoordinatorLayout                      = (CoordinatorLayout)findViewById(R.id.gif_item_detail_root_coordinator_layout);
        mainNestedScrollView                       = (NestedScrollView)findViewById(R.id.gif_item_detail_main_nested_scroll_view);
        innerLinearLayout                          = (LinearLayout)findViewById(R.id.gif_item_detail_main_nested_scroll_view_inner_linear_layout);
        innerGifImageView                          = (AppCompatImageView)findViewById(R.id.gif_item_detail_main_nested_scroll_view_inner_gif_image_view);
        topAppBarLeftImageView                     = (AppCompatImageView)findViewById(R.id.gif_item_detail_top_app_bar_left_image_view);
        innerGifTitleLeftTextView                  = (AppCompatTextView)findViewById(R.id.gif_item_detail_main_nested_scroll_view_inner_gif_title_left_text_view);
        topAppBarLeftTextView                      = (AppCompatTextView)findViewById(R.id.gif_item_detail_top_app_bar_left_text_view);
        innerGifRatingTextView                     = (AppCompatTextView)findViewById(R.id.gif_item_detail_main_nested_scroll_view_inner_gif_rating_text_view);
        innerGifSourceLeftTextView                 = (AppCompatTextView)findViewById(R.id.gif_item_detail_main_nested_scroll_view_inner_gif_source_left_text_view);
        innerGifSourceRightTextView                = (AppCompatTextView)findViewById(R.id.gif_item_detail_main_nested_scroll_view_inner_gif_source_right_text_view);
        innerGifTitleRightTextView                 = (AppCompatTextView)findViewById(R.id.gif_item_detail_main_nested_scroll_view_inner_gif_title_right_text_view);
        innerGifGiphyUrlLeftTextView               = (AppCompatTextView)findViewById(R.id.gif_item_detail_main_nested_scroll_view_inner_gif_giphy_url_left_text_view);
        innerGifGiphyUrlRightTextView              = (AppCompatTextView)findViewById(R.id.gif_item_detail_main_nested_scroll_view_inner_gif_giphy_url_right_text_view);
        topAppBarLayout                            = (AppBarLayout)findViewById(R.id.gif_item_detail_top_app_bar_layout);
        topAppBarInnerCollapsingToolbarLayout      = (CollapsingToolbarLayout)findViewById(R.id.gif_item_detail_top_app_bar_inner_collapsing_tool_bar_layout);
        innerGifRatingBar                          = (RatingBar)findViewById(R.id.gif_item_detail_main_nested_scroll_view_inner_gif_rating_bar);
        innerGifRatingUpdateButton                 = (AppCompatButton)findViewById(R.id.gif_item_detail_main_nested_scroll_view_inner_gif_rating_update_button);

        // Set up method
        setUpTopAppBarLeftImageView();
        setUpAppBarLayout();
        setUpMainNestScrollView();
        getGifDetailFromDatabase();
        setUpInnerGifRatingUpdateButton();
    }

    private void setUpTopAppBarLeftImageView() {
        try {
            topAppBarLeftImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void setUpAppBarLayout() {
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

    private void setUpMainNestScrollView() {
        try {
            mainNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    try {
                        if (scrollY <= 0) {
                            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                                topAppBarLayout.setElevation(0);
                            }
                        }
                        else {
                            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                                topAppBarLayout.setElevation(4);
                            }
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

    private void setUpInnerGifRatingUpdateButton() {
        try {
            innerGifRatingUpdateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        final double newRating = (double)innerGifRatingBar.getRating();
                        Gif.getMonarchy().writeAsync(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                GifItem gifItem = realm.where(GifItem.class).equalTo(Contract.GIF_PARAMETER_KEY_ID, gifId).findFirst();
                                if (gifItem != null){
                                    gifItem.setUserRating(newRating);
                                }
                                GifItemRatingPersist gifItemRatingPersist = realm.where(GifItemRatingPersist.class).equalTo(Contract.GIF_PARAMETER_KEY_ID, gifId).findFirst();
                                if (gifItemRatingPersist != null){
                                    gifItemRatingPersist.setUserRating(newRating);
                                }
                            }
                        });
                        showAlertDialogWithOneButton(GifItemDetailActivity.this, "", "Rating updated!", "Ok", null, true);
                    } catch (Exception e){
                        GifLogger.e(e.getLocalizedMessage());
                    }
                }
            });
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void getGifDetailFromDatabase() {
        try {
            gifId = getIntent().getStringExtra(Contract.GIF_PARAMETER_KEY_ID);
            if (gifId == null){
                gifId = "";
            }
            Gif.getMonarchy().runTransactionSync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    GifItem gifItem = realm.where(GifItem.class).equalTo(Contract.GIF_PARAMETER_KEY_ID, gifId).findFirst();
                    if (gifItem == null){
                        return;
                    }
                    gifTitle                  = gifItem.getTitle();
                    gifOriginalUrlString      = gifItem.getGifItemOriginal().getUrl();
                    gifOriginalStillUrlString = gifItem.getGifItemOriginalStill().getUrl();
                    gifEmbedUrlString         = gifItem.getEmbedUrl();
                    gifSourceString           = gifItem.getSource();
                    gifGiphyUrlString         = gifItem.getUrl();
                    gifRating                 = gifItem.getUserRating();
                }
            });
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void setUpGifDetail() {
        setUpInnerGifTitleTextView();
        setUpInnerGifImageView();
        setUpInnerGifRatingBar();
        setUpInnerGifSourceRightTextView();
        setUpInnerGifGiphyUrlRightTextView();
    }

    private void setUpInnerGifTitleTextView() {
        try {
            innerGifTitleRightTextView.setText(gifTitle);
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void setUpInnerGifImageView() {
        try {
            Glide.with(this)
                    .load(gifOriginalUrlString)
                    .into(innerGifImageView);
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void setUpInnerGifRatingBar() {
        try {
            innerGifRatingBar.setRating((float)gifRating);
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void setUpInnerGifSourceRightTextView() {
        try {
            innerGifSourceRightTextView.setText(gifSourceString);
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void setUpInnerGifGiphyUrlRightTextView() {
        try {
            innerGifGiphyUrlRightTextView.setText(gifGiphyUrlString);
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

}
