package com.example.novigrad30;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class ClientRgTest {


    @Rule
    public ActivityTestRule<ClientRg> mActivityTestRule = new
            ActivityTestRule<>(ClientRg.class);

    @Test
    public void emailIsInvalid() {
        onView(withId(R.id.etRecueillirNom)).perform(typeText("user"), closeSoftKeyboard());
        onView(withId(R.id.etRecueillirPrenom)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.etRecueillirMotDePasse)).perform(typeText("test"), closeSoftKeyboard());
        onView(withId(R.id.etRecueillirMail)).perform(typeText("email@"), closeSoftKeyboard());
        // onView(withId(R.id.btnINSCRIPTION)).perform(click());

    }


}