package com.example.novigrad30;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class SignUPRedirectionTest {


    @Rule
    public ActivityTestRule<SignUPRedirection> mActivityTestRule= new ActivityTestRule<SignUPRedirection>(SignUPRedirection.class);
    private SignUPRedirection mActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(ClientRg.class.getName(),null,false);


    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }
    @Test
    public  void testlaunchOfSecondActivityOnButtonClick(){
        assertNotNull(mActivity.findViewById(R.id.btnClient));
        onView(withId(R.id.btnClient)).perform(click());
        Activity ClientRg = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(ClientRg);
        ClientRg.finish();
    }

    @After
    public void tearDown() throws Exception {
    }


}