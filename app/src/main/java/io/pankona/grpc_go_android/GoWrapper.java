package io.pankona.grpc_go_android;

import hello.Hello;

public class GoWrapper {
    public GoWrapper() {
        // nop
    }

    public String Hello(String name) {
        return Hello.greetings(name);
    }

    public int initializeGRPCServer() {
        return (int)Hello.initializeStringService();
    }

    public void finalizeGRPCServer() {
        Hello.finalizeStringService();
    }

    public String getLargeString() {
        return Hello.getLargeString();
    }
}
