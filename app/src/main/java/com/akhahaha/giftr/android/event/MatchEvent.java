package com.akhahaha.giftr.android.event;

import com.akhahaha.giftr.android.model.Match;

/**
 * Match event
 * Created by Alan on 5/31/2016.
 */
public class MatchEvent extends Event {
    private Match match;

    public MatchEvent(String eventTag, Match match) {
        super(eventTag);
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
