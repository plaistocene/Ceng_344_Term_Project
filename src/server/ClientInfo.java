package server;

import java.net.InetAddress;

public class ClientInfo {
	private String name;
	private int ID;
	private InetAddress address;
	private int port;

	public ClientInfo(String name, int ID, InetAddress address, int port) {
		this.address = address;
		this.port = port;
		this.name = name;
		this.ID = ID;
	}

	public InetAddress getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return ID;
	}
}
