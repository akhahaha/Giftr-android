package com.akhahaha.giftr.android.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akhahaha.giftr.android.R;


/**
 * A simple {@link ViewFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends ViewFragment {
    private static final String ARG_CURR_UID = "ARG_CURR_UID";

    private Integer currUID;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment DashboardFragment.
     */
    public static DashboardFragment newInstance(Integer currUID) {
        DashboardFragment fragment = new DashboardFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Setup pager
        ViewPager pager = (ViewPager) rootView.findViewById(R.id.dashboard_pager);
        pager.setAdapter(new DashboardFragmentPagerAdapter(
                getActivity().getSupportFragmentManager()));

        // Setup tabs
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.dashboard_tabs);
        tabLayout.setupWithViewPager(pager);

        return rootView;
    }

    private class DashboardFragmentPagerAdapter extends FragmentPagerAdapter {
        private static final int NUM_ITEMS = 3;

        public DashboardFragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public DashboardPageFragment getItem(int position) {
            switch (position) {
                case 0:
                    return DashboardExchangeFragment.newInstance(currUID);
                case 1:
                    return DashboardFriendsFragment.newInstance(currUID);
                case 2:
                    return DashboardProfileFragment.newInstance(currUID);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getItem(position).getPageTitle();
        }
    }
}
