package arcoiris.org.dragchaser2.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import arcoiris.org.dragchaser2.R;
import arcoiris.org.dragchaser2.pojo.Country;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kukolka on 3/1/17.
 */

public class CountryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.imageView)
    ImageView imageView;

    public CountryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(final Context context, final Country country) {
        itemView.setTag(country);
        textView.setText(country.getPopulation());
        Picasso.with(context)
                .load(country.getImageUrl())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(context)
                                .load(country.getImageUrl())
                                .into(imageView);
                    }
                });
    }
}
