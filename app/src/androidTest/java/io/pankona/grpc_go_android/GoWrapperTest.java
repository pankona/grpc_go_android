package io.pankona.grpc_go_android;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GoWrapperTest extends TestCase {

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
    public void testHello() throws Exception {
        GoWrapper go = new GoWrapper();
        String result = go.Hello("");
        Log.d("", go.getLargeString());
    }

    @Test
    public void testGRPCServerUpDown() throws Exception {
        GoWrapper go = new GoWrapper();
        int port = go.initializeGRPCServer();
        assertEquals(port, 50053);
        go.finalizeGRPCServer();
    }
}
