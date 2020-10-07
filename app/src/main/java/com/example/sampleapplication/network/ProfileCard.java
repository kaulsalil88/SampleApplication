package com.example.sampleapplication.network;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sampleapplication.R;
import com.example.sampleapplication.ui.main.MainViewModel;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

@Layout(R.layout.item_profile)
public class ProfileCard {
    @View(R.id.profileImageView)
    private ImageView profileImageView;

    @View(R.id.tv_desc)
    private TextView description;

    @View(R.id.tv_details)
    private TextView details;

    private Profile mProfile;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;
    private MainViewModel mMainViewModel;

    public ProfileCard(Context context, Profile profile, SwipePlaceHolderView swipeView, MainViewModel mainViewModel) {
        mContext = context;
        mProfile = profile;
        mSwipeView = swipeView;
        mMainViewModel = mainViewModel;
    }

    @Resolve
    private void onResolved() {
        Glide.with(mContext).
                load(mProfile.getUser().getPicture()).
                into(profileImageView);
        details.setText(mProfile.getUser().component1());

    }

    @SwipeOut
    private void onSwipedOut() {
        Log.d("EVENT", "onSwipedOut");
        mSwipeView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn() {
        Log.d("EVENT", "onSwipedIn");
        mMainViewModel.saveFavouriteProfile(mProfile);
    }

    @SwipeInState
    private void onSwipeInState() {
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState");
    }

    private void setViewModel(MainViewModel mainViewModel) {
        mMainViewModel = mainViewModel;
    }

}
