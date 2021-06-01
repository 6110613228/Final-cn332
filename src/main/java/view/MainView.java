package view;

public class MainView {

    int width = 40;
    public String line = "========================================";
    public String welcome = "======= Welcome to hotel.com ===========";
    public String command = "> ";
    
    public MainView() {

    }

    public void welcomeUser(String username) {
        System.out.printf("= Welcome, " + "%s", username + " =");
    }

    public void chooseLogin() {
        System.out.println("Login with ?");
        System.out.println("1. Our account");
        System.out.println("2. Google");
    }

    public void option() {
        System.out.println("What do you want to do?");
        System.out.println("1. ");
    }

    public void adminCommand() {
        System.out.println("Admin's command lists");
        System.out.println("\"users\"   see all users");
        System.out.println("\"promotion\"   create promotion");
    }

    public void userCommand(){
        System.out.println("User's command lists");
        System.out.println("\"reserve room\"   see all users");
        System.out.println("\"payment method\"   create promotion");
    }
}
