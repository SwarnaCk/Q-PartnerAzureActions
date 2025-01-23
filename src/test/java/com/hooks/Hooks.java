package com.hooks;

import com.base.BaseClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;


public class Hooks extends BaseClass{

    @Before
    public void runBeforeTest() {
        setUp();
    }

    @After
    public void runAfterTest() {
      tearDown();
    }
}
