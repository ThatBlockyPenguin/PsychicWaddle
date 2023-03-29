package com.blockypenguin.telnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Telnet {
	public static void main(String[] args) {
		String url = "localhost";
		int port = 8080;

		try(Socket pingSocket = new Socket(url, port)) { // Open new socket
			try(
				PrintWriter out = new PrintWriter(pingSocket.getOutputStream(), true); // Create PrintWriter (output) for socket
				BufferedReader in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream())) // Create BufferedReader (input) for socket
			) {
				out.println("ping"); // Send "ping" over socket
				System.out.println("Telnet Success: " + in.readLine());
			}
		}catch(IOException e) { // If socket IO creation failed
			System.out.println("Telnet Fail: " + e.getMessage());
		}
	}
}
