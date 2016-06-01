package com.akhahaha.giftr.android.flow;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import com.akhahaha.giftr.android.event.Event;
import com.akhahaha.giftr.android.event.MatchEvent;
import com.akhahaha.giftr.android.view.DashboardExchangeFragment;
import com.akhahaha.giftr.android.view.DashboardFragment;

/**
 * Dashboard presentation controller
 * Created by Alan on 5/31/2016.
 */
public class DashboardPresenter extends Presenter {
    private Fragment viewFragment;
    private Integer currUID;

    public DashboardPresenter(Integer currUID) {
        this.currUID = currUID;
        viewFragment = DashboardFragment.newInstance(currUID);
    }

    @Override
    public boolean handleEvent(Event event) {
        Presenter nextPresenter;
        switch (event.getEventTag()) {
            default:
                return false;
        }
    }

    @Override
    public boolean handleEvent(KeyEvent event) {
        return false;
    }

    @Override
    public Fragment getViewFragment() {
        return viewFragment;
    }
}
