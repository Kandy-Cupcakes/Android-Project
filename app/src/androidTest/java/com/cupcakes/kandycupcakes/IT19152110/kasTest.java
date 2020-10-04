package com.cupcakes.kandycupcakes.IT19152110;

import android.widget.EditText;
import android.widget.ListView;

import androidx.test.rule.ActivityTestRule;

import com.cupcakes.kandycupcakes.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

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

