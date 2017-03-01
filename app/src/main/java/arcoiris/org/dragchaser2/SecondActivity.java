package arcoiris.org.dragchaser2;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.Collections;

import arcoiris.org.dragchaser2.pojo.Country;
import arcoiris.org.dragchaser2.views.CountryAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.clRoot)
    CoordinatorLayout clRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Wow subtitle");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void showSnackbar(String memo) {
        Snackbar.make(clRoot, memo, Snackbar.LENGTH_LONG).show();
    }
}
