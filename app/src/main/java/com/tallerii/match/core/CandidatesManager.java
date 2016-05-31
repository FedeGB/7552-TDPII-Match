package com.tallerii.match.core;

import com.tallerii.match.core.query.http.HttpCandidatesListRequester;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Demian on 26/05/2016.
 */
public class CandidatesManager {
    Queue<UserProfile> candidatesList = new LinkedList<>();
    HttpCandidatesListRequester httpMatchListRequester = new HttpCandidatesListRequester();

    public UserProfile getNextCandidate(){
        return candidatesList.remove();
    }

    public void addToMatchQueue(Collection collection){
        candidatesList.addAll(collection);
    }

    public boolean hasCandidate(){
        return !candidatesList.isEmpty();
    }
}
