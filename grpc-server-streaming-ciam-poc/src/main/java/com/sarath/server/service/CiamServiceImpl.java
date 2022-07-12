package com.sarath.server.service;

import com.sarath.ciam.CiamServiceGrpc;
import com.sarath.ciam.ProfileRequest;
import com.sarath.ciam.ProfileResponse;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CiamServiceImpl extends CiamServiceGrpc.CiamServiceImplBase {

	@Override
	public void migrateUsers(ProfileRequest request, StreamObserver<ProfileResponse> responseObserver) {

		for (int i = 0; i < 100000; i++) {
			System.out.println("i ------> "+i);
			responseObserver
					.onNext(ProfileResponse.newBuilder().setAddress("address " + i).setName("name " + i).build());
			if (i % 10000 == 0) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		responseObserver.onCompleted();
	}

}
