package writeDB;


import dto.PeriodDTO;

import java.util.ArrayList;
import java.util.List;

public class fileActs {
    public void writeToDB(long processId, String creatorName, String sourceFileName, String targetFileName, String action, String status, String log){

    }

    public String getActPerformed(long processId, String targetFileName){

        return targetFileName;
    }

    public String getStatusOfAct(long processId, String sourceFileName){

        return sourceFileName;
    }
    public String getPathOfFile(long processId, String targetFileName){

        return targetFileName;
    }

    public List<String> getFilesCreatedInPeriod(PeriodDTO searchPeriod){

        return new ArrayList<>();
    }

}
