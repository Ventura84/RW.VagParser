import java.util.ArrayList;
import java.util.List;

public class M220 {

    private List<String> operationDataList = new ArrayList<>();

    public List<String> m220Parsing(String text, String nv, String nvOwner, Fields fields) {

        String[] textArray = text.split(":");
        String[] textArrayItem = textArray[1].split(" ");


        fields.setStForm(textArrayItem[2]);
        fields.setPNum(textArrayItem[3]);
        fields.setPInd(textArrayItem[4] + " " + textArrayItem[5] + " " + textArrayItem[6]);
        fields.setPIndInt(textArrayItem[4] + " " + Integer.parseInt(textArrayItem[5]) + " " + textArrayItem[6]);
        fields.setPInd2Zeros(textArrayItem[4] + " " + String.format("%02d", Integer.parseInt(textArrayItem[5])) + " " + textArrayItem[6]);
        fields.setPInd3Zeros(textArrayItem[4] + " " + String.format("%03d", Integer.parseInt(textArrayItem[5])) + " " + textArrayItem[6]);
        fields.setOpDate(textArrayItem[8] + "." + textArrayItem[9]);
        fields.setOpTime(textArrayItem[10] + ":" + textArrayItem[11]);

        String mess = "220";
        String operation = "ПРМ-СД";
        if(
           textArrayItem[3].equals("7300") ||
           textArrayItem[3].equals("6646") ||
           textArrayItem[3].equals("6633") ||
           textArrayItem[3].equals("6639") ||
           textArrayItem[3].equals("7372") ||
           textArrayItem[3].equals("7351")
        ){operation = "ПРМГ  ";}

        if(
           textArrayItem[3].substring(0, 2).equals("74") ||
           textArrayItem[3].substring(0, 2).equals("75")
        ){ operation = "СДЧГ  ";}




        operationDataList = fields.dataToList(nv, nvOwner, mess, operation, "");
        return operationDataList;



    }

}