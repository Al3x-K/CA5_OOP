package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Exceptions.DaoException;
import DAOs.*;

public class Server {
    /**
     * Main author: Aleksandra Kail
     */
    final int PORT = 8888; //port number to listen for

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start()
    {

        ServerSocket serverSocket =null;
        Socket clientSocket =null;

        try
        {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server has started.");
            int clientNumber = 0;  // a number sequentially allocated to each new client (for identification purposes here)

            while (true)
            {
                System.out.println("Server: Listening/waiting for connections on port ..." + PORT);
                clientSocket = serverSocket.accept();
                clientNumber++;
                System.out.println("Server: Listening for connections on port ..." + PORT);

                System.out.println("Server: Client " + clientNumber + " has connected.");
                System.out.println("Server: Port number of remote client: " + clientSocket.getPort());
                System.out.println("Server: Port number of the socket used to talk with client " + clientSocket.getLocalPort());

                // create a new ClientHandler for the requesting client, passing in the socket and client number,
                // pass the handler into a new thread, and start the handler running in the thread.
                Thread t = new Thread(new ClientHandler(clientSocket, clientNumber));
                t.start();

                System.out.println("Server: ClientHandler started in thread " + t.getName() + " for client " + clientNumber + ". ");

            }
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            try
            {
                if(clientSocket!=null)
                    clientSocket.close();
            }
            catch (IOException e)
            {
                System.out.println(e);
            }
            try
            {
                if(serverSocket!=null)
                    serverSocket.close();
            }
            catch (IOException e)
            {
                System.out.println(e);
            }

        }
        System.out.println("Server: Server exiting, Goodbye!");
    }
}

class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
{
    BufferedReader socketReader;
    PrintWriter socketWriter;
    Socket clientSocket;
    final int clientNumber;

    // Constructor
    public ClientHandler(Socket clientSocket, int clientNumber)
    {
        this.clientSocket = clientSocket;  // store socket for closing later
        this.clientNumber = clientNumber;  // ID number that we are assigning to this client
        try
        {
            // assign to fields
            this.socketWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            this.socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void run ()
    {
        ProductDaoInterface IProductDao = new MySqlProductDao();
        VendorDaoInterface IVendorDao = new MySqlVendorDao();
        ProductsVendorsDaoInterface IProductsVendorsDao = new MySqlProductsVendorsDao();
        JsonConverter jsonConverter = new JsonConverter();

        int id;
        String jsonString;
        String request;
        try
        {
            while ((request = socketReader.readLine()) != null)
            {
                System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + request);
                switch (request)
                {
                    case "1":
                        id = Integer.parseInt(socketReader.readLine());
                        System.out.println(id);
                        socketWriter.println("Display Product by ID: ");
                        jsonString = jsonConverter.ConvertProductToJsonString(IProductDao.getProductById(id));
                        socketWriter.println(jsonString);
                        break;
                    case "2":
                        id = Integer.parseInt(socketReader.readLine());
                        System.out.println(id);
                        socketWriter.println("Display Vendor by ID: ");
                        jsonString = jsonConverter.ConvertProductToJsonString(IVendorDao.getVendorById(id));
                        socketWriter.println(jsonString);
                        break;
                    case "3":
                        id = Integer.parseInt(socketReader.readLine());
                        System.out.println(id);
                        socketWriter.println("Display Vendors selling chosen Product: ");
                        jsonString = jsonConverter.convertVendorListToJsonString(IProductsVendorsDao.getVendorsSellingProductId(id));
                        socketWriter.println(jsonString);
                        break;
                    case "4":
                        id = Integer.parseInt(socketReader.readLine());
                        System.out.println(id);
                        socketWriter.println("Display Products sold by chosen Vendor: ");
                        jsonString = jsonConverter.ConvertProductToJsonString(IProductsVendorsDao.getProductsSoldByVendorId(id));
                        socketWriter.println(jsonString);
                        break;
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
                        socketWriter.println("Invalid option");
                        break;
                    }
                }
            }
            catch (IOException | DaoException ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                this.socketWriter.close();
                try
                {
                    this.socketReader.close();
                    this.clientSocket.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
    }
}
