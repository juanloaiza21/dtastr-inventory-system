package users;
import java.sql.SQLException;
import java.util.LinkedList;
/**
 * User class implementation  
 * @Author
 * Juan Loaiza
 */
public class User extends Users {
    
    String password;
    public User() {
        super();
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

    @Override
    protected void passwordCrypt () {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            password = (String) sb.toString();
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void createUser(String[] data) {
        try {
            if(!validatorId(data)) throw new IllegalStateException("The id must be an integer, example: '1'");
            if(!roleValidator(data[4])) throw new IllegalStateException("The role must be 'SELLER' or 'BUYER'");
            password = data[5];
            passwordCrypt();
            data[5] = password;
            LinkedList<Object> user = userConverter(data);
            conector.connect();
            conector.insert(user, createFields);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void updateUser(String[] data, String[] fields) {
        updateFields = fields;
        int id =0;
        try {
            if(!validatorId(data)) throw new IllegalStateException("The id must be an integer, example: '1'");
            updateFieldscheck();
            password = data[data.length-1];
            passwordCrypt();
            data[data.length-1] = password;
            LinkedList<Object> user = userConverter(data);
            conector.connect();
            id = (int)user.get(0);
            user.remove(0);
            conector.update(user, updateFields, id);
            conector.getOne(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        User user = new User();
        try {
            user.getAllUsers();
            /* 
            System.out.println("::::::::::::::::::::::::::::Creacion usuario ID 1::::::::::::::::::::::::::::::::::::");
            user.createUser(new String[]{"1","Juan", "juan400reyesloaiza@gmail.com", "123456789", "SELLER", "123456"});
            user.getOneUser(1);
            System.out.println("::::::::::::::::::::Creacion usuario ID 2 mismo correo:::::::::::::::::::::::::::::::");
            user.createUser(new String[]{"2","Juan", "juan400reyesloaiza@gmail.com", "123456789", "SELLER", "123456"});
            user.getOneUser(2);
            System.out.println("::::::::::::::::::::::::::::Creacion usuario ID 3 y update usuario 1::::::::::::::::::::::::::::::::::::");
            user.createUser(new String[]{"3","Juan", "joumanmaster_@outlook.es", "123456789", "SELLER", "123456"});
            user.updateUser(new String[]{"1","JuanUpdate", "joumanmaaster_@outlook.es", "123456789", "123456"} , new String[]{"nombre", "email", "cellphone", "pass"});
            user.getAllUsers();
            */
        } catch (SQLException e) {
            System.err.println(e.getMessage()); 
        }
    }
}
