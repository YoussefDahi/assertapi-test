package fr.enedis.teme.api;

import static fr.enedis.teme.api.RestTemplateClientHttpRequestInitializer.init;

import org.springframework.http.client.ClientHttpRequestInitializer;
import org.springframework.web.client.RestTemplate;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RestTemplateBuilder {

	public static RestTemplate build(ServerConfig conf) {

		return build(conf, init(conf));
	}
	
	public static RestTemplate build(ServerConfig conf, ClientHttpRequestInitializer initializer) {

		var rt = new RestTemplate();
		RootUriTemplateHandler.addTo(rt, conf.buildRootUrl());
		rt.getClientHttpRequestInitializers().add(initializer);
		return rt;
	}


}