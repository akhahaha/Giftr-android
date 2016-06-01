package com.akhahaha.giftr.android.flow;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import com.akhahaha.giftr.android.event.Event;
import com.akhahaha.giftr.android.view.FindExchangeFragment;

/**
 * Find Exchange presentation controller
 * Created by Alan on 5/31/2016.
 */
public class FindExchangePresenter extends Presenter {
    private Fragment viewFragment;
    private Integer currUID;

    public FindExchangePresenter(Integer currUID) {
        this.currUID = currUID;
        viewFragment = FindExchangeFragment.newInstance(currUID);
    }

    @Override
    public boolean handleEvent(Event event) {
        return false;
    }

    @Override
    public boolean handleEvent(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK:
                pop();
                return true;
            default:
                return false;
        }
    }

    @Override
    public Fragment getViewFragment() {
        return viewFragment;
    }
}
