import java.util.ArrayList;
import java.util.List;

public class M203 {

    private List<String> operationDataList = new ArrayList<>();

    public List<String> m203Parsing(String text, String nv, String nvOwner){
        //System.out.println(text);

        String[] textArray = text.split(":");
        String[] textArrayItem = textArray[1].split(" ");
        //System.out.println("TEXT_ARRAY : " + Arrays.toString(TEXT_ARRAY));
        //System.out.println("TEXT_ARRAY_ITEM : " + Arrays.toString(TEXT_ARRAY_ITEM));

        if(textArrayItem[1].substring(0, 2).equals("1+")){
            VagonHandlerThread.stForm = textArrayItem[1].substring(2);
        } else {
            VagonHandlerThread.stForm = textArrayItem[1];

        }
        VagonHandlerThread.pNum = textArrayItem[2];
        VagonHandlerThread.pInd = textArrayItem[3] + " " + textArrayItem[4] + " " + textArrayItem[5];
        VagonHandlerThread.pIndInt = textArrayItem[3] + " " + Integer.parseInt(textArrayItem[4]) + " " + textArrayItem[5];
        VagonHandlerThread.pInd2Zeros = textArrayItem[3] + " " + String.format("%02d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[5];
        VagonHandlerThread.pInd3Zeros = textArrayItem[3] + " " + String.format("%03d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[5];
        VagonHandlerThread.opDate = textArrayItem[7] + "." + textArrayItem[8];
        VagonHandlerThread.opTime = textArrayItem[9] + ":" + textArrayItem[10];

        String mess = "203";
        String operation = "РАСФ  ";


        operationDataList = VagonHandlerThread.dataToList(nv, nvOwner, mess, operation, "");

        VagonHandlerThread.pNum = "";
        VagonHandlerThread.pInd = "---- x ----";
        VagonHandlerThread.pIndInt = "---- x ----";
        VagonHandlerThread.pInd2Zeros = "---- xx ----";
        VagonHandlerThread.pInd3Zeros = "---- xxx ----";


        //System.out.println();


        return operationDataList;

    }

}