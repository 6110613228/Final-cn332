package controller;

import model.GoogleAPI;

public class GoogleController implements Controller {

    // Mockup GoogleAPI
    GoogleAPI google = new GoogleAPI();

    /**
     * 
     * @return false if user, doesn't exist
     */
    public Boolean isExist(String username, String password) {
        return google.isExist(username, password);
    }

    public String getUsername(String email, String password) {
        return google.getUsername(email, password);
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub

    }

    @Override
    public Boolean execute(Object a, Object b) {
        return isExist((String) a, (String) b);
    }
}
