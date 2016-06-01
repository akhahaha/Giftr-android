package com.akhahaha.giftr.android.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.akhahaha.giftr.android.GiftrClient;
import com.akhahaha.giftr.android.R;
import com.akhahaha.giftr.android.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link ViewFragment} subclass.
 * Use the {@link FindExchangeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindExchangeFragment extends ViewFragment {
    private static final String ARG_CURR_UID = "ARG_CURR_UID";

    private Integer currUID;

    private View rootView;

    public FindExchangeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param currUID Current User ID.
     * @return A new instance of fragment FindExchangeFragment.
     */
    public static FindExchangeFragment newInstance(Integer currUID) {
        FindExchangeFragment fragment = new FindExchangeFragment();
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
        rootView = inflater.inflate(R.layout.fragment_find_exchange, container, false);

        final ListView userList = (ListView) rootView.findViewById(R.id.find_exchange_list);

        GiftrClient.getService().getAllUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body() != null && !response.body().isEmpty()) {
                    // Add all users except current
                    List<User> filteredUsers = new ArrayList<User>();
                    for (User user : response.body()) {
                        if (!user.getId().equals(currUID)) {
                            filteredUsers.add(user);
                        }
                    }

                    userList.setAdapter(new UserArrayAdapter(getContext(), filteredUsers));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

        return rootView;
    }

    private class UserArrayAdapter extends BaseAdapter {
        private Context context;
        private List<User> users;

        public UserArrayAdapter(Context context, List<User> users) {
            this.context = context;
            this.users = users;
        }

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public User getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return users.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                // Inflate the layout for the list item if necessary
                convertView = inflater.inflate(R.layout.listitem_user, parent, false);
            }

            final TextView ageText = (TextView) convertView.findViewById(R.id.age);
            final TextView genderText = (TextView) convertView.findViewById(R.id.gender);
            final TextView locationText = (TextView) convertView.findViewById(R.id.location);
            final TextView usernameText = (TextView) convertView.findViewById(R.id.username);
            final TextView interestsText = (TextView) convertView.findViewById(R.id.interests);

            // TODO: Age
            User user = getItem(position);
            genderText.setText(user.getGender().getName());
            locationText.setText(user.getLocation());
            usernameText.setText(user.getUsername());
            interestsText.setText(user.getInterests());

            return convertView;
        }
    }
}
