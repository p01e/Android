package tops.com.barberapp.model;

/**
 * Created by Admin on 4/6/2018.
 */

public class Client {
        private int id;
    private String name;
    private String mobile;
    private String emai;

    public Client(){

    }

    public Client(int id, String name, String mobile, String emai) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.emai = emai;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }
}
