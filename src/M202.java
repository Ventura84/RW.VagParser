import java.util.ArrayList;
import java.util.List;

public class M202 {

    private List<String> operationDataList = new ArrayList<>();

    public List<String> m202Parsing(String text, String nv, String nvOwner, Fields fields){

        String[] textArray = text.split(":");
        String[] textArrayItem = textArray[1].split(" ");

        fields.setStForm(textArrayItem[1]);
        fields.setPNum(textArrayItem[2]);
        fields.setPInd(textArrayItem[3] + " " + textArrayItem[4] + " " + textArrayItem[5]);
        fields.setPIndInt(textArrayItem[3] + " " + Integer.parseInt(textArrayItem[4]) + " " + textArrayItem[5]);
        fields.setPInd2Zeros(textArrayItem[3] + " " + String.format("%02d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[5]);
        fields.setPInd3Zeros(textArrayItem[3] + " " + String.format("%03d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[5]);
        fields.setOpDate(textArrayItem[7] + "." + textArrayItem[8]);
        fields.setOpTime(textArrayItem[9] + ":" + textArrayItem[10]);

        String mess = "202";
        String operation = "ПРОС  ";

        operationDataList = fields.dataToList(nv, nvOwner, mess, operation, "");
        return operationDataList;

    }

}