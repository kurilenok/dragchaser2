package arcoiris.org.dragchaser2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import arcoiris.org.dragchaser2.pojo.City;
import arcoiris.org.dragchaser2.views.CityViewHolder;
import arcoiris.org.dragchaser2.views.RecyclerItemClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class SecondActivityFragment extends Fragment {

    @BindView(R.id.etName)
    EditText etName;

    @BindView(R.id.spinner)
    AppCompatSpinner spinner;

    @BindView(R.id.rvCities)
    RecyclerView rvCities;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second, container, false);
        ButterKnife.bind(this, view);

//        CountryAdapter adapter = new CountryAdapter(getActivity());
//        Country estonia = new Country("https://raw.githubusercontent.com/kurilenok/EuroCII/master/app/src/main/res/drawable-hdpi/be.gif", "1,900,000");
//        adapter.setData(Collections.singletonList(estonia));
//        recyclerView.setAdapter(adapter);


        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("categories");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                Set<String> dataSet = map.keySet();
                String[] dataArray = dataSet.toArray(new String[dataSet.size()]);
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                        getActivity(), android.R.layout.simple_spinner_item,
                        dataArray);

                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.data));

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        DatabaseReference dbRef2 = FirebaseDatabase.getInstance().getReference("cities");
        dbRef2.keepSynced(true);

        final FirebaseRecyclerAdapter<City, CityViewHolder> fbAdapter =
                new FirebaseRecyclerAdapter<City, CityViewHolder>(
                        City.class, R.layout.city, CityViewHolder.class, dbRef2) {
                    @Override
                    protected void populateViewHolder(CityViewHolder viewHolder, City city, int position) {
                        viewHolder.populate(city);
                    }
                };

        rvCities.setAdapter(fbAdapter);

        rvCities.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), rvCities,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(final View view, final int position) {
                        final String name = fbAdapter.getItem(position).getName().toUpperCase();
                        fbAdapter.getRef(position).removeValue().addOnCompleteListener(
                                new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Snackbar.make(view, "Bye-bye ***" + name + "*** !", Snackbar.LENGTH_LONG).show();
                                    }
                                });
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                }));

        return view;
    }

    @OnClick(R.id.bSubmit)
    public void onClick() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            Snackbar.make(view, "Name cannot be empty", Snackbar.LENGTH_LONG).show();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("cities");
        String id = db.push().getKey();
        final City city = new City(id, etName.getText().toString().trim(), spinner.getSelectedItem().toString());

        db.child(id).setValue(city, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                etName.setText("");
                spinner.setSelection(0);
                progressDialog.dismiss();

                getSecondActivity().showSnackbar("***" + city.getName().toUpperCase() + "*** was added to catalog");

//                Snackbar.make(view, "***" + city.getName().toUpperCase() + "*** was added to catalog",
//                        Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private SecondActivity getSecondActivity() {
        return (SecondActivity) getActivity();
    }

}
