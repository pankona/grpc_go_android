package io.pankona.grpc_go_android;

import java.util.List;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.pankona.stringservice.*;

public class GRPCClient {
    ManagedChannel m_channel;
    StringServiceGrpc.StringServiceBlockingStub m_stub;
    public GRPCClient(int port) {
        m_channel = ManagedChannelBuilder.forAddress("localhost", port)
                .usePlaintext(true)
                .build();
        m_stub = StringServiceGrpc.newBlockingStub(m_channel);
    }

    public List<String> getString() {
        Empty e = Empty.newBuilder().build();
        LargeString largeString = m_stub.getLargeString(e);
        return largeString.getStringListList();
    }
}
