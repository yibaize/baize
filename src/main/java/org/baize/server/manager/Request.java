package org.baize.server.manager;

public class Request {
	private short id;
	/**
	 * 数据
	 */
	private String[] data;

	public Request() {
	}

	public Request(String[] data) {

		this.data = data;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}
}
