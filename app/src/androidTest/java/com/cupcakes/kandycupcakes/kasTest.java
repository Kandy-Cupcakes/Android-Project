package com.cupcakes.kandycupcakes;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.*;

public class kasTest {

    public ActivityTestRule<kas> mActivityTestRule = new ActivityTestRule<>(kas.class);
    private kas kas = null;

    @Before
    public void setUp() {
        kas = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        ListView listView = kas.findViewById(R.id.faqList);
        assertNotNull(listView);
    }

    @After
    public void tearDown() {
        kas = null;
    }


    @Before
    public void setUp2() {
        kas = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch2() {
        EditText que = kas.findViewById(R.id.questionU);
        assertNotNull(que);
    }

    @After
    public void tearDown2() {
        kas = null;
    }
}

