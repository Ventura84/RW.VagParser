import java.util.ArrayList;
import java.util.List;

public class M209 {

    private List<String> operationDataList = new ArrayList<>();

    public List<String> m209Parsing(String text, String nv, String nvOwner){
        //System.out.println(text);

        String[] textArray = text.split(":");
        String[] textArrayItem = textArray[1].split(" ");

        VagonHandlerThread.stForm = textArrayItem[1];
        VagonHandlerThread.pNum = textArrayItem[2];
        if(textArrayItem.length <= 12 ){

            VagonHandlerThread.pInd = textArrayItem[3] + " " + textArrayItem[4] + " " + textArrayItem[11];
            VagonHandlerThread.pIndInt = textArrayItem[3] + " " + Integer.parseInt(textArrayItem[4]) + " " + textArrayItem[11];
            VagonHandlerThread.pInd2Zeros = textArrayItem[3] + " " + String.format("%02d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[11];
            VagonHandlerThread.pInd3Zeros = textArrayItem[3] + " " + String.format("%03d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[11];


        } else if (textArrayItem.length == 13){

            VagonHandlerThread.pInd = textArrayItem[5] + " " + textArrayItem[11] + " " + textArrayItem[12];
            VagonHandlerThread.pIndInt = textArrayItem[5] + " " + Integer.parseInt(textArrayItem[11]) + " " + textArrayItem[12];
            VagonHandlerThread.pInd2Zeros = textArrayItem[5] + " " + String.format("%02d", Integer.parseInt(textArrayItem[11])) + " " + textArrayItem[12];
            VagonHandlerThread.pInd3Zeros = textArrayItem[5] + " " + String.format("%03d", Integer.parseInt(textArrayItem[11])) + " " + textArrayItem[12];

        } else if (textArrayItem.length == 14){

            VagonHandlerThread.pInd = textArrayItem[11] + " " + textArrayItem[12] + " " + textArrayItem[13];
            VagonHandlerThread.pIndInt = textArrayItem[11] + " " + Integer.parseInt(textArrayItem[12]) + " " + textArrayItem[13];
            VagonHandlerThread.pInd2Zeros = textArrayItem[11] + " " + String.format("%02d", Integer.parseInt(textArrayItem[12])) + " " + textArrayItem[13];
            VagonHandlerThread.pInd3Zeros = textArrayItem[11] + " " + String.format("%03d", Integer.parseInt(textArrayItem[12])) + " " + textArrayItem[13];

        }

        VagonHandlerThread.opDate = textArrayItem[7] + "." + textArrayItem[8];
        VagonHandlerThread.opTime = textArrayItem[9] + ":" + textArrayItem[10];

        String mess = "209";
        String operation = "КОРР  ";

        operationDataList = VagonHandlerThread.dataToList(nv, nvOwner, mess, operation, "");

        return operationDataList;


    }

}