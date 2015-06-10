package com.mt.zt.im.model;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public final class HttpEntityEnclosingGetRequest extends
		HttpEntityEnclosingRequestBase {

	public final static String METHOD_NAME = "GET";

	public HttpEntityEnclosingGetRequest() {
		super();
	}

	public HttpEntityEnclosingGetRequest(final URI uri) {
		super();
		setURI(uri);
	}

	/**
	 * @throws IllegalArgumentException
	 *             if the uri is invalid.
	 */
	public HttpEntityEnclosingGetRequest(final String uri) {
		super();
		setURI(URI.create(uri));
	}

	@Override
	public String getMethod() {
		return METHOD_NAME;
	}

}
