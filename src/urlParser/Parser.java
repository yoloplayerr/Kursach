package urlParser;

import db.DbManager;

import java.io.*;

//0 1 2 4 5 6 7
public class Parser {

    public boolean parser(String userName,File file) {
        BufferedReader br = null;
        FileReader fileReader=null;
        try {
           fileReader= new FileReader(file);
            br = new BufferedReader(fileReader);
            DbManager.insertData(br, userName);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
