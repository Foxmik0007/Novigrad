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

public class loadingTest {

    @Rule
    public ActivityTestRule<loading> mActivityTestRule= new ActivityTestRule<loading>(loading.class);
    private loading mActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }
    @Test
    public  void testlaunchOfSecondActivityOnButtonClick(){
        assertNotNull(mActivity.findViewById(R.id.connect));
        onView(withId(R.id.connect)).perform(click());
        Activity MainActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(MainActivity);
        MainActivity.finish();
    }
    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}