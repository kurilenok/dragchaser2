package arcoiris.org.dragchaser2.views;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import arcoiris.org.dragchaser2.R;
import arcoiris.org.dragchaser2.pojo.Country;

/**
 * Created by kukolka on 3/1/17.
 */

public class CountryAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<Country> countries;
    private LayoutInflater inflater;
    private CountryListner listner;

    public CountryAdapter(Activity activity) {
        this.activity = activity;
        this.listner = listner;
        inflater = activity.getLayoutInflater();
        countries = new ArrayList<>();
    }

    public void setData(List<Country> countries) {
        this.countries.clear();
        this.countries.addAll(countries);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Country country = countries.get(position);
        ((CountryViewHolder) holder).populate(activity, country);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public interface CountryListner {
        void onCountryClicked(Country country);
    }

}
