package SumClient;

import SumServer.SumServicelmpl;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import com.proto.sum.Sum;
import com.proto.sum.SumRequest;
import com.proto.sum.SumResponse;
import com.proto.sum.SumServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class SumClient {
    public static void main(String[] args){
        System.out.println("Hello grpc Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost",55554)
                .usePlaintext()
                .build();
        System.out.println("Creating stub");
//        DummyServiceGrpc.DummyServiceBlockingStub syncClient
//                = DummyServiceGrpc.newBlockingStub(channel);

        SumServiceGrpc.SumServiceBlockingStub sumClient;
        sumClient = SumServiceGrpc.newBlockingStub(channel);


        Sum sum = Sum.newBuilder()
                .setNum1(5)
                .setNum2(10)
                .build();

        SumRequest sumRequest = SumRequest.newBuilder()
                .setSum(sum)
                .build();

        SumResponse sumResponse = sumClient.sum(sumRequest);

        System.out.println(sumResponse.getResult());

        System.out.println("shutting down channel");
        channel.shutdown();
    }
}
