package org.baize.server.manager;

public class Request {
	/**
	 * 数据
	 */
	private String[] data;

	public Request() {
	}

	public Request(String[] data) {

		this.data = data;
	}

	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}
}
