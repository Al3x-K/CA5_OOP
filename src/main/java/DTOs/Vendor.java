package DTOs;

public class Vendor
{
    private int id;
    private String name;

    public Vendor(int vendorId, String name)
    {
        this.id = vendorId;
        this.name = name;
    }

    public Vendor(String name)
    {
        this.id = 0;
        this.name = name;
    }

    public Vendor()
    {

    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Vendor {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                '}';
    }
}
