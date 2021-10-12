package dbHelper;


import dto.PeriodDTO;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    public void writeToDB(long processId, String creatorName, String sourceFileName, String targetFileName, String action, String status, String log){

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
