package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import DTOs.Product;
import DTOs.Vendor;
import Exceptions.DaoException;
import DAOs.*;

public class Server
{
    /**
     * Main author: Aleksandra Kail
     *
     */
    public static void main(String[] args)
    {
        final int PORT = 8888; //port number to listen for

        ProductDaoInterface IProductDao = new MySqlProductDao();
        VendorDaoInterface IVendorDao = new MySqlVendorDao();
        ProductsVendorsDaoInterface IProductsVendorsDao = new MySqlProductsVendorsDao();
        JsonConverter jsonConverter = new JsonConverter();

        int id;
        String jsonString;

        try (ServerSocket serverSocket = new ServerSocket(PORT))
        {
            while (true)
            {
                try (   Socket clientSocket = serverSocket.accept();
                        // connection is made.
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                )
                {
                    System.out.println("Server started. A client has connected.");

                    //Read client's request
                    String option = in.readLine();
                    System.out.println("Received option from client: " + option);

                    switch (option)
                    {
                        case "1":
                            id = Integer.parseInt(in.readLine());
                            System.out.println(id);
                            out.println("Display Product by ID: ");
                            jsonString = jsonConverter.ConvertProductToJsonString(IProductDao.getProductById(id));
                            out.println(jsonString);
                            break;
                        case "2":
                            id = Integer.parseInt(in.readLine());
                            System.out.println(id);
                            out.println("Display Vendor by ID: ");
                            jsonString = jsonConverter.ConvertProductToJsonString(IVendorDao.getVendorById(id));
                            out.println(jsonString);
                            break;
                        case "3":
                            id = Integer.parseInt(in.readLine());
                            System.out.println(id);
                            out.println("Display Vendors selling chosen Product: ");
                            jsonString = jsonConverter.convertVendorListToJsonString(IProductsVendorsDao.getVendorsSellingProductId(id));
                            out.println(jsonString);
                            break;
                        case "4":
                            id = Integer.parseInt(in.readLine());
                            System.out.println(id);
                            out.println("Display Products sold by chosen Vendor: ");
                            jsonString = jsonConverter.ConvertProductToJsonString(IProductsVendorsDao.getProductsSoldByVendorId(id));
                            out.println(jsonString);
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
                            out.println("Invalid option");
                            break;
                    }
                    out.flush();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (DaoException e)
        {
            throw new RuntimeException(e);
        }
    }
}
