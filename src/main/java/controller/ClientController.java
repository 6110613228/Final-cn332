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

    private ArrayList<String> user = new ArrayList<String>();
    
    private Boolean isAuth = false;

    private String username;
    private String password;
    private String email;
    private String command;

    public void run() {

        // Set role of user as admin for bussiness logic mockup
        user.add("admin");

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

                        context = new Context(auth);
                        // Login sequence
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
                } else if (command.equals("register")) {
                    System.out.print("Enter your name : ");
                    username = sc.nextLine();
                    System.out.print("Enter your surname : ");
                    String surname = sc.nextLine();
                    System.out.print("Enter your password : ");
                    password = sc.nextLine();
                    System.out.print("Enter your telephone number : ");
                    String telephone = sc.nextLine();
                    
                    // Strategy choose context
                    RegisterController register = new RegisterController();
                    register.set(username, surname, telephone, password);
                    context = new Context(register);
                    if(context.execute()) {
                        System.out.println("Register success..");
                    } else {
                        System.out.println("Register fail..");
                    }
                }
            } else { // If user is authenticated, continue the flow.

                System.out.println(view.line);
                view.welcomeUser(username);
                System.out.println(view.line);

                System.out.println("You are authed.");

                // Admin scope
                if (user.get(0).equals("admin")) {
                    view.adminCommand();
                    System.out.print(view.command);
                    command = sc.nextLine();
                    if (command.equals("Add room")) {
                        //
                    }
                    if (command.equals("Promotion")) {
                        // 
                    }
                }
            }

            if (command.equals("exit") || command.equals("q")) {
                System.out.println("Exiting site...");
                break;
            }

            // Clear command
            command = "";
            System.out.println();
        }
    }
}