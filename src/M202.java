import java.util.ArrayList;
import java.util.List;

public class M202 {

    private List<String> operationDataList = new ArrayList<>();

    public List<String> m202Parsing(String text, String nv, String nvOwner){
        //System.out.println(text);

        String[] textArray = text.split(":");
        String[] textArrayItem = textArray[1].split(" ");

        VagonHandlerThread.stForm = textArrayItem[1];
        VagonHandlerThread.pNum = textArrayItem[2];
        VagonHandlerThread.pInd = textArrayItem[3] + " " + textArrayItem[4] + " " + textArrayItem[5];
        VagonHandlerThread.pIndInt = textArrayItem[3] + " " + Integer.parseInt(textArrayItem[4]) + " " + textArrayItem[5];
        VagonHandlerThread.pInd2Zeros = textArrayItem[3] + " " + String.format("%02d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[5];
        VagonHandlerThread.pInd3Zeros = textArrayItem[3] + " " + String.format("%03d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[5];
        VagonHandlerThread.opDate = textArrayItem[7] + "." + textArrayItem[8];
        VagonHandlerThread.opTime = textArrayItem[9] + ":" + textArrayItem[10];

        String mess = "202";
        String operation = "ПРОС  ";

        operationDataList = VagonHandlerThread.dataToList(nv, nvOwner, mess, operation, "");

        return operationDataList;

    }

}