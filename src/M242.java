import java.util.ArrayList;
import java.util.List;

public class M242 {

    private List<String> operationDataList = new ArrayList<>();

    public List<String> m242Parsing(String text, String nv, String nvOwner) {
        //System.out.println(text);

        String[] textArray = text.split(":");
        String[] textArrayItem = textArray[1].split(" ");

        VagonHandlerThread.stForm = textArrayItem[1];
        VagonHandlerThread.pNum = "";
        VagonHandlerThread.pInd = "---- x ----";
        VagonHandlerThread.pIndInt = "---- x ----";
        VagonHandlerThread.pInd2Zeros = "---- xx ----";
        VagonHandlerThread.pInd3Zeros = "---- xxx ----";
        VagonHandlerThread.opDate = textArrayItem[3] + "." + textArrayItem[4];
        VagonHandlerThread.opTime = textArrayItem[5] + ":" + textArrayItem[6];

        for (String elem : textArray) {
            if(elem.contains(nv)) {

                String mess = "242";
                String operation = "ВЫГР  ";
                VagonHandlerThread.nvStatus = "ПОР ";
                VagonHandlerThread.cargoWeight = "0";
                VagonHandlerThread.cargo = "";

                operationDataList = VagonHandlerThread.dataToList(nv, nvOwner, mess, operation, elem);

            }
        }

        return operationDataList;

    }

}