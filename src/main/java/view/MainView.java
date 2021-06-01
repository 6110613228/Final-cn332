package view;

public class MainView {

    int width = 40;
    public String line = "========================================";
    public String welcome = "======= Welcome to hotel.com ===========";
    public String command = "> ";
    
    public MainView() {

    }

    public void welcome(String username) {

        System.out.printf("= Welcome, " + "%s", username + " =");
        for (int i = 0; i < (width - (username.length() + 13)); i++) {
            System.out.print("=");
        }
    }

    public void chooseLogin() {
        System.out.println("Login with ?");
        System.out.println("1. Our account");
        System.out.println("2. Google");
        System.out.println("3. Facebook");
    }
}
