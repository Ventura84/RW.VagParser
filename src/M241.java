import java.util.ArrayList;
import java.util.List;

public class M241 {

    private List<String> operationDataList = new ArrayList<>();

    public List<String> m241Parsing(String text, String nv, String nvOwner, Fields fields) {

        String[] textArray = text.split(":");
        String[] textArrayItem = textArray[1].split(" ");

        fields.setStForm(textArrayItem[1]);
        fields.setPNum("");
        fields.setPInd("---- x ----");
        fields.setPIndInt("---- x ----");
        fields.setPInd2Zeros("---- xx ----");
        fields.setPInd3Zeros("---- xxx ----");
        fields.setOpDate(textArrayItem[3] + "." + textArrayItem[4]);
        fields.setOpTime(textArrayItem[5] + ":" + textArrayItem[6]);

        for (String elem : textArray) {
            if(elem.contains(nv)) {

                String mess = "241";
                String operation = "ПОГР  ";
                fields.setNvStatus("ГРУЖ");
                String[] elemSplited = elem.split(" ");

                fields.setStDest(elemSplited[4]);
                fields.setCargoWeight(String.valueOf(Integer.parseInt(elemSplited[3])/10));
                //VagonHandlerThread.cargoDest = elemSplited[4];
                fields.setCargo(elemSplited[5]);
                //VagonHandlerThread.cargoRecipient = elemSplited[6];


                operationDataList = fields.dataToList(nv, nvOwner, mess, operation, elem);
                return operationDataList;
            }
        }


        return null;

    }


}