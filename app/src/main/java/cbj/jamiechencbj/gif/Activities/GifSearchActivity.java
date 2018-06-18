package cbj.jamiechencbj.gif.Activities;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import cbj.jamiechencbj.gif.Core.Contract;
import cbj.jamiechencbj.gif.Core.Gif;
import cbj.jamiechencbj.gif.Core.GifBaseActivity;
import cbj.jamiechencbj.gif.Entities.GifItem;
import cbj.jamiechencbj.gif.Entities.GifItemOriginal;
import cbj.jamiechencbj.gif.Entities.GifItemOriginalStill;
import cbj.jamiechencbj.gif.R;
import cbj.jamiechencbj.gif.Utils.GifLogger;
import cbj.jamiechencbj.gif.customviews.LottieFontViewGroupVJ;
import io.realm.Realm;

public class GifSearchActivity extends GifBaseActivity {

    private RelativeLayout rootRelativeLayout, topBarRootRelativeLayout;
    private RelativeLayout topBarBottomPlaceholderRelativeLayout;
    private AppCompatImageView topBarLeftImageView;
    private AppCompatTextView topBarLeftTextView;
    private ScrollView rootScrollView;
    private LinearLayout innerLinearLayout;
    private LottieFontViewGroupVJ lottieFontViewGroupVJ;
    private AppCompatButton bottomButton;

    private ViewTreeObserver.OnGlobalLayoutListener fontListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_search);
        initVariablesAndLayouts();
    }

    @Override
    protected void onStart() {
        super.onStart();
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
        try {
            lottieFontViewGroupVJ.getViewTreeObserver().removeOnGlobalLayoutListener(fontListener);
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
        super.onDestroy();
    }

    private void initVariablesAndLayouts() {
        // Set up layouts
        rootRelativeLayout                    = (RelativeLayout)findViewById(R.id.gif_search_root_relative_layout);
        topBarRootRelativeLayout              = (RelativeLayout)findViewById(R.id.gif_search_top_bar_root_relative_layout);
        topBarBottomPlaceholderRelativeLayout = (RelativeLayout)findViewById(R.id.gif_search_top_bar_bottom_placeholder_relative_layout);
        topBarLeftImageView                   = (AppCompatImageView)findViewById(R.id.gif_search_top_bar_left_image_view);
        topBarLeftTextView                    = (AppCompatTextView)findViewById(R.id.gif_search_top_bar_left_text_view);
        rootScrollView                        = (ScrollView)findViewById(R.id.gif_search_root_scroll_view);
        innerLinearLayout                     = (LinearLayout)findViewById(R.id.gif_search_inner_linear_layout);
        lottieFontViewGroupVJ                 = (LottieFontViewGroupVJ)findViewById(R.id.gif_search_lottie_font_view_group);
        bottomButton                          = (AppCompatButton)findViewById(R.id.gif_search_bottom_button);

        // Set up variables
        fontListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rootScrollView.fullScroll(View.FOCUS_DOWN);
            }
        };
        lottieFontViewGroupVJ.getViewTreeObserver().addOnGlobalLayoutListener(fontListener);

        setUpTopBar();
        setUpBottomButton();
    }

    private void setUpTopBar() {
        try {
            topBarLeftImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void setUpBottomButton() {
        try {
            bottomButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String inputString = lottieFontViewGroupVJ.getInputString();
                    GifLogger.d("Before: "+inputString);
                    inputString = inputString.trim().replaceAll(" +", "+");
                    GifLogger.d("After: "+inputString);
                    if (inputString.length() == 0){
                        return;
                    }
                    Gif.getEditor().putBoolean(Contract.GIF_HAS_NEW_KEYWORDS_DOC, true)
                            .putString(Contract.GIF_SEARCH_KEYWORDS_DOC, inputString).apply();
                    Gif.getMonarchy().runTransactionSync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.where(GifItem.class).findAll().deleteAllFromRealm();
                            realm.where(GifItemOriginal.class).findAll().deleteAllFromRealm();
                            realm.where(GifItemOriginalStill.class).findAll().deleteAllFromRealm();
                        }
                    });
                    finish();
                }
            });
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

}
