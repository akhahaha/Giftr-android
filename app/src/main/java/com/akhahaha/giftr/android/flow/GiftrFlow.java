package com.akhahaha.giftr.android.flow;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;

import com.akhahaha.giftr.android.R;

/**
 * Giftr flow controller
 * Created by Alan on 5/31/2016.
 */
public class GiftrFlow extends FlowController {
    private Integer currUID;

    public GiftrFlow(FragmentActivity activity, int resourceID) {
        super(activity, resourceID);

        // Initialize user session and preferences
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor settingsEditor = settings.edit();
        currUID = settings.getInt(getActivity().getString(R.string.pref_curr_uid), -1);
        if (currUID == -1) {
            currUID = 1; // TODO: Implement login
            settingsEditor.putInt(getActivity().getString(R.string.pref_curr_uid), currUID);
        }

        setPresenter(new DashboardPresenter(currUID));
    }
}
