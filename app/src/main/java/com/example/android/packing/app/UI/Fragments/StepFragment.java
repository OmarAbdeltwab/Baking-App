package com.example.android.packing.app.UI.Fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.packing.app.Data.Step;
import com.example.android.packing.app.MainActivity;
import com.example.android.packing.app.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by Omar on 21-May-18.
 */

public class StepFragment extends Fragment {

    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private final static String LatestPlayPointKey = "LATESTPLAY";
    private final static String  PlayStateKey = "LATESTPLAYState";

    public StepFragment(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (((MainActivity)context).getSupportActionBar() != null)
            ((MainActivity)context).getSupportActionBar().setDisplayHomeAsUpEnabled( true );
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        container.removeAllViews();
        View rootView;
        Step CurrentStep =MainActivity.AllRecipes.getResults()[MainActivity.CurrentRecipePos].getSteps().get( MainActivity.CurrentStepPos );

            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && !MainActivity.IsTablet) {
                rootView = inflater.inflate( R.layout.step_details_fragment_landscape, container, false);
            }
            else {
                rootView = inflater.inflate( R.layout.step_details_fragment, container, false );
                TextView LD = rootView.findViewById( R.id.tv_step_ld ) ;


                LD.setText(CurrentStep.getDescription());
            }


        mPlayerView=rootView.findViewById( R.id.media_player );

        initializePlayer(CurrentStep. getVideoURL() );
if( mExoPlayer!=null){
            if (savedInstanceState != null) {

                mExoPlayer.seekTo( savedInstanceState.getLong( LatestPlayPointKey ) );
                mExoPlayer.setPlayWhenReady( savedInstanceState.getBoolean( PlayStateKey ) );
            } else {

                mExoPlayer.setPlayWhenReady( true );
            }
    mExoPlayer.prepare( mediaSource );
}
        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState( outState );
        if (mExoPlayer!=null) {

            outState.putLong(  LatestPlayPointKey,position );
            outState.putBoolean( PlayStateKey,mExoPlayer.getPlayWhenReady() );
        }
        else
            {
                outState.putLong(  LatestPlayPointKey,0 );
                outState.putBoolean( PlayStateKey,true );
            }
    }
    Long position=0L;
    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
}
    MediaSource mediaSource;

    private void initializePlayer(String mediaUrl) {

        Uri mediaUri =Uri.parse( mediaUrl );
    if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
        if (mediaUrl.length()!=0) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance( getContext(), trackSelector, loadControl );
            mPlayerView.setPlayer( mExoPlayer );
            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent( getContext(), "ClassicalMusicQuiz" );
            mediaSource = new ExtractorMediaSource( mediaUri, new DefaultDataSourceFactory(
                    getContext(), userAgent ), new DefaultExtractorsFactory(), null, null );



        }
    else   {
            ImageView novideoIV =new ImageView( getContext() );
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT );
            novideoIV.setImageResource( R.drawable.no_video_available );
            mPlayerView.setVisibility( View.GONE );
            ((LinearLayout) mPlayerView.getParent()).addView(  novideoIV,layoutParams);

    }
    }

}


    /**
     * Release ExoPlayer.
     */
    private void releasePlayer() {
     if (mExoPlayer!=null) {

         position=mExoPlayer.getCurrentPosition();

         mExoPlayer.stop();
         mExoPlayer.release();
         mExoPlayer = null;
     }
    }
}
