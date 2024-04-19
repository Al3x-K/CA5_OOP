package DTOs;

public class Offer
{
    /**
     * Main author: Samuel Sukovský
     *
     */

    private int productId;
    private int vendorId;
    private String productName;
    private double price;
    private int quantity;

    public Offer(int productId,  int vendorId, String productName, double price, int quantity)
    {
        this.productId = productId;
        this.vendorId = vendorId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public Offer(int productId,  int vendorId, double price, int quantity)
    {
        this.productId = productId;
        this.vendorId = vendorId;
        this.price = price;
        this.quantity = quantity;
    }

    public Offer(int productId,  int vendorId)
    {
        this.productId = productId;
        this.vendorId = vendorId;
    }
    public Offer()
    {

    }

    public int getProductId()
    {
        return productId;
    }

    public int getVendorId()
    {
        return vendorId;
    }

    public String getProductName()
    {
        return productName;
    }

    public double getPrice()
    {
        return price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public void setVendorId(int vendorId)
    {
        this.vendorId = vendorId;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public double purchase(int quantity)
    {
        this.quantity -= quantity;
        return this.price * quantity;
    }

    @Override
    public String toString()
    {
        return "Offer {" + "productId = " + productId + ", vendorId = " + vendorId + ", name = '" + productName + ", price = " + price + ", quantity = " + quantity + '\'' + '}';
    }
}
