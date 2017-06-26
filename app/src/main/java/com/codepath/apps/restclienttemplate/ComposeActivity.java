package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class ComposeActivity extends AppCompatActivity {

    //a numeric code to identify edit activity
    public final static int EDIT_REQUEST_CODE = 20;
    //keys used for passing data between activities
    public final static String TWEET_TEXT = "tweetText";

    EditText etComposeTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        }
}
