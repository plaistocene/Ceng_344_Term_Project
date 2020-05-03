package server;

import java.net.InetAddress;;

public class ClientInfo {
	private InetAddress address;
	private int port;
	private String name;
	private int ID;

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
