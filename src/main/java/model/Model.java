package model;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import model.Model;
import java.util.List;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * UserModel
 * 
 * Provide all Model's sub classes a connection with DB.
 * 
 * Template
 */
public abstract class Model {

    public final String spreadsheetId = "1DsQ-bpTeJu_PMT9nR5Eo23uN1vEx2OFuVUylkY7XBLs";

    private DB db = new DB();
    public Sheets connection;

    public Model() {
        try {
            this.connection = db.getInstance();
        } catch(Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        
    }
}