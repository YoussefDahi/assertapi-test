package org.usf.assertapi.test;

import org.springframework.web.client.RestTemplate;
import org.usf.assertapi.core.ApiCheck;
import org.usf.assertapi.core.ApiCompareResult;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author u$f
 *
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j(topic="org.usf.assertapi.core.ApiAssertion")
public final class TestContext {

	private static final ThreadLocal<TestContext> threadLocal = new ThreadLocal<>();
	
	private final RestTemplate template;
	private final String traceUri;

	public static void setLocalContext(RestTemplate template, String traceUri) {
		threadLocal.set(new TestContext(template, traceUri));
	}
	
	public static void testCaseOrigin(ApiCheck ar, ApiCompareResult cr) {
		var context = threadLocal.get();
		if(context == null) {
			log.warn("cannot trace result");
		}
		else {
			context.template.put(context.traceUri, cr, ar.getId());
		}
	}
	
}
