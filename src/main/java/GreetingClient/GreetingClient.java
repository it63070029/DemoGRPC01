package GreetingClient;

import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {
    public static void main(String[] args){
        System.out.println("Hello grpc Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost",50055)
                .usePlaintext()
                .build();
        System.out.println("Creating stub");
//        DummyServiceGrpc.DummyServiceBlockingStub syncClient
//                = DummyServiceGrpc.newBlockingStub(channel);

        GreetServiceGrpc.GreetServiceBlockingStub greetClient;
        greetClient = GreetServiceGrpc.newBlockingStub(channel);


        Greeting greeting = Greeting.newBuilder()
                        .setFirstName("John")
                        .setLastName("Baan")
                        .build();

        GreetRequest greetRequest = GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        GreetResponse greetResponse = greetClient.greet(greetRequest);

        System.out.println(greetResponse.getResult());

        System.out.println("shutting down channel");
        channel.shutdown();
    }
}
