
package org.example;

import DTOs.*;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client
{
    private static DataInputStream dataInputStream = null;
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
                dataInputStream = new DataInputStream(socket.getInputStream());

                System.out.println("Client message: The Client is running and has connected to the server");

            Scanner console = new Scanner(System.in);
            boolean running = true;
            String userRequest;
            String response;
            JsonConverter jsonConverter = new JsonConverter();
            Order order = new Order();



            while (running)
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
                System.out.println("10. Place order");
                System.out.println("11. Cancel order");
                System.out.println("12. Get Images List");
                System.out.println("13. Exit");
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
                    {
                        response = in.readLine();
                        List<Product> productList = jsonConverter.convertJsonStringToList(response, Product.class);
                        System.out.println("\nResponse from the server:");
                        displayList(productList);
                        break;
                    }
                    case "6":
                    {
                        response = in.readLine();
                        List<Vendor> vendorList = jsonConverter.convertJsonStringToList(response, Vendor.class);
                        System.out.println("\nResponse from the server:");
                        displayList(vendorList);
                        break;
                    }
                    case "7":
                    {
                        response = in.readLine();
                        List<Offer> offerList = jsonConverter.convertJsonStringToList(response, Offer.class);
                        System.out.println("\nResponse from the server:");
                        displayList(offerList);
                        break;
                    }
                    case "8":
                    {
                        System.out.print("Enter Product ID: ");
                        String id = console.next();
                        //Send Product ID to the server
                        out.println(id);
                        System.out.print("Enter Vendor ID: ");
                        id = console.next();
                        //Send Vendor ID to the server
                        out.println(id);
                        response = in.readLine();
                        Offer offer = jsonConverter.ConvertJsonStringToObject(response, Offer.class);
                        if (offer != null)
                        {
                            System.out.print("Enter quantity: ");
                            int quantity = console.nextInt();
                            if (quantity <= offer.getQuantity())
                            {
                                order.addItem(offer, quantity);
                                order.printItems();
                            }
                            else
                            {
                                System.out.println("Invalid quantity");
                            }
                        }
                        System.out.println("\nResponse from the server: \n" + offer + "\n");
                        break;
                    }
                    case "9":
                    {
                        System.out.print("Enter Item Index: ");
                        int itemIndex = console.nextInt();
                        try
                        {
                            order.removeItem(itemIndex);
                            System.out.println("Remove item on index " + itemIndex + " from the order\n");
                        }
                        catch (Exception e)
                        {
                            System.out.println("Cannot remove item on index " + itemIndex + ":\n" + e + "\n");
                        }
                        break;
                    }
                    case "10":
                    {
                        String jsonString = jsonConverter.ConvertObjectToJsonString(order);
                        out.println(jsonString);
                        response = in.readLine();
                        System.out.println("\nResponse from the server: \n" + response + "\n");
                        if (response.equals("Order placed successfully"))
                        {
                            order.clearItems();
                        }
                        break;
                    }
                    case "11":
                    {
                        System.out.print("Enter ID: ");
                        String id = console.next();
                        //Send ID to the server
                        out.println(id);
                        response = in.readLine();
                        System.out.println("\nResponse from the server: \n" + response + "\n");
                        break;
                    }
                    case "12":
                    {
                        response = in.readLine();
                        List<String> imageList = parseImageList(response);
                        System.out.println("Available images: ");
                        for (int i = 0; i < imageList.size(); i++)
                        {
                            System.out.println((i + 1) + ". " + imageList.get(i));
                        }
                        System.out.println("Select an image to download (enter number)");
                        int imgIndex = Integer.parseInt(console.nextLine()) - 1;
                        String selectedImage = imageList.get(imgIndex);

                        out.println("Get image:" + selectedImage);
                        receiveFile(selectedImage);
                        break;
                    }
                    case "13":
                        running = false;
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
        catch (Exception e)
        {
            throw new RuntimeException(e);
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
        return List.of(images);
    }

    private static void receiveFile(String fileName) throws Exception
    {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long bytes_remaining = dataInputStream.readLong();
        System.out.println("Server: file size in bytes = " + bytes_remaining);

        byte[] buffer = new byte[4 * 1024];

        System.out.println("Server:  Bytes remaining to be read from socket: ");
        int bytes_read;

        while (bytes_remaining > 0 &&  (bytes_read =
                dataInputStream.read(buffer, 0,(int)Math.min(buffer.length, bytes_remaining))) != -1)
        {
            fileOutputStream.write(buffer, 0, bytes_read);

            bytes_remaining = bytes_remaining - bytes_read;

            System.out.print(bytes_remaining + ", ");
        }

        System.out.println("File is Received");
        fileOutputStream.close();
    }
}
