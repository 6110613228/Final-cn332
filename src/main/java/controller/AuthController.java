package controller;

import model.UserModel;

/**
 * AuthController
 */
public class AuthController implements Controller {
    
    private UserModel user = new UserModel();

    /**
     * 
     * @return false if user, doesn't exist
     */
    public Boolean isExist(String username, String password) {
        return user.isExist(username, password);
    }

    @Override
    public Boolean execute() {
        
        return true;
    }
    
    @Override
    public Boolean execute(Object a, Object b) {
        return isExist((String) a, (String) b);
    }
}
