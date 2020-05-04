package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Server {
	private static DatagramSocket socket;
	private static boolean running;

	private static int clientID;
	private static ArrayList<ClientInfo> clients = new ArrayList<>();

	public static void startEngine(int port) {
		try {
			socket = new DatagramSocket(port);

			running = true;
			listen();
			System.out.println("Server Started On The Port, " + port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void hollyBroadcast(String message) {
		for(ClientInfo info : clients){
			send(message, info.getAddress(), info.getPort());
		}
	}

	private static void send(String message, InetAddress address, int port) {
		try {
			message += "\\e";
			byte[] data = message.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			socket.send(packet);
			System.out.println("Sent Message To:.. " + address.getHostAddress() + ":" + port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void listen() {
		Thread listenThread = new Thread("ChatProgram Listener") {
			public void run() {
				try {
					while (running) {
						byte[] data = new byte[1024];
						DatagramPacket packet = new DatagramPacket(data, data.length);
						socket.receive(packet);

						String message = new String(data);
						message = message.substring(0, message.indexOf("\\e"));

						//FIXME: Manage Message
						if (!isCommand(message, packet)) {
							hollyBroadcast(message);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		listenThread.start();
	}

	/*
	 * SERVER COMMAND LIST
	 * \con:[name] -> Connects Client to Server
	 * \dis:[id]   -> Disconnect Client from Server
	 */
	private static boolean isCommand(String message, DatagramPacket packet) {
		if(message.startsWith("\\con:")){
			//Run Connection Code
			String name = message.substring(message.indexOf(":") + 1);
			clients.add(new ClientInfo(name, clientID++, packet.getAddress(), packet.getPort()));
			hollyBroadcast("User: " + name + " Connected!");

			return true;
		}
//		else if (message.startsWith("\\dis:")){
//			String name = message.substring(message.indexOf(":") + 1);
//			int discID = 0;
//			int idx = 0;
//			for(ClientInfo disc : clients){
//				if (packet.getAddress().equals(disc.getAddress())){
//					discID = disc.getID();
//					break;
//				}
//				idx++;
//			}
//			clients.remove(idx);
//			hollyBroadcast("User: " + name + " Disconnected!");
//
//			return true;
//		}
		return false;
	}

	public static void stopEngine() {
		running = false;
	}
}












































