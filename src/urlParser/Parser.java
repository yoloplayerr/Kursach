package urlParser;

import db.DbManager;

import java.io.*;

//0 1 2 4 5 6 7
public class Parser {
    public Parser(String userName) {
        parser(userName);
    }

    private void parser(String userName) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/Users/maxim/Desktop/text.csv"));
            DbManager dbManager = new DbManager();
            dbManager.insertData(br, userName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
