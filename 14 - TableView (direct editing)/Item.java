public class Item
{
    private String name;
    private Double price;
    private Integer quantity;
    
    public Item(String n, Double p, Integer q)
    {
        name = n;
        price = p;
        quantity = q;
    }
    
    public void setName(String n)
    {
        name = n;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setPrice(Double p)
    {
        price = p;
    }
    
    public Double getPrice()
    {
        return price;
    }
    
    public void setQuantity(Integer q)
    {
        quantity = q;
    }
    
    public Integer getQuantity()
    {
        return quantity;
    }
    
    public String toString()
    {
        return name + " , " + price + " , " + quantity;
    }
  
}