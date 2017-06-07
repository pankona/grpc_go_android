package io.pankona.grpc_go_android;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class GRPCClientTest extends TestCase {

    protected void setUp() {
        try {
            Thread.sleep(2 * 1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void tearDown() {
        try {
            Thread.sleep(2 * 1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testGetStringViaGRPC() throws Exception {
        GoWrapper go = new GoWrapper();
        go.Hello("testGetStringViaGRPC");

        int port = go.initializeGRPCServer();
        assertEquals(port, 50053);
        GRPCClient client = new GRPCClient(port);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            List<String> result = client.getString();
        }
        long endTime = System.currentTimeMillis();
        assertTrue("Total execution time: " + (endTime-startTime) + "ms", false);

        go.finalizeGRPCServer();
    }

    @Test
    public void testGetStringViaBinding() throws Exception {
        GoWrapper go = new GoWrapper();
        go.Hello("testGetStringViaBinding");

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String result = go.getLargeString();
        }
        long endTime = System.currentTimeMillis();
        assertTrue("Total execution time: " + (endTime-startTime) + "ms", false);
    }
}
