package model;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.IOException;


public class UserModel extends Model {
    
    // Constructor
    public UserModel() {
        super();
    }

    // Get users
    public List<List<Object>> getUsers() {

        List<List<Object>> query = null;
        try {
            ValueRange data = connection.spreadsheets().values().get(spreadsheetId, "users!A2:G").execute();
            query = data.getValues();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
        return query;
    }

    /**
     * 
     * @return list of that user with username, passqword null if there is no user 
     */
    public List getUser(String username, String password) {

        List<List<Object>> users = getUsers();
        for (List row : users) {
            if (row.get(1).equals(username) && row.get(3).equals(password)) {
                return row;
            }
        }
        return null;
    }

    public Boolean isExist(String username, String password) {

        List user = null;
        List<List<Object>> users = getUsers();
        for (List row : users) {
            if (row.get(1).equals(username) && row.get(4).equals(password)) {
                user = row;
                break;
            }
        }
        return user == null ? false : true;
    }
}
