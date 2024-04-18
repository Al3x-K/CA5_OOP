package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class Client {
    /**
     * Main author: Aleksandra Kail
     */
    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }

    public void start()
    {
        try(Socket socket = new Socket("localhost", 8888);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {
            System.out.println("Client message: The Client is running and has connected to the server");

            Scanner console = new Scanner(System.in);

            System.out.println("1. Display Product by ID");
            System.out.println("2. Display Vendor by ID");
            System.out.println("3. Display Vendors selling chosen Product");
            System.out.println("4. Display Products sold by chosen Vendor");
            System.out.println("5. Display all Products");
            System.out.println("6. Display all Vendors");
            System.out.println("7. Display all Offers");
            System.out.println("8. Add Product to an order");
            System.out.println("9. DeleteProduct from the order");
            System.out.println("10.Get Images List");
            System.out.println("11. Exit");
            System.out.println();
            System.out.println("Please enter a command: ");

            String userRequest = console.nextLine();

            switch (userRequest)
            {
                case "1":
                {
                    System.out.print("Enter ID: ");
                    String id = console.next();
                    //Send ID to the server
                    out.println(id);
                    String pString = in.readLine();
                    System.out.println("Response from the server: " + pString);
                    break;
                }
                case "2":
                {
                    System.out.print("Enter ID: ");
                    String id = console.next();
                    out.println(id);
                    String vString = in.readLine();
                    System.out.println("Response from the server: " + vString);
                    break;
                }
                case "3":
                {
                    System.out.print("Enter ID: ");
                    String id = console.next();
                    out.println(id);
                    String vPString = in.readLine();
                    System.out.println("Response from the server: " + vPString);
                    break;
                }
                case "4":
                {
                    System.out.print("Enter ID: ");
                    String id = console.next();
                    out.println(id);
                    String pVString = in.readLine();
                    System.out.println("Response from the server: " + pVString);
                    break;
                }
                case "5":

                    break;
                case "6":

                    break;
                case "7":

                    break;
                case "8":

                    break;
                case "9":

                    break;
                case "10":

                    break;
                case "11":

                    break;
                default:
                    System.out.println("Invalid request or error in response");
                    break;
            }

            String jsonResponse = in.readLine();
            if(jsonResponse != null)
            {
                System.out.println("Received JSON data: ");
                System.out.println(jsonResponse);
            }

            console = new Scanner(System.in);

            System.out.println("1. Display Product by ID");
            System.out.println("2. Display Vendor by ID");
            System.out.println("3. Display Vendors selling chosen Product");
            System.out.println("4. Display Products sold by chosen Vendor");
            System.out.println("5. Display all Products");
            System.out.println("6. Display all Vendors");
            System.out.println("7. Display all Offers");
            System.out.println("8. Add Product to an order");
            System.out.println("9. DeleteProduct from the order");
            System.out.println("10.Get Images List");
            System.out.println("11. Exit");
            System.out.println();
            System.out.println("Please enter a command: ");

            userRequest = console.nextLine();
        }
        catch (IOException e)
        {
            System.out.println("Client message: IOException: " + e);
        }
        System.out.println("Exiting client, but server may still be running.");
    }
}