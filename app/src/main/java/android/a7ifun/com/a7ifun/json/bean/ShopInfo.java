package android.a7ifun.com.a7ifun.json.bean;

/**
 * Created by 7yan on 2017/1/8.
 */

public class ShopInfo
{
    private int id;
    private String name;
    private double price;
    private String imagePath;
    //生成构造，带参数
    public ShopInfo(int id, double price, String name, String imagePath) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.imagePath = imagePath;
    }
    //生成构造，不带参数
    public ShopInfo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "ShopInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
