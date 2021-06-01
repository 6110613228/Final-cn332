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

    public int getLastId() {
        List<List<Object>> query;
        ArrayList<Integer> result = new ArrayList<Integer>();
        try {
            ValueRange data = connection.spreadsheets().values().get(spreadsheetId, "Users!A2:A").execute();
            query = data.getValues();
            for (List row : query) {
                result.add(Integer.parseInt((String)row.get(0)));
            }
            return Collections.max(result);
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
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

    public Boolean addUser(String name, String surname, String telephone, String password) {

        Object data[] = {Integer.toString(getLastId()+1), name, surname, telephone, password, 0, 0, "user"};

        List<List<Object>> values = Arrays.asList(Arrays.asList(data));

        ValueRange body = new ValueRange().setValues(values);
        try {
            String range = String.format("users!A%s:G%s", getLastId()+2, getLastId()+2);
            UpdateValuesResponse result = connection.spreadsheets().values().update(spreadsheetId, range, body)
                                            .setValueInputOption("USER_ENTERED")
                                            .execute();
            return true;
        } catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
