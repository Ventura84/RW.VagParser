import java.util.ArrayList;
import java.util.List;

public class M241 {

    private List<String> operationDataList = new ArrayList<>();

    public List<String> m241Parsing(String text, String nv, String nvOwner) {
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

                String mess = "241";
                String operation = "ПОГР  ";
                VagonHandlerThread.nvStatus = "ГРУЖ";
                String[] elemSplited = elem.split(" ");

                VagonHandlerThread.stDest = elemSplited[4];
                VagonHandlerThread.cargoWeight = String.valueOf(Integer.parseInt(elemSplited[3])/10);
                VagonHandlerThread.cargoDest = elemSplited[4];
                VagonHandlerThread.cargo = elemSplited[5];
                VagonHandlerThread.cargoRecipient = elemSplited[6];



                operationDataList = VagonHandlerThread.dataToList(nv, nvOwner, mess, operation, elem);

            }
        }


        return operationDataList;

    }


}