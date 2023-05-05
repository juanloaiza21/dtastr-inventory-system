package users;
import java.sql.SQLException;
import java.util.*;
import db.Conector;

/**
 * Users interfaces around CRUD
 * @Author
 * Juan Loaiza
 */
public abstract class UsersFunctions {
    
    String[] createFields;
    String[] updateFields;
    Conector conector;
    
    protected UsersFunctions() {
        createFields = new String[] {"id", "nombre", "email", "cellphone", "rol", "pass"};
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        String[] updateFields = null;
    }

    protected enum role{
        ROL_ONE{
            public String toString() {
                return "SELLER";
            }
        }, 
        ROL_TWO{
            public String toString() {
                return "USER";
            }
        }
    }

    protected enum fields{
        NOMBRE{
            public String toString() {
                return "nombre";
            }
        },
        EMAIL{
            public String toString() {
                return "email";
            }
        },
        CELLPHONE{
            public String toString() {
                return "cellphone";
            }
        },
        PASS{
            public String toString() {
                return "pass";
            }
        },
    }

    protected Boolean validatorId(String[] data) {
        try {
            Integer.parseInt(data[0]);
            return true;
        } catch (Exception e) {
            System.err.println("The id must be an integer, example: '1'");
            return false;
        }
    }
    
    /**
     * Validate if the role is valid
     * @param rol String
     * @return Boolean
     */
    protected Boolean roleValidator(String rol) {
        if (rol.equals(role.ROL_ONE.toString()) || rol.equals(role.ROL_TWO.toString())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Convert data to LinkedList
     * @param data String[]. The data you want to convert
     * @implNote The first element of the array must be an integer  
     */
    protected LinkedList<Object> userConverter (String[] data){
        LinkedList<Object> result = new LinkedList<Object>();
        for (int i = 0; i < data.length; i++) {
            if (i == 0) {
                result.add(Integer.parseInt(data[i]));
            } else {
                result.add(data[i]);
            }
        } 
        return result;
    }
    
    /**
     * Crypt the password
     * @param pass String
     * @return String crypted pass
     */
    protected void passwordCrypt() {
    }
    
    /**
     * Convert data to LinkedList and the insert it on db
     * @param data String[]. The data you want to insert.
     * @implNote The first element of the array must be an integer on string
     */
    public void createUser(String[] data){}

    /**
     * Convert data to LinkedList and the update it on db
     * @param data String[]. The data you want to insert.
     * @param fields String[]. The fields you want to update
     * @implNote The first element of the array must be an integer on string, last element must be the password
     */
    public void updateUser(String[] data, String[] fields) {}

    /**
     * Convert data to LinkedList and the update it on db
     * @param data String[]. The data you want to insert.
     * @implNote The first element of the array must be an integer on string, last element must be the password
     */
    public void updateUser(String[] data,  Boolean password) {}

    /**
     * Delete one user
     * @param id
     * @throws SQLException
     */
    public void deleteUser(int id) throws SQLException {
        conector.connect();
        conector.deleteOne(id);
    }

    /**
     * Get all users
     * @throws SQLException
     */
    public void getAllUsers() throws SQLException {
        conector.connect();
        conector.getAll();
    }

    /**
     * Get one user by id
     * @param id
     * @throws SQLException
     */
    public void getOneUser(int id) throws SQLException {
        conector.connect();
        conector.getOne(id);
    }
} 
