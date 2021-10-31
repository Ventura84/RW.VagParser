import java.util.ArrayList;
import java.util.List;

public class M203 {

    private List<String> operationDataList = new ArrayList<>();

    public List<String> m203Parsing(String text, String nv, String nvOwner, Fields fields){

        String[] textArray = text.split(":");
        String[] textArrayItem = textArray[1].split(" ");

        if(textArrayItem[1].substring(0, 2).equals("1+")){
            fields.setStForm(textArrayItem[1].substring(2));
        } else {
            fields.setStForm(textArrayItem[1]);
        }
        fields.setPNum(textArrayItem[2]);
        fields.setPInd(textArrayItem[3] + " " + textArrayItem[4] + " " + textArrayItem[5]);
        fields.setPIndInt(textArrayItem[3] + " " + Integer.parseInt(textArrayItem[4]) + " " + textArrayItem[5]);
        fields.setPInd2Zeros(textArrayItem[3] + " " + String.format("%02d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[5]);
        fields.setPInd3Zeros(textArrayItem[3] + " " + String.format("%03d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[5]);
        fields.setOpDate(textArrayItem[7] + "." + textArrayItem[8]);
        fields.setOpTime(textArrayItem[9] + ":" + textArrayItem[10]);

        String mess = "203";
        String operation = "РАСФ  ";


        operationDataList = fields.dataToList(nv, nvOwner, mess, operation, "");

        fields.setPNum("");
        fields.setPInd("---- x ----");
        fields.setPIndInt("---- x ----");
        fields.setPInd2Zeros("---- xx ----");
        fields.setPInd3Zeros("---- xxx ----");

        return operationDataList;

    }

}