package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    /**
     * Main author: Aleksandra Kail
     *
     */
    public static void main(String[] args)
    {
        final int PORT = 8888; //port number to listen for

        try (ServerSocket serverSocket = new ServerSocket(PORT))
        {
            System.out.println("Server started. Waiting for the client to connect...");

            //Accept client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket);

            //Open streams for communication with the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            //Read client's request
            String request = in.readLine();
            System.out.println("Received request from client: " + request);

            //Process request (echo back to client)
            out.println("Server received your request: " + request);

            //Close streams and sockets
            in.close();
            out.close();
            clientSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
