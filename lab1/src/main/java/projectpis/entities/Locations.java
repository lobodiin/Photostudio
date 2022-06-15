package projectpis.entities;

public class Locations {
    private long id;
    private String title;
    private long price;

    public Locations(){

    }

    public Locations(String title, long price) {
        this.title = title;
        this.price = price;
    }

    public Locations(long id, String title, long price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }


    public void printLocations(){
        System.out.println(this.getId()+"  "+this.getTitle()+"  "+this.getPrice()+" UAH " );

    }
}
