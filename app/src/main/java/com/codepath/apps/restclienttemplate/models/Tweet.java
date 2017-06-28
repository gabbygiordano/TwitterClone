package com.codepath.apps.restclienttemplate.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gabbygiordano on 6/26/17.
 */

@org.parceler.Parcel
public class Tweet implements Parcelable {

    // list out the attributes
    public String body;
    public long uid; // database ID for the tweet
    public User user;
    public String createdAt;

    protected Tweet(Parcel in) {
        this.body = in.readString();
        this.uid = in.readLong();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.createdAt = in.readString();
    }

    public static final Creator<Tweet> CREATOR = new Creator<Tweet>() {
        @Override
        public Tweet createFromParcel(Parcel in) {
            return new Tweet(in);
        }

        @Override
        public Tweet[] newArray(int size) {
            return new Tweet[size];
        }
    };

    public Tweet() {

    }


    // deserialize the JSON
    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        // extract the values from JSON
        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        return tweet;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(body);
        dest.writeLong(uid);
        dest.writeString(createdAt);
        dest.writeParcelable(this.user, flags);
    }
}
