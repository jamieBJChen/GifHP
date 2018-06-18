package cbj.jamiechencbj.gif.Activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.zhuinden.monarchy.Monarchy;

import org.json.JSONObject;

import cbj.jamiechencbj.gif.Adapters.GifPagedRecyclerViewAdapter;
import cbj.jamiechencbj.gif.Core.Contract;
import cbj.jamiechencbj.gif.Core.Gif;
import cbj.jamiechencbj.gif.Core.GifBaseActivity;
import cbj.jamiechencbj.gif.Entities.GifItem;
import cbj.jamiechencbj.gif.Entities.GifItemOriginal;
import cbj.jamiechencbj.gif.Entities.GifItemOriginalStill;
import cbj.jamiechencbj.gif.Entities.GifItemRatingPersist;
import cbj.jamiechencbj.gif.R;
import cbj.jamiechencbj.gif.Utils.GifLogger;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.Sort;

public class GifMainListActivity extends GifBaseActivity {

    private RelativeLayout rootRelativeLayout, innerRelativeLayout;
    private RelativeLayout innerTopPlaceHolderRelativeLayout, innerBottomRootRelativeLayout;
    private CoordinatorLayout rootCoordinatorLayout;
    private RecyclerView bottomRecyclerView;
    private AppBarLayout topAppBarLayout;
    private CollapsingToolbarLayout innerCollapsingToolbarLayout;
    private AppCompatTextView innerBottomTagsTextView;
    private AppCompatImageView innerBottomRankingImageView, innerBottomSearchImageView;

    private GifPagedRecyclerViewAdapter gifPagedRecyclerViewAdapter;
    private LiveData<PagedList<GifItem>> gifItemsList;
    private DataSource.Factory<Integer, GifItem> gifItemDataSourceFactory;
    private Observer<PagedList<GifItem>> gifItemObserver = new Observer<PagedList<GifItem>>() {
        @Override
        public void onChanged(@Nullable PagedList<GifItem> gifItems) {
            gifPagedRecyclerViewAdapter.submitList(gifItems);
        }
    };

