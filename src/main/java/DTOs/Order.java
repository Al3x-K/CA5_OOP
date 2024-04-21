package DTOs;

import java.util.ArrayList;
import java.util.List;

public class Order
{
    private int orderId;
    private List<Offer> items;

    public Order(int orderId, List<Offer> items)
    {
        this.orderId = orderId;
        this.items = items;
    }

    public Order(int orderId)
    {
        this.orderId = orderId;
        this.items = new ArrayList<>();
    }

    public Order(List<Offer> items)
    {
        this.orderId = -1;
        this.items = items;
    }

    public Order()
    {
        this.orderId = -1;
        this.items = new ArrayList<>();
    }

    public int getOrderId()
    {
        return orderId;
    }

    public List<Offer> getItems()
    {
        return items;
    }

    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }

    public void setItems(List<Offer> items)
    {
        this.items = items;
    }

    public void addItem(Offer item)
    {
        this.items.add(item);
    }

    public void addItem(Offer item, int quantity)
    {
        item.setQuantity(quantity);
        this.items.add(item);
    }

    public void removeItem(int index)
    {
        this.items.remove(index);
    }

    public void clearItems()
    {
        this.items.clear();
    }

    public void printItems()
    {
        for (Offer o : this.items)
        {
            System.out.println(o);
        }
    }

    @Override
    public String toString()
    {
        String ret = "Order {" + "orderId = " + orderId + ", items = ";
        for (Offer o : this.items)
        {
            ret += o + ", ";
        }
        ret.substring(0, ret.length() - 2);
        return  ret + '}';
    }
}