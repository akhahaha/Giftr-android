package com.akhahaha.giftr.android.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akhahaha.giftr.android.R;


/**
 * A simple {@link DashboardPageFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashboardFriendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFriendsFragment extends DashboardPageFragment {
    private static final String ARG_CURR_UID = "ARG_CURR_UID";

    private Integer currUID;

    @Override
    public String getPageTitle() {
        return "Friends";
    }

    public DashboardFriendsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DashboardFriendsFragment.
     */
    public static DashboardFriendsFragment newInstance(Integer currUID) {
        DashboardFriendsFragment fragment = new DashboardFriendsFragment();
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
        return inflater.inflate(R.layout.fragment_dashboard_friends, container, false);
    }
}