    private final int defaultPageSize         = 10;
    private final int defaultPreFetchDistence = 3;
    private boolean sortByHighestRanking      = true;

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
        checkNewKeywordsAndLoadNewGifs();
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
            gifItemsList.removeObserver(gifItemObserver);
            Gif.getMonarchy().runTransactionSync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.where(GifItem.class).findAll().deleteAllFromRealm();
                    realm.where(GifItemOriginal.class).findAll().deleteAllFromRealm();
                    realm.where(GifItemOriginalStill.class).findAll().deleteAllFromRealm();
                }
            });
            Gif.getEditor().putBoolean(Contract.GIF_HAS_NEW_KEYWORDS_DOC, true).apply();
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
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
        setUpBottomRecyclerView();
    }

    private void setUpInnerBottomRankingImageViewListeners() {
        try {
            innerBottomRankingImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Sort the list
                    sortByHighestRanking = true;
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
                    showGifSearchActivity();
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
            bottomRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int lastVisibleItemPosition = ((GridLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                    int totalCount              = Gif.getSharedPreferences().getInt(Contract.GIF_TOTAL_COUNT_DOC, 0);
                    int offset                  = Gif.getSharedPreferences().getInt(Contract.GIF_OFFSET_DOC, 0);
                    boolean canLoad             = Gif.getSharedPreferences().getBoolean(Contract.GIF_CAN_LOAD_DOC, true);
                    String searchKeywordsString = Gif.getSharedPreferences().getString(Contract.GIF_SEARCH_KEYWORDS_DOC, "");
                    if (canLoad){
                        if (lastVisibleItemPosition == offset-defaultPreFetchDistence) {
                            if (offset < totalCount) {
                                Gif.getEditor().putBoolean(Contract.GIF_CAN_LOAD_DOC, false).apply();
                                loadGifs(defaultPageSize, offset, searchKeywordsString);
                            }
                        }
                    }
                }
            });
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void setUpBottomRecyclerView() {
        try {
            gifPagedRecyclerViewAdapter = new GifPagedRecyclerViewAdapter(this);
            bottomRecyclerView.setHasFixedSize(true);
            bottomRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            bottomRecyclerView.setAdapter(gifPagedRecyclerViewAdapter);
            gifItemDataSourceFactory = Gif.getMonarchy().createDataSourceFactory(new Monarchy.Query<GifItem>() {
                @Override
                public RealmQuery<GifItem> createQuery(Realm realm) {
                    GifLogger.d("Update");
                    if (sortByHighestRanking){
                        return realm.where(GifItem.class).sort(Contract.GIF_PARAMETER_KEY_USER_RATING, Sort.DESCENDING);
                    }
                    return realm.where(GifItem.class);
                }
            });
            gifItemsList = Gif.getMonarchy().findAllPagedWithChanges(gifItemDataSourceFactory,
                    new LivePagedListBuilder<Integer, GifItem>(gifItemDataSourceFactory, 10));
            gifItemsList.observeForever(gifItemObserver);
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void showGifSearchActivity() {
        try {
            Intent intent = new Intent(GifMainListActivity.this, GifSearchActivity.class);
            startActivity(intent);
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void checkNewKeywordsAndLoadNewGifs() {
        try {
            boolean hasNewKeywords = Gif.getSharedPreferences().getBoolean(Contract.GIF_HAS_NEW_KEYWORDS_DOC, false);
            String searchKeywordsString = Gif.getSharedPreferences().getString(Contract.GIF_SEARCH_KEYWORDS_DOC, "");
            if (hasNewKeywords && !searchKeywordsString.equals("")){
                Gif.getEditor().putBoolean(Contract.GIF_HAS_NEW_KEYWORDS_DOC, false).apply();
                innerBottomTagsTextView.setText("#"+searchKeywordsString);
                loadGifs(defaultPageSize, 0, searchKeywordsString);
            }
            else if (searchKeywordsString.equals("")){
                showGifSearchActivity();
            }
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void loadGifs(final int pageSize, final int offset, final String keywordsString) {
        try {
            if (isNetworkConnected()){
                /////////////////////////\ Debug \///////////////////////////////
                GifLogger.d(Contract.GIPHY_SEARCH_ENDPOINT_API_TAG+" Request body:\nnone");
                /////////////////////////////////////////////////////////////////
                String urlString = String.format(Contract.GIPHY_SEARCH_ENDPOINT_URL_COMPONENT, keywordsString, Contract.GIPHY_API_KEY, pageSize, offset);
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
                                    if (response.has(Contract.GIF_PARAMETER_KEY_META)){
                                        if (!response.isNull(Contract.GIF_PARAMETER_KEY_META)){
                                            JSONObject metaJsonObject = response.getJSONObject(Contract.GIF_PARAMETER_KEY_META);
                                            if (metaJsonObject.getInt(Contract.GIF_PARAMETER_KEY_STATUS) == 200){
                                                if (response.has(Contract.GIF_PARAMETER_KEY_DATA)){
                                                    if (!response.isNull(Contract.GIF_PARAMETER_KEY_DATA)){
                                                        for (int i=0; i<response.getJSONArray(Contract.GIF_PARAMETER_KEY_DATA).length(); i++){
                                                            final JSONObject gifItemJsonObject = response.getJSONArray(Contract.GIF_PARAMETER_KEY_DATA).getJSONObject(i);
                                                            Gif.getMonarchy().writeAsync(new Realm.Transaction() {
                                                                @Override
                                                                public void execute(Realm realm) {
                                                                    try {
                                                                        GifItem gifItem = GifItem.getGifItemFromJson(gifItemJsonObject);
                                                                        GifItemRatingPersist gifItemRatingPersist = realm.where(GifItemRatingPersist.class)
                                                                                .equalTo(Contract.GIF_PARAMETER_KEY_ID, gifItem.getId()).findFirst();
                                                                        if (gifItemRatingPersist != null){
                                                                            gifItem.setUserRating(gifItemRatingPersist.getUserRating());
                                                                        }
                                                                        else {
                                                                            GifItemRatingPersist newGifItemRatingPersist = GifItemRatingPersist.getGifItemRatingPersistWith(gifItem.getId(), 0.0);
                                                                            realm.copyToRealmOrUpdate(newGifItemRatingPersist);
                                                                        }
                                                                        realm.copyToRealmOrUpdate(gifItem);
                                                                    } catch (Exception e){
                                                                        GifLogger.e(e.getLocalizedMessage());
                                                                    }
                                                                }
                                                            });
                                                        }
                                                    }
                                                }
                                                if (response.has(Contract.GIF_PARAMETER_KEY_PAGINATION)){
                                                    if (!response.isNull(Contract.GIF_PARAMETER_KEY_PAGINATION)){
                                                        JSONObject paginationJsonObject = response.getJSONObject(Contract.GIF_PARAMETER_KEY_PAGINATION);
                                                        int totalCount  = paginationJsonObject.getInt(Contract.GIF_PARAMETER_KEY_TOTAL_COUNT);
                                                        final int count = paginationJsonObject.getInt(Contract.GIF_PARAMETER_KEY_COUNT);
                                                        Gif.getEditor().putInt(Contract.GIF_TOTAL_COUNT_DOC, totalCount)
                                                                .putInt(Contract.GIF_OFFSET_DOC, offset+count)
                                                                .putBoolean(Contract.GIF_CAN_LOAD_DOC, true).apply();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } catch (Exception e){
                                    GifLogger.e(e.getLocalizedMessage());
                                    showAlertDialogWithTwoButton(GifMainListActivity.this, "", e.getLocalizedMessage(), "retry", "cancel", new Runnable() {
                                        @Override
                                        public void run() {
                                            loadGifs(pageSize, offset, keywordsString);
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
                                        loadGifs(pageSize, offset, keywordsString);
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
                    loadGifs(pageSize, offset, keywordsString);
                }
            }, null, true);
        }
    }

}
