package com.example.novigrad30;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class PortailAdministrateurTest {


    @Rule
    public ActivityTestRule<PortailAdministrateur> mActivityTestRule= new ActivityTestRule<PortailAdministrateur>(PortailAdministrateur.class);
    private PortailAdministrateur mActivity=null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }
    @Test
    public void testlaunch(){
        View view = mActivity.findViewById(R.id.btnGestionClient);
        assertNotNull(view);

    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }


}