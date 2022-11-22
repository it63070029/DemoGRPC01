package SumServer;

import com.proto.sum.Sum;
import com.proto.sum.SumRequest;
import com.proto.sum.SumResponse;
import com.proto.sum.SumServiceGrpc;
import io.grpc.stub.StreamObserver;

public class SumServicelmpl extends SumServiceGrpc.SumServiceImplBase{
    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse>
            responseObserver){
        //Block 1: extract the data required
        Sum sum = request.getSum();
        Integer num1 = sum.getNum1();
        Integer num2 = sum.getNum2();

        //Block:2 create the response message
        String result ="Server Output :"+num1+"+ "+num2+"="+(num1+num2);
        SumResponse response = SumResponse.newBuilder()
                .setResult(result)
                .build();
        //Block:3 send the response
        responseObserver.onNext(response);

        //Block:4 complete the RPC call
        responseObserver.onCompleted();
    }

}
