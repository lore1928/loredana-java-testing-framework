package com.company.api.Listeners;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("[TESTNG] Suite started: " + context.getSuite().getName() + "[THREAD]" + Thread.currentThread().getName());
        }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("[TESTNG] Suite finished: " + context.getSuite().getName() + "[THREAD]" + Thread.currentThread().getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[TESTING] Test started: " + result.getName() + "[THREAD]" + Thread.currentThread().getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[PASS] " + result.getName()+ "[THREAD]" + Thread.currentThread().getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[FAIL] " + result.getName());
        if (result.getThrowable() != null) {
            result.getThrowable().printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[SKIP] " + result.getName() + "[THREAD]" + Thread.currentThread().getName());
    }

}
