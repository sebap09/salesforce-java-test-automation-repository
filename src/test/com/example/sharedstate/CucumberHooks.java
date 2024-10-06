package com.example.sharedstate;

import io.cucumber.java.*;

public class CucumberHooks {
    private static final TestContext testContext = TestContext.getInstance();
    private static final Hook hook = testContext.getHook();

    @BeforeAll
    public static void beforeAll() {
        hook.beforeAllHook();
    }

    @Before("@initSessionBefore")
    public static void before(){
        hook.initSessionHook();
    }
    @After("@endSessionAfter")
    public static void after() {
        hook.endSessionHook();
    }
}