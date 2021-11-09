package dbHelper;


import dto.PeriodDTO;
import dto.UserDTO;
import enums.DBEnums;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import net.thegreshams.firebase4j.service.Firebase;
import org.apache.http.client.utils.DateUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DBHelper {

    public static final String STATUS = "status";
    public static final String INPUT_FILE_NAME = "inputFileName";
    private static final String DB_ROOT = "https://filesacts-default-rtdb.firebaseio.com/";
    public static final String DATE_PATTERN = "yyyyMMddhhmmss";
    public static final String ACT = "act";
    public static final String USER = "user";
    private static final String USERS_TABLE = "users";
    private static final String ACTS_TABLE = "acts";
    public static final String PROCESS = "process";
    public static final String LOG = "log";
    public static final String ORDER_BY = "orderBy";
    public static final String EQUAL_TO = "equalTo";
    public static final String TARGET_FILE_NAME = "targetFileName";
    public static final String CREATION_DATE1 = "\"creationDate\"";
    public static final String CREATION_DATE = "creationDate";


    public static void main(String[]args) throws FirebaseException {
        for (int i = 0; i < 10; i++) {
            writeToDB("user" + i, "process" + i, "input" + i,"terget"
                    + i, "act" + i, "status" + i, "log" + i);
        }
       /* String test1 = getActPerformed(String.valueOf(5), "targetFileName5");
        String test2 = getStatusOfAct(String.valueOf(5), "targetFileName5");
        List<String> test3 = getFilesCreatedInPeriod(new PeriodDTO(new Date(2020, 01, 01), new Date(2040, 01, 01)));
        int a = 5;*/

    }


    public static void writeToDB(String user, String process, String inputFileName, String targetFileName, String act, String status, String log) throws FirebaseException {
        Firebase firebase = new Firebase(DB_ROOT);
        String creationDate = DateUtils.formatDate(new Date(), DATE_PATTERN);
        try {
            String key = createKey(firebase, user, creationDate);
            Map<String, Object> actsMap = new HashMap<>();
            actsMap.put(USER, user);
            actsMap.put(CREATION_DATE, creationDate);
            actsMap.put(PROCESS, process);
            actsMap.put(INPUT_FILE_NAME, inputFileName);
            actsMap.put(TARGET_FILE_NAME, targetFileName);
            actsMap.put(ACT, act);
            actsMap.put(STATUS, status);
            actsMap.put(LOG, log);

            firebase.put(ACTS_TABLE + "/" + key,  actsMap);
          /* Map<String, Object> usersMap = new HashMap<>();
            for (int i = 0; i < 5; i++) {
                //UUID uniqueID = UUID.randomUUID();
                UserDTO userDTO = new UserDTO(String.valueOf(i+1), user + (i + 1));
                usersMap.put(userDTO.getName(), userDTO.getId().toString());
            }
            firebase.put(USERS_TABLE,  usersMap);*/
        } catch (JacksonUtilityException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String createKey(Firebase firebase, String user, String creationDate) throws FirebaseException, IOException {
        String userId = getUserId(firebase, user);
        return userId + creationDate;
    }

    private static String getUserId(Firebase firebase, String user) throws FirebaseException, IOException {
        FirebaseResponse users = firebase.get(USERS_TABLE);
        Map<String, Object> body = users.getBody();
        return (String)body.get(user).toString();
    }


    public static String getActPerformed(String processId, String targetFileName) throws FirebaseException {
        // https://filesacts-default-rtdb.firebaseio.com/acts.json?orderBy=%22process%22&equalTo=1&print=pretty
        String res = getByTargetFileName(PROCESS, processId, targetFileName, ACT);
        return res;

    }

    private static String getByTargetFileName(String keyOrderBy, String valueOrderBy, String targetFileName, String valueToGet) throws FirebaseException {
        Firebase firebase = new Firebase(DB_ROOT);
        firebase.addQuery(ORDER_BY, keyOrderBy);
        firebase.addQuery(EQUAL_TO, valueOrderBy);
        String act = null;
        try {
            FirebaseResponse actsOfProcess = firebase.get(ACTS_TABLE);

            Map<String, Object> body = actsOfProcess.getBody();

            act = body.entrySet().stream()
                    .map(u -> u.getValue())
                    .map(v -> (HashMap<String, String>) v)
                    .filter(m -> m.get(TARGET_FILE_NAME).equals(targetFileName))
                    .map(m -> m.get(valueToGet))
                    .findAny()
                    .orElse(null);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return act;
    }

    public static String getStatusOfAct(String processId, String targetFileName) throws FirebaseException {
        String res = getByTargetFileName(PROCESS, processId, targetFileName, STATUS);
        return res;
    }
    public String getPathOfFile(long processId, String targetFileName){

        return targetFileName;
    }

    public static List<String> getFilesCreatedInPeriod(PeriodDTO searchPeriod) throws FirebaseException {
        Firebase firebase = new Firebase(DB_ROOT);
        String from = DateUtils.formatDate(searchPeriod.getFrom(), DATE_PATTERN);
        String to = DateUtils.formatDate(searchPeriod.getTo(), DATE_PATTERN);
        firebase.addQuery(ORDER_BY, CREATION_DATE1);
        firebase.addQuery("startAt", from);
        firebase.addQuery("endAt", to);
        List<String> res = null;
        try {
            FirebaseResponse actsOfProcess = firebase.get(ACTS_TABLE);

            Map<String, Object> body = actsOfProcess.getBody();

            res = body.entrySet().stream()
                    .map(u -> u.getValue())
                    .map(v -> (HashMap<String, String>) v)
                    .map(m -> m.get("creationDate"))
                    .collect(Collectors.toList());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public List<String> getFilesCreatedInPeriodAndUserName(PeriodDTO searchPeriod, String userName){

        return new ArrayList<>();
    }

    public List<String> getFilesCreatedInPeriodAndStatus(PeriodDTO searchPeriod, String status){

        return new ArrayList<>();
    }



}
