package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
public class Client
{
    /**
     * Main author: Aleksandra Kail
     *
     */
    public static void main(String[] args)
    {
        final String SERVER_ADDRESS  = "localhost";
        final int SERVER_PORT = 8888;

        try(Socket socket = new Socket(SERVER_ADDRESS,SERVER_PORT);
            OutputStream outputStream = socket.getOutputStream();)
        {
            PrintWriter out = new PrintWriter(outputStream,true);
            System.out.println("The client is running and has connected to the server.");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e)
        {
            System.out.println(e);  // print out the exception
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
}
