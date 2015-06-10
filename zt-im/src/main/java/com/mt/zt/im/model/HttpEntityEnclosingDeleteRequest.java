package com.mt.zt.im.model;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public final class HttpEntityEnclosingDeleteRequest extends HttpEntityEnclosingRequestBase {
	 
    public HttpEntityEnclosingDeleteRequest(final URI uri) {
        super();
        setURI(uri);
    }
 
    @Override
    public String getMethod() {
        return "DELETE";
    }
}
