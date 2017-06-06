package io.pankona.grpc_go_android;

import java.util.List;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.pankona.stringservice.*;

public class GRPCClient {
    int m_port = 0;
    public GRPCClient(int port) {
        m_port = port;
    }

    public List<String> getString() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", m_port)
                .usePlaintext(true)
                .build();

        StringServiceGrpc.StringServiceBlockingStub stub = StringServiceGrpc.newBlockingStub(channel);
        Empty e = Empty.newBuilder().build();
        LargeString largeString = stub.getLargeString(e);
        return largeString.getStringListList();
    }
}
