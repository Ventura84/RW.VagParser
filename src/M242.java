import java.util.ArrayList;
import java.util.List;

public class M242 {

    private List<String> operationDataList = new ArrayList<>();

    public List<String> m242Parsing(String text, String nv, String nvOwner, Fields fields) {

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

                String mess = "242";
                String operation = "ВЫГР  ";
                fields.setNvStatus("ПОР ");
                fields.setCargoWeight("0");
                fields.setCargo("");

                operationDataList = fields.dataToList(nv, nvOwner, mess, operation, elem);
                return operationDataList;
            }
        }

        return null;

    }

}