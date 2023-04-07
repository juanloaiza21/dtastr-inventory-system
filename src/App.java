import java.util.*;
import users.Main;

public class App {
    
    public static void main(String[] args){
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        /*
         * User must be loged in to use the service
         */
        while (!main.getLogged()) {
            System.out.println("Welcome to inventory system 0.1 (Alpha)");
            System.out.println("1. Login");
            System.out.println("2. Sign up");
            try {
                int selector = sc.nextInt();
                switch (selector) {
                    case 1:
                        main.login();
                        if (!main.getLogged()){
                            System.out.println("Incorrect email or password");
                            Thread.sleep(1000);
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                        }
                        else {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Welcome to " + main.getTypeUser() + "Module");
                        }
                    break;
                    case 2:
                        main.createUser();
                        Thread.sleep(1000);
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                    break;
                    default:
                        System.out.println("Incorrect option: " + sc.nextInt());
                    break;
                }
            } catch (InputMismatchException | InterruptedException e){
                System.err.println("The option must be a int");
                sc.nextLine();
            } 
        }
        
        if (main.getTypeUser()) {
            //TODO seller menu
        } else{
            //TODO user menu
        }
    }
}
