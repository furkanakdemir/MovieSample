package net.furkanakdemir.moviesample.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import net.furkanakdemir.moviesample.R;
import net.furkanakdemir.moviesample.ui.base.BaseFragment;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends BaseFragment {


    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.detailNameTextView)
    TextView detailNameTextView;

    @BindView(R.id.detailDateTextView)
    TextView detailDateTextView;

    @BindView(R.id.overviewTextView)
    TextView overviewTextView;


    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie_detail;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        collapsingToolbarLayout.setTitle("Detail");

        detailNameTextView.setText("Movie Name");
        detailDateTextView.setText("Movie Date");
        overviewTextView.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");


    }
}
