package com.blockypenguin.telnet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Telnet {
	private static final String CHARSET = "UTF-8";
	
	public static void main(String[] args) {
		int port = 23;

		try(ServerSocket serverSocket = new ServerSocket(port)) {
			Socket connectionSocket = serverSocket.accept();

			//Create IO for the connection
			InputStream input = connectionSocket.getInputStream();
			OutputStream output = connectionSocket.getOutputStream();

			try(Scanner scanner = new Scanner(input, CHARSET)) {
				System.out.println("Telnet Success!");
				
				PrintWriter out = new PrintWriter(new OutputStreamWriter(output, CHARSET), true);
				
				// Output to the server
				out.println("Hello World! Enter Peace to exit.");

				// Echo back input from the client
				boolean done = false;

				while(!done && scanner.hasNextLine()) {
					String line = scanner.nextLine();
					out.println("Echo from Server: " + line);

					if(line.toLowerCase().trim().equalsIgnoreCase("peace"))
						done = true;
				}
			}
		}catch(IOException e) {
			System.out.println("Telnet Fail: " + e.getMessage());
		}
	}
}
