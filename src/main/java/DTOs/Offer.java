package DTOs;

public class Offer
{
    /**
     * Main author: Samuel Sukovský
     *
     */
    private int productId;
    private int vendorId;
    private String name;
    private int price;
    private int quantity;

    public Offer(int productId,  int vendorId, String name, int price, int quantity)
    {
        this.productId = productId;
        this.vendorId = vendorId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Offer(int productId,  int vendorId, int price, int quantity)
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

    public String getName()
    {
        return name;
    }

    public int getPrice()
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

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int purchase(int quantity)
    {
        this.quantity -= quantity;
        return this.price * quantity;
    }

    @Override
    public String toString()
    {
        return "Offer {" + "productId = " + productId + ", vendorId = " + vendorId + ", name = '" + name + ", price = " + price + ", quantity = " + quantity + '\'' + '}';
    }
}