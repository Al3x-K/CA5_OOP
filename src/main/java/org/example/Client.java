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
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in)))
        {
            System.out.println("The client is running and has connected to the server.");

            while(true)
            {
                //Display menu:
                System.out.println("1. Display Product by ID");
                System.out.println("2. Display Vendor by ID");
                System.out.println("3. Display Vendors selling chosen Product");
                System.out.println("4. Display Products sold by chosen Vendor");
                System.out.println();
                System.out.print("Choose an option: ");

                String option = console.readLine();

                //Send user's option to the server
                out.println(option);

                if(option.equals("1"))
                {
                    System.out.print("Enter ID: ");
                    String id = console.readLine();
                    //Send ID to the server
                    out.println(id);
                    String pString = in.readLine();
                    System.out.println("Response from the server: " + pString);
                }
                else if(option.equals("2"))
                {
                    System.out.print("Enter ID: ");
                    String id = console.readLine();
                    out.println(id);
                    String vString = in.readLine();
                    System.out.println("Response from the server: " + vString);
                }
                else if(option.equals("3"))
                {
                    System.out.print("Enter ID: ");
                    String id = console.readLine();
                    out.println(id);
                    String vPString = in.readLine();
                    System.out.println("Response from the server: " + vPString);
                }
                else if(option.equals("4"))
                {
                    System.out.print("Enter ID: ");
                    String id = console.readLine();
                    out.println(id);
                    String pVString = in.readLine();
                    System.out.println("Response from the server: " + pVString);
                }
                else
                {
                    System.out.println("Invalid request or error in response");
                    break;
                }

                String jsonResponse = in.readLine();
                if(jsonResponse != null)
                {
                    System.out.println("Received JSON data: ");
                    System.out.println(jsonResponse);
                }

                System.out.println();
            }

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
