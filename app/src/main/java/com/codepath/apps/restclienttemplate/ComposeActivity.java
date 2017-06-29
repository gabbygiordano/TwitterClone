package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    //a numeric code to identify edit activity
    public final static int EDIT_REQUEST_CODE = 20;
    //keys used for passing data between activities
    public final static String TWEET_TEXT = "tweetText";
    private Button button;
    private ImageView ivProfilePic;
    private TextView tvYourUser;
    private TwitterClient client;
    public EditText getEtComposeTweet;
    String tweet;


    EditText etComposeTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        client = TwitterApp.getRestClient();
        etComposeTweet = (EditText) findViewById(R.id.etComposeTweet);
        tweet = etComposeTweet.getText().toString();


        ivProfilePic = (ImageView) findViewById(R.id.ivProfilePic);
        tvYourUser = (TextView) findViewById(R.id.tvYourUser);
        tvYourUser.setText("@gabby_giordano");
        button = (Button) findViewById(R.id.btTweet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.sendTweet(etComposeTweet.getText().toString(), new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        Tweet newTweet = null;
                        try {
                            Log.i("Info", "Goes here");
                            newTweet = Tweet.fromJSON(response);
                            Intent i = new Intent(ComposeActivity.this, TimelineActivity.class);
                            i.putExtra("TWEET", newTweet);
                            startActivityForResult(i, 1);
                            // finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        throwable.printStackTrace();
                    }
                });
            }
        });
    }


}
