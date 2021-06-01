package controller;

import model.UserModel;
import view.MainView;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * ClientController
 */
public class ClientController {

    private Context context;

    private AuthController auth = new AuthController();
    private GoogleController google = new GoogleController();

    private MainView view = new MainView();

    private Scanner sc = new Scanner(System.in);

    private ArrayList<Object> user;
    private Boolean isAuth = false;

    private String username;
    private String password;
    private String email;
    private String command;

    public void run() {

        while (true) {
            System.out.println(view.line);
            System.out.println(view.welcome);
            System.out.println(view.line);

            // If user is not authenticated, Login.
            if (!isAuth) {

                System.out.println("Are you new to this site? If you want to register type \"register\"");
                System.out.println("If you want to Login type \"Login\".");

                System.out.print(view.command);
                command = sc.nextLine();

                if (command.equals("Login") || command.equals("login")) {

                    view.chooseLogin();

                    System.out.print(view.command);
                    command = sc.nextLine();

                    if (command.equals("1")) {
                        System.out.print("Enter your username : ");
                        username = sc.nextLine();
                        System.out.print("Enter your password : ");
                        password = sc.nextLine();

                        // Login sequence
                        context = new Context(auth);
                        if (context.execute(username, password)) {
                            System.out.println("Login success.");
                            isAuth = true;
                        } else {
                            System.out.println("Login fail.");
                        }
                    } else if (command.equals("2")) {
                        System.out.print("Enter your gmail :");
                        email = sc.nextLine();
                        System.out.print("Enter your google's password : ");
                        password = sc.nextLine();

                        // Google login sequence
                        context = new Context(google);
                        if (context.execute(email, password)) {
                            System.out.println("Login success.");
                            isAuth = true;
                            username = google.getUsername(email, password);
                        } else {
                            System.out.println("Login fail.");
                        }
                    }
                }
            } else { // If user is authenticated, continue the flow.

                System.out.println(view.line);
                view.welcome(username);
                System.out.println(view.line);

                System.out.println("You are authed.");
                System.out.print(view.command);
                command = sc.nextLine();

            }

            if (command.equals("exit") || command.equals("q")) {
                System.out.println("Exiting site...");
                break;
            }
            System.out.println();
        }
    }
}