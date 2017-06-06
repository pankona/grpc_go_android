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
        int port = go.initializeGRPCServer();
        assertEquals(port, 50053);
        GRPCClient client = new GRPCClient(port);

        // TODO: loop
        List<String> result = client.getString();
        Log.d("", result.toString());

        go.finalizeGRPCServer();
    }

    @Test
    public void testGetStringViaBinding() throws Exception {
        GoWrapper go = new GoWrapper();

        // TODO: loop
        String result = go.getLargeString();
        Log.d("", result);
    }
}
