package model;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.IOException;

import java.util.List;

public class GoogleAPI extends Model {

    // Constructor
    public GoogleAPI() {
        super();
    }

    // Get users
    public List<List<Object>> getUsers() {

        List<List<Object>> query = null;
        try {
            ValueRange data = connection.spreadsheets().values().get(spreadsheetId, "google!A2:D").execute();
            query = data.getValues();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
        return query;
    }

    /**
     * 
     * @return list of that user with email, passqword null if there is no user
     */
    public List getUser(String email, String password) {

        List<List<Object>> users = getUsers();
        for (List row : users) {
            if (row.get(0).equals(email) && row.get(2).equals(password)) {
                return row;
            }
        }
        return null;
    }

    public Boolean isExist(String email, String password) {

        List user = null;
        List<List<Object>> users = getUsers();
        for (List row : users) {
            if (row.get(0).equals(email) && row.get(2).equals(password)) {
                user = row;
                break;
            }
        }
        return user == null ? false : true;
    }

    public String getUsername(String email, String password) {

        List<List<Object>> users = getUsers();
        for (List row : users) {
            if (row.get(0).equals(email) && row.get(2).equals(password)) {
                return (String) row.get(1);
            }
        }
        return null;
    }
}
