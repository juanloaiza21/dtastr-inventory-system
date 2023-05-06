package users;

/**
 * User object
 * @author Juan Loaiza
 */

public class User extends Users {
    
    String password;
    Boolean logged;
    Boolean typeUser;
    int userId;
    String email;



    public User() {
        super();
        logged = false;
    }

    public Boolean getLogged() {
        return logged;
    }

    public Boolean getTypeUser() {
        return typeUser;
    }

    public String getEmail(){
        return email;
    }

    public void login(String email, String password) throws SQLException {
        this.password = password;
        this.email = email;
        conector.connect();
        String hasedPass = conector.getOne("pass", email, "email");
        passwordCrypt();
        logged = this.password.equals(hasedPass);
        if (conector.getOne("rol", email, "email").equals("seller") ) {
            typeUser = true;
        } else {
            typeUser = false;
        }
    }

    public String getByEmail(String email) throws SQLException {
        conector.connect();
        return conector.getOne("id", email, "email");
    }


    private void updateFieldscheck()throws IllegalStateException{
        if(updateFields == null) throw new IllegalStateException("You must set the fields you want to update");
        for (int i = 0; i < updateFields.length; i++) {
            updateFields[i]= updateFields[i].toLowerCase();
        }
        for (int i = 0; i < updateFields.length; i++) {
            if ((!updateFields[i].equals(fields.NOMBRE.toString())) && (!updateFields[i].equals(fields.EMAIL.toString())) && (!updateFields[i].equals(fields.CELLPHONE.toString())) && (!updateFields[i].equals(fields.PASS.toString()))) {
                throw new IllegalStateException("The update fields must be 'nombre', 'email', 'cellphone', 'rol' or 'pass'");
            }
        }

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
