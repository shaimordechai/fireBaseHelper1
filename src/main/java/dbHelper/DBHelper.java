package dbHelper;


import dto.PeriodDTO;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.service.Firebase;

import java.io.UnsupportedEncodingException;
import java.util.*;


public class DBHelper {
    private static final String DB_ROOT = "https://filesacts-default-rtdb.firebaseio.com/";
    private static final String USERS_TABLE = "users";


    public static void main(String[]args){
        try {
            writeToDB(0, null, null,null, null, null, null);
        } catch (FirebaseException e) {
            e.printStackTrace();
        }
    }


    public static void writeToDB(long processId, String creatorName, String sourceFileName, String targetFileName, String action, String status, String log) throws FirebaseException {
        Firebase firebase = new Firebase(DB_ROOT);
        try {

            String usersTable = firebase.get(USERS_TABLE + "/shai").getRawBody();
            Map<String, Object> user1 = new HashMap<>();
            List list = Arrays.asList(1,2,3);
            user1.put("shai", list);
            Map<String, Object> user2 = new HashMap<>();
            user2.put("mordechai", 1L);
            Map<String, Object> user3 = new HashMap<>();
            user2.put("toto", 1L);
            firebase.patch(USERS_TABLE, user1);
            firebase.patch(USERS_TABLE, user2);
            firebase.patch(USERS_TABLE, user3);

           /* Map<String, Object> dataMap = new HashMap<>();
            Map<String, Long> user1 = new HashMap<>();
            user1.put("shai", 1L);
            Map<String, Long> user2 = new HashMap<>();
            user1.put("mordechai", 2L);
            dataMap.put("users", user1);
            firebase.patch(String.valueOf(user1));
            firebase.patch(String.valueOf(user2));*/

            /*firebase. patch("2");
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("shai", "1");
            String json = gson.toJson(dataMap);
            firebase.patch(json);
            dataMap.put("shai", "2");
            json = gson.toJson(dataMap);
            firebase.put(json);
            firebase.patch("shai", "3");
            dataMap.put("mordechai", "1");
            json = gson.toJson(dataMap);
            firebase.put(json);*/
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
