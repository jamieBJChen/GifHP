package cbj.jamiechencbj.gif.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;

import cbj.jamiechencbj.gif.Core.GifBaseActivity;
import cbj.jamiechencbj.gif.R;
import cbj.jamiechencbj.gif.Utils.GifLogger;

public class GifSplashScreenActivity extends GifBaseActivity {

    private RelativeLayout rootRelativeLayout;
    private LottieAnimationView mainLottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_splash_screen);
        initVariablesAndLayouts();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkStateAndDoAppropriateWork();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
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
        rootRelativeLayout      = (RelativeLayout)findViewById(R.id.gif_splash_screen_root_relative_layout);
        mainLottieAnimationView = (LottieAnimationView)findViewById(R.id.gif_splash_screen_main_lottie_animation_view);

        // Set up methods
        setUpLottoeAnimationViewListeners();
    }

    private void setUpLottoeAnimationViewListeners() {
        try {
            mainLottieAnimationView.addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    GifLogger.d("Animation is stop.");
                    showMainListActivity();
                }
            });
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void playAnimation() {
        try {
            mainLottieAnimationView.playAnimation();
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void showMainListActivity() {
        try {
            Intent intent = new Intent(this, GifMainListActivity.class);
            startActivity(intent);
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void checkStateAndDoAppropriateWork() {
        if (isNetworkConnected()){
            playAnimation();
        }
        else {
            showAlertDialogWithOneButton(this, "Network Connection Problem", "Network connection appears to be offline. Please check your network connection.", "Settings", new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }, false);
        }
    }

}
