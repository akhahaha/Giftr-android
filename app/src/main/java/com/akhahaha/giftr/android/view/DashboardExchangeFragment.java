package com.akhahaha.giftr.android.view;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.akhahaha.giftr.android.GiftrClient;
import com.akhahaha.giftr.android.R;
import com.akhahaha.giftr.android.event.Event;
import com.akhahaha.giftr.android.event.MatchEvent;
import com.akhahaha.giftr.android.model.Match;
import com.akhahaha.giftr.android.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link DashboardPageFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashboardExchangeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardExchangeFragment extends DashboardPageFragment {
    public static final String EVENT_MATCH_FIND_NEW = "EVENT_FIND_NEW_MATCH";
    public static final String EVENT_MATCH_SELECTED = "EVENT_MATCH_SELECTED";

    private static final String ARG_CURR_UID = "ARG_CURR_UID";

    private Integer currUID;

    private View rootView;

    @Override
    public String getPageTitle() {
        return "Exchange";
    }

    public DashboardExchangeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment DashboardExchangeFragment.
     */
    public static DashboardExchangeFragment newInstance(Integer currUID) {
        DashboardExchangeFragment fragment = new DashboardExchangeFragment();
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
        rootView = inflater.inflate(R.layout.fragment_dashboard_exchange, container, false);

        final View emptyView = rootView.findViewById(R.id.dashboard_exchange_empty);
        final Button newButton = (Button) rootView.findViewById(R.id.dashboard_exchange_find_new);
        final ListView exchangeList = (ListView) rootView.findViewById(
                R.id.dashboard_exchange_list);

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyListener(new Event(EVENT_MATCH_FIND_NEW));
            }
        });

        GiftrClient.getService().getUserMatches(currUID).enqueue(
                new Callback<List<Match>>() {
                    @Override
                    public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                        if (response.body() != null && !response.body().isEmpty()) {
                            emptyView.setVisibility(View.GONE);
                            exchangeList.setVisibility(View.VISIBLE);

                            final ExchangeArrayAdapter adapter = new ExchangeArrayAdapter(
                                    getContext(), response.body());
                            exchangeList.setAdapter(adapter);
                            exchangeList.setOnItemClickListener(
                                    new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view,
                                                                int position, long id) {
                                            notifyListener(new MatchEvent(EVENT_MATCH_SELECTED,
                                                    adapter.getItem(position)));
                                        }
                                    });
                        } else {
                            emptyView.setVisibility(View.VISIBLE);
                            exchangeList.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Match>> call, Throwable t) {

                    }
                }

        );

        return rootView;
    }

    private class ExchangeArrayAdapter extends BaseAdapter {
        private Context context;
        private List<Match> matches;

        public ExchangeArrayAdapter(Context context, List<Match> matches) {
            this.context = context;
            this.matches = matches;
        }

        @Override
        public int getCount() {
            return matches.size();
        }

        @Override
        public Match getItem(int position) {
            return matches.get(position);
        }

        @Override
        public long getItemId(int position) {
            return matches.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                // Inflate the layout for the list item if necessary
                convertView = inflater.inflate(R.layout.listitem_exchange, parent, false);
            }

            final TextView statusText = (TextView) convertView.findViewById(R.id.exchange_status);
            final TextView ageText = (TextView) convertView.findViewById(R.id.exchange_age);
            final TextView usernameText = (TextView) convertView.findViewById(
                    R.id.exchange_username);

            Match match = matches.get(position);
            statusText.setText(match.getStatus().getName());
            ageText.setText(DateUtils.getRelativeTimeSpanString(match.getCreated().getTime(),
                    System.currentTimeMillis(), DateUtils.DAY_IN_MILLIS));
            Integer uid = currUID.equals(match.getUser2ID()) ?
                    match.getUser1ID() : match.getUser2ID();
            GiftrClient.getService().getUser(uid).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    usernameText.setText(response.body().getUsername());
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });

            return convertView;
        }
    }
}
