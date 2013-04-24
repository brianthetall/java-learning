package com.brianthetall.mongo;

public class Cloud {
    final String scheme;
    final String host;
    final int port;

    public static final Cloud ml = new Cloud("https","api.mongolab.com",443);

    public Cloud(String scheme, String host, int port) {
        this.scheme = scheme;
        this.host = host;
        this.port = port;
    }

    public String getScheme() {
        return scheme;
    }

    public String getHost() {
	return host;
    }

    public int getPort() {
        return port;
    }
}
