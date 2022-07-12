package com.sarath.grpcclient.grpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sarath.ciam.CiamServiceGrpc.CiamServiceBlockingStub;
import com.sarath.ciam.ProfileRequest;

import lombok.Setter;
import net.devh.boot.grpc.client.inject.GrpcClient;

/**
 * Configuration of SpringGrpcServerClient
 */
@Component
@Setter
public class SpringGrpcServerClient {

	@GrpcClient("springgrpcserver")
	private CiamServiceBlockingStub ciamServiceBlockingStub;

	@Autowired
	private Producer producer;

	/**
	 * Method calling hello method from SpringGrpcServer service.
	 */
	public String migrateUsers() {
		ProfileRequest pRequest = ProfileRequest.newBuilder().setCustId("1").build();
		this.ciamServiceBlockingStub.migrateUsers(pRequest)
				.forEachRemaining(p ->producer.sendMessage(p.getAddress() + " " + p.getName()));
		return "SUCCESS";

	}

}
