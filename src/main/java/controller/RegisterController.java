package controller;

import model.UserModel;

/**
 * RegisterController
 */
public class RegisterController implements Controller {

    private UserModel model = new UserModel();

    String name;
    String surname;
    String telephone;
    String password;

    public void set(String name, String surname, String telephone, String password) {
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.password = password;
    }

    @Override
    public Boolean execute() {

        return model.addUser(name, surname, telephone, password);
    }

    @Override
    public Boolean execute(Object a, Object b) {
        // TODO Auto-generated method stub
        return null;
    } 
}