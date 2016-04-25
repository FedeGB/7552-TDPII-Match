package com.tallerii.match;

import org.json.JSONObject;

/**
 * Created by Demian on 24/04/2016.
 */
public interface JsonSerializable {
    public JSONObject jsonSerialize();
    public void jsonDeserialize(JSONObject jsonObject);
}
