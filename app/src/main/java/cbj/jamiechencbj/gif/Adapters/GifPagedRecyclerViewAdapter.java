package cbj.jamiechencbj.gif.Adapters;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import cbj.jamiechencbj.gif.Activities.GifItemDetailActivity;
import cbj.jamiechencbj.gif.Core.Contract;
import cbj.jamiechencbj.gif.Entities.GifItem;
import cbj.jamiechencbj.gif.R;
import cbj.jamiechencbj.gif.Utils.GifLogger;

public class GifPagedRecyclerViewAdapter extends PagedListAdapter<GifItem, RecyclerView.ViewHolder> {

    private Context context;

    public GifPagedRecyclerViewAdapter(Context context) {
        super(GifItem.ITEM_CALLBACK);
        this.context = context;
    }

    public static class GifItemViewHolder extends RecyclerView.ViewHolder {

        public View itemView;
        public RelativeLayout rootRelativeLayout;
        public CardView rootCardView;
        public LinearLayout innerLinearLayout;
        public AppCompatImageView innerImageView;
        public AppCompatTextView innerTitleTextView;
        public RatingBar innerRatingBar;

        public GifItemViewHolder(View view) {
            super(view);
            itemView           = view;
            rootRelativeLayout = (RelativeLayout)view.findViewById(R.id.gif_list_item_root_relative_layout);
            rootCardView       = (CardView)view.findViewById(R.id.gif_list_item_root_card_view);
            innerLinearLayout  = (LinearLayout)view.findViewById(R.id.gif_list_item_inner_linear_layout);
            innerImageView     = (AppCompatImageView)view.findViewById(R.id.gif_list_item_inner_image_view);
            innerTitleTextView = (AppCompatTextView)view.findViewById(R.id.gif_list_item_inner_title_text_view);
            innerRatingBar     = (RatingBar)view.findViewById(R.id.gif_list_item_inner_rating_bar);
        }

        public void bind(GifItem gifItem) {
            try {
                //Picasso.get().load(gifItem.getGifItemOriginalStill().getUrl()).into(innerImageView);
                innerTitleTextView.setText(gifItem.getTitle());
                innerRatingBar.setRating((float)gifItem.getUserRating());
            } catch (Exception e){
                GifLogger.e(e.getLocalizedMessage());
            }
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gif_list_item_layout, parent, false);
        return new GifItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        try {
            if (holder instanceof GifItemViewHolder) {
                final GifItem gifItem = getItem(position);
                if (gifItem != null) {
                    ((GifItemViewHolder) holder).bind(gifItem);
                    if (gifItem.getGifItemFixedWidthDownsampled() != null){
                        Glide.with(context).load(gifItem.getGifItemFixedWidthDownsampled().getUrl())
                                .into(((GifItemViewHolder) holder).innerImageView);
                    }
                    else {
                        Glide.with(context).load(gifItem.getGifItemOriginal().getUrl())
                                .into(((GifItemViewHolder) holder).innerImageView);
                    }
                    ((GifItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showGifItemDetailActivity(gifItem.getId());
                        }
                    });
                }
            }
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

    private void showGifItemDetailActivity(String gifIdString) {
        try {
            Intent intent = new Intent(context, GifItemDetailActivity.class);
            intent.putExtra(Contract.GIF_PARAMETER_KEY_ID, gifIdString);
            context.startActivity(intent);
        } catch (Exception e){
            GifLogger.e(e.getLocalizedMessage());
        }
    }

}
