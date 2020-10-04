package com.cupcakes.kandycupcakes;

import android.view.View;
import android.widget.EditText;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class customerViewTest {


    public ActivityTestRule<customerView> mActivityTestRule = new ActivityTestRule<customerView>(customerView.class);
    private customerView cv = null;

    @Before
    public void setUp() {
        cv = mActivityTestRule.getActivity();
    }

    @Test
    public void test() {
        View view = cv.findViewById(R.id.txtFaq);
        assertNotNull(view);
    }

    @After
    public void tearDown() {
        cv = null;
    }

}