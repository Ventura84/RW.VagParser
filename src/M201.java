import java.util.ArrayList;
import java.util.List;

public class M201 {

    private List<String> operationDataList = new ArrayList<>();

    public List<String> m201Parsing(String text, String nv, String nvOwner){
        //System.out.println(text);

        String[] textArray = text.split(":");
        String[] textArrayItem = textArray[1].split(" ");

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

        String mess = "201";
        String operation = "ПРИБ  ";
        if(
           (textArrayItem[3].equals("7562") && textArrayItem[5].equals("7561")) ||
           (textArrayItem[3].equals("7569") && textArrayItem[5].equals("7568")) ||
           (textArrayItem[3].equals("8770") && textArrayItem[5].equals("8769")) ||
           (textArrayItem[3].equals("5485") && textArrayItem[5].equals("7545")) ||
           (textArrayItem[3].equals("8773") && textArrayItem[5].equals("8772"))
        ){ operation = "ПРМГ  "; }

        operationDataList = VagonHandlerThread.dataToList(nv, nvOwner, mess, operation, "");

        return operationDataList;


    }

}

