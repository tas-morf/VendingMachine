package com.vending.android.controller.fragment;

import com.vending.android.view.AdDetailsIndicator;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;

/**
 * This is more of an example unit test to demonstrate that I can actually write one, and that the
 * fragment has been written in a way that supports this. It is by no means complete, but given enough time, 
 * it can easily be.
 */
public class AdDetailsFragmentTest {

    private static final String A_PHONE_NUMBER = "12345";
    private static final String A_MAPS_URI = "amapsuri";

    //Mocks
    @Mock
    private AdFetcher mockAdFetcher;
    @Mock
    private FavoritesManager mockFavoritesManager;
    @Mock
    private AdDetailsIndicator mockAdDetailsIndicator;
    @Mock
    private LayoutInflater mockInflater;

    private AdDetailsFragment sut;
    
    private Intent startedActivityIntent;
    
    @Before
    public void setup() throws NoSuchMethodException {
        MockitoAnnotations.initMocks(this);
        sut = new AdDetailsFragment(mockAdFetcher, mockFavoritesManager, mockAdDetailsIndicator) {
            @Override
            public void startActivity(Intent intent) {
                    startedActivityIntent = intent;
            }

        };
    }

    @Test
    public void onResumeResumesMap() {
        sut.onResume();
        verify(mockAdDetailsIndicator).resumeMap();
    }

    @Test
    public void onPausePausesMap() {
        sut.onPause();
        verify(mockAdDetailsIndicator).pauseMap();
    }

    @Test
    public void onDestroyDestroysMap() {
        sut.onDestroy();
        verify(mockAdDetailsIndicator).destroyMap();
    }

    @Test
    public void onDestroyViewClearsListener() {
        sut.onDestroyView();
        verify(mockAdFetcher).clearListener();
    }
    
    @Test
    public void onAdFetchFailureShowsError() {
        sut.onAdFetchFailure();
        verify(mockAdDetailsIndicator).showError();
    }

    @Test
    public void onCallRequestedStartsCallIntent() {
        sut.onCallRequested(A_PHONE_NUMBER);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + A_PHONE_NUMBER));
        assertThat(startedActivityIntent.toUri(0), Matchers.equalTo(intent.toUri(0)));
    }

    @Test
    public void onSmsRequestedStartsSMSIntent() {
        sut.onSmsRequested(A_PHONE_NUMBER);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + A_PHONE_NUMBER));
        assertThat(startedActivityIntent.toUri(0), Matchers.equalTo(intent.toUri(0)));
    }

    @Test
    public void oGoToMapsRequestedStartsMapsIntent() {
        sut.onGoToMapsRequested(A_MAPS_URI);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(A_MAPS_URI));
        assertThat(startedActivityIntent.toUri(0), Matchers.equalTo(intent.toUri(0)));
    }
}