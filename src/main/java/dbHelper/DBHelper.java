package dbHelper;


import dto.PeriodDTO;
import dto.UserDTO;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import net.thegreshams.firebase4j.service.Firebase;

import java.io.UnsupportedEncodingException;
import java.util.*;


public class DBHelper {
    private static final String DB_ROOT = "https://filesacts-default-rtdb.firebaseio.com/";
    private static final String USERS_TABLE = "users";
    private static final String UACTS_TABLE = "acts";


    public static void main(String[]args){
        try {
            writeToDB(0, "mordechai", null,null, null, null, null);
        } catch (FirebaseException e) {
            e.printStackTrace();
        }
    }


    public static void writeToDB(long processId, String creatorName, String sourceFileName, String targetFileName, String action, String status, String log) throws FirebaseException {
        Firebase firebase = new Firebase(DB_ROOT);
        try {
            Map<String, Object> usersMap = new HashMap<>();
            for (int i = 0; i < 1000; i++) {
                UserDTO user = new UserDTO(i, Integer.valueOf(i).toString());
                usersMap.put(user.getId().toString(),  user.toMap());
            }
            firebase.put(USERS_TABLE ,  usersMap);

        } catch (UnsupportedEncodingException | JacksonUtilityException e) {
            e.printStackTrace();
        }
    }

    public String getActPerformed(long processId, String targetFileName){

        return targetFileName;
    }

    public String getStatusOfAct(long processId, String targetFileName){

        return targetFileName;
    }
    public String getPathOfFile(long processId, String targetFileName){

        return targetFileName;
    }

    public List<String> getFilesCreatedInPeriod(PeriodDTO searchPeriod){

        return new ArrayList<>();
    }

    public List<String> getFilesCreatedInPeriodAndUserName(PeriodDTO searchPeriod, String userName){

        return new ArrayList<>();
    }

    public List<String> getFilesCreatedInPeriodAndStatus(PeriodDTO searchPeriod, String status){

        return new ArrayList<>();
    }



}
