import java.util.ArrayList;
import java.util.List;

public class M209 {

    private List<String> operationDataList = new ArrayList<>();

    public List<String> m209Parsing(String text, String nv, String nvOwner, Fields fields){

        String[] textArray = text.split(":");
        String[] textArrayItem = textArray[1].split(" ");

        fields.setStForm(textArrayItem[1]);
        fields.setPNum(textArrayItem[2]);

        if(textArrayItem.length <= 12 ){

            fields.setPInd(textArrayItem[3] + " " + textArrayItem[4] + " " + textArrayItem[11]);
            fields.setPIndInt(textArrayItem[3] + " " + Integer.parseInt(textArrayItem[4]) + " " + textArrayItem[11]);
            fields.setPInd2Zeros(textArrayItem[3] + " " + String.format("%02d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[11]);
            fields.setPInd3Zeros(textArrayItem[3] + " " + String.format("%03d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[11]);


        } else if (textArrayItem.length == 13){

            fields.setPInd(textArrayItem[5] + " " + textArrayItem[11] + " " + textArrayItem[12]);
            fields.setPIndInt(textArrayItem[5] + " " + Integer.parseInt(textArrayItem[11]) + " " + textArrayItem[12]);
            fields.setPInd2Zeros(textArrayItem[5] + " " + String.format("%02d", Integer.parseInt(textArrayItem[11])) + " " + textArrayItem[12]);
            fields.setPInd3Zeros(textArrayItem[5] + " " + String.format("%03d", Integer.parseInt(textArrayItem[11])) + " " + textArrayItem[12]);


        } else if (textArrayItem.length == 14){

            fields.setPInd(textArrayItem[11] + " " + textArrayItem[12] + " " + textArrayItem[13]);
            fields.setPIndInt(textArrayItem[11] + " " + Integer.parseInt(textArrayItem[12]) + " " + textArrayItem[13]);
            fields.setPInd2Zeros(textArrayItem[11] + " " + String.format("%02d", Integer.parseInt(textArrayItem[12])) + " " + textArrayItem[13]);
            fields.setPInd3Zeros(textArrayItem[11] + " " + String.format("%03d", Integer.parseInt(textArrayItem[12])) + " " + textArrayItem[13]);
        }

        fields.setOpDate(textArrayItem[7] + "." + textArrayItem[8]);
        fields.setOpTime(textArrayItem[9] + ":" + textArrayItem[10]);

        String mess = "209";
        String operation = "КОРР  ";

        operationDataList = fields.dataToList(nv, nvOwner, mess, operation, "");
        return operationDataList;


    }

}