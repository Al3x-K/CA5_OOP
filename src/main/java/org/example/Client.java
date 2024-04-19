
package org.example;

import DTOs.*;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client
{
    /**
     * Main author: Aleksandra Kail
     * Modified by: Samuel Sukovský
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
            String userRequest = "";
            String response;
            JsonConverter jsonConverter = new JsonConverter();
           

            while (userRequest != "11")
            {
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
                System.out.print("Please enter a command: ");

                userRequest = console.nextLine();
                out.println(userRequest);

                switch (userRequest)
                {
                    case "1":
                    {
                        System.out.print("Enter ID: ");
                        String id = console.next();
                        //Send ID to the server
                        out.println(id);
                        response = in.readLine();
                        Product product = jsonConverter.ConvertJsonStringToObject(response, Product.class);
                        System.out.println("\nResponse from the server: \n" + product + "\n");
                        break;
                    }
                    case "2":
                    {
                        System.out.print("Enter ID: ");
                        String id = console.next();
                        out.println(id);
                        response = in.readLine();
                        Vendor vendor = jsonConverter.ConvertJsonStringToObject(response, Vendor.class);
                        System.out.println("\nResponse from the server: \n" + vendor + "\n");
                        break;
                    }
                    case "3":
                    {
                        System.out.print("Enter ID: ");
                        String id = console.next();
                        out.println(id);
                        response = in.readLine();
                        List<Vendor> vendorList = jsonConverter.convertJsonStringToList(response, Vendor.class);
                        System.out.println("\nResponse from the server:");
                        displayList(vendorList);
                        break;
                    }
                    case "4":
                    {
                        System.out.print("Enter ID: ");
                        String id = console.next();
                        out.println(id);
                        response = in.readLine();
                        List<Product> productList = jsonConverter.convertJsonStringToList(response, Product.class);
                        System.out.println("\nResponse from the server:");
                        displayList(productList);
                        break;
                    }
                    case "5":
                        response = in.readLine();
                        List<Product> productList = jsonConverter.convertJsonStringToList(response, Product.class);
                        System.out.println("\nResponse from the server:");
                        displayList(productList);
                        break;
                    case "6":
                        response = in.readLine();
                        List<Vendor> vendorList = jsonConverter.convertJsonStringToList(response, Vendor.class);
                        System.out.println("\nResponse from the server:");
                        displayList(vendorList);
                        break;
                    case "7":
                        response = in.readLine();
                        List<Offer> offerList = jsonConverter.convertJsonStringToList(response, Offer.class);
                        System.out.println("\nResponse from the server:");
                        displayList(offerList);
                        break;
                    case "8":

                        break;
                    case "9":

                        break;
                    case "10":
                        response = in.readLine();
                        List<String> imageList = parseImageList(response);
                        System.out.println("Available images: ");
                        for(int i = 0; i < imageList.size(); i++)
                        {
                            System.out.println((i + 1) + ". " + imageList.get(i));
                        }
                        System.out.println("Select an image to dpwnload (enter number)");
                        int imgIndex = Integer.parseInt(in.readLine()) - 1;
                        String selectedImage = imageList.get(imgIndex);

                        out.println("Get image: " + selectedImage);
                        saveImageFromServer(in,selectedImage);
                        break;
                    case "11":
                        break;
                    default:
                        System.out.println("Invalid request or error in response");
                        break;
                }

                console = new Scanner(System.in);
            }
        }
        catch (IOException e)
        {
            System.out.println("Client message: IOException: " + e);
        }
        System.out.println("Exiting client, but server may still be running.");
    }

    private <T> void displayList(List<T> list)
    {
        for (T object : list)
        {
            System.out.println(object);
        }
        System.out.println();
    }

    public static List<String> parseImageList(String imgListJson)
    {
        String[] images = imgListJson.substring(1,imgListJson.length() - 1).split(", ");
        List<String> imageList = List.of(images);
        return imageList;
    }

    public static void saveImageFromServer(BufferedReader in, String imageName) throws IOException {
        try (BufferedInputStream imageStream = new BufferedInputStream(in);
             BufferedOutputStream fileStream = new BufferedOutputStream(new FileOutputStream("downloaded_" + imageName))) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = imageStream.read(buffer)) != -1) {
                fileStream.write(buffer, 0, bytesRead);
            }

            System.out.println("Image downloaded and saved: " + "downloaded_" + imageName);
        }
    }
}
