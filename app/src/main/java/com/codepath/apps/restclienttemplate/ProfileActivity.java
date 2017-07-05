package com.codepath.apps.restclienttemplate;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codepath.apps.restclienttemplate.fragments.UserTimelineFragment;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String screenName = getIntent().getStringExtra("screen_name");

        // create the user fragment
        UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(screenName);

        // display tye user timeline fragment inside container

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // make changes

        ft.replace(R.id.flContainer, userTimelineFragment);

        // commit transaction
        ft.commit();
    }
}
