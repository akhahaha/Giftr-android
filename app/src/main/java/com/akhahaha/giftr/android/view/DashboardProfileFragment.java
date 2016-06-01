package com.akhahaha.giftr.android.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akhahaha.giftr.android.GiftrClient;
import com.akhahaha.giftr.android.R;
import com.akhahaha.giftr.android.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link DashboardPageFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashboardProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardProfileFragment extends DashboardPageFragment {
    private static final String ARG_CURR_UID = "ARG_CURR_UID";

    private Integer currUID;
    private View rootView;

    @Override
    public String getPageTitle() {
        return "My Profile";
    }

    public DashboardProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DashboardProfileFragment.
     */
    public static DashboardProfileFragment newInstance(Integer currUID) {
        DashboardProfileFragment fragment = new DashboardProfileFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CURR_UID, currUID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currUID = getArguments().getInt(ARG_CURR_UID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_dashboard_profile, container, false);

        final TextView usernameText = (TextView) rootView.findViewById(
                R.id.dashboard_profile_username);
        final TextView ageText = (TextView) rootView.findViewById(R.id.dashboard_profile_age);
        final TextView genderText = (TextView) rootView.findViewById(R.id.dashboard_profile_gender);
        final TextView locationText = (TextView) rootView.findViewById(
                R.id.dashboard_profile_location);
        final TextView interestsText = (TextView) rootView.findViewById(
                R.id.dashboard_profile_interests);

        GiftrClient.getService().getUser(currUID).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                // TODO: Set image
                usernameText.setText(user.getUsername());
                // TODO: Age
                genderText.setText(user.getGender().getName());
                locationText.setText(user.getLocation());
                interestsText.setText(user.getInterests()); // TODO: Format interests
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        return rootView;
    }
}
