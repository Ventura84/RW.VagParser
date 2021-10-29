import java.util.ArrayList;
import java.util.List;

public class M220 {

    private List<String> operationDataList = new ArrayList<>();

    public List<String> m220Parsing(String text, String nv, String nvOwner) {
        //System.out.println(text);

        String[] textArray = text.split(":");
        String[] textArrayItem = textArray[1].split(" ");

        VagonHandlerThread.stForm = textArrayItem[2];
        VagonHandlerThread.pNum = textArrayItem[3];
        VagonHandlerThread.pInd = textArrayItem[4] + " " + textArrayItem[5] + " " + textArrayItem[6];
        VagonHandlerThread.pIndInt = textArrayItem[4] + " " + Integer.parseInt(textArrayItem[5]) + " " + textArrayItem[6];
        VagonHandlerThread.pInd2Zeros = textArrayItem[4] + " " + String.format("%02d", Integer.parseInt(textArrayItem[5])) + " " + textArrayItem[6];
        VagonHandlerThread.pInd3Zeros = textArrayItem[4] + " " + String.format("%03d", Integer.parseInt(textArrayItem[5])) + " " + textArrayItem[6];
        VagonHandlerThread.opDate = textArrayItem[8] + "." + textArrayItem[9];
        VagonHandlerThread.opTime = textArrayItem[10] + ":" + textArrayItem[11];

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




        operationDataList = VagonHandlerThread.dataToList(nv, nvOwner, mess, operation, "");


        return operationDataList;



    }

}