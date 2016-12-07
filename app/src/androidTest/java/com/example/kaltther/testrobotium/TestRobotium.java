package com.example.kaltther.testrobotium;

import android.content.pm.PackageManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.Manifest.permission.READ_CONTACTS;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kaltther on 20/11/2016.
 */

public class TestRobotium {
    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    private Solo solo;
    private static final String hello = "HELLO", email = "hello@cat.cat", password = "kitty";

    @Before
    public void setUp() throws Exception {
        //setUp() is run before a test case is started.
        //This is where the solo object is created.
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),
                activityTestRule.getActivity());
    }

    @After
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }

    @Test
    public void testLoginActivity() throws Exception {

        //test email invalid
        solo.unlockScreen();
        solo.enterText(0, hello);
        solo.clickOnView(solo.getView(R.id.email_sign_in_button));
        assertTrue("not found", solo.searchText("This email address is invalid"));

        //test email and password valid
        solo.clearEditText(0);
        solo.enterText(0, email);
        solo.enterText(1, password);
        solo.clickOnView(solo.getView(R.id.email_sign_in_button));
    }
}
