package arcoiris.org.dragchaser2.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import arcoiris.org.dragchaser2.R;
import arcoiris.org.dragchaser2.pojo.City;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kukolka on 3/1/17.
 */

public class CityViewHolder extends RecyclerView.ViewHolder {

    View view;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvCategory)
    TextView tvCategory;

    public CityViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void populate(City city) {
        tvName.setText(city.getName());
        tvCategory.setText(city.getCategory());
    }

}
