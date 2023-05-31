package users;

/**
 * User object
 * @author Juan Loaiza
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String cellphone;
    private String rol;
    private String pass;

    public User(int id, String name, String email, String cellphone, String rol, String pass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.rol = rol;
        this.pass = pass;
    }

    public User GetUser() {
        return this;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setCellphone(String cellphone){
        this.cellphone = cellphone;
    }

    public void setRol(String rol){
        this.rol = rol;
    }

}
