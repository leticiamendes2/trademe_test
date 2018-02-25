package listing.trademe.test.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import listing.trademe.test.com.R;
import listing.trademe.test.com.model.Item;

/**
 * Created by Letícia on 23/02/2018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private final ArrayList<Item.ItemDetail> items;

    public ItemAdapter(ArrayList<Item.ItemDetail> items) {
        this.items = items;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.list_item_items, parent, false);
        return new ItemAdapter.ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        if(items != null) {
            return items.size();
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        Item.ItemDetail item = items.get(position);
        holder.bind(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_item)
        ImageView imgItem;
        @BindView(R.id.txt_title_item)
        TextView txtTitle;
        @BindView(R.id.txt_subtitle_item)
        TextView txtSubtitle;
        @BindView(R.id.txt_price_display)
        TextView txtDisplayPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Item.ItemDetail item) {

            Glide.with(context)
                    .load(item.getPictureHref())
                    .into(imgItem);

            txtTitle.setText(item.getTitle());
            txtSubtitle.setText(item.getSubtitle());
            txtDisplayPrice.setText(item.getPriceDisplay());

            // else mark as grey maybe in the view
        }
    }
}
