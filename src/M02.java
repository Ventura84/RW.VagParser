import java.util.ArrayList;
import java.util.List;

public class M02 {

    private List<String> operationDataList = new ArrayList<>();

    public List<String> m02Parsing(String text, String nv, String nvOwner){
        //System.out.println(text);

        String[] textArray = text.split(":");
        String[] textArrayItem = textArray[1].split(" ");

        VagonHandlerThread.stForm = textArrayItem[1];
        VagonHandlerThread.pNum = textArrayItem[2];
        VagonHandlerThread.pInd = textArrayItem[3] + " " + textArrayItem[4] + " " + textArrayItem[5];
        VagonHandlerThread.pIndInt = textArrayItem[3] + " " + Integer.parseInt(textArrayItem[4]) + " " + textArrayItem[5];
        VagonHandlerThread.pInd2Zeros = textArrayItem[3] + " " + String.format("%02d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[5];
        VagonHandlerThread.pInd3Zeros = textArrayItem[3] + " " + String.format("%03d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[5];
        VagonHandlerThread.opDate = textArrayItem[7] + "." + textArrayItem[8];
        VagonHandlerThread.opTime = textArrayItem[9] + ":" + textArrayItem[10];


        for (String elem : textArray) {
            if(elem.contains(nv)){
                String mess = "02 ";
                String operation = "ФОРМ  ";
                String[] elemSplited = elem.split(" ");
                //System.out.println(Arrays.toString(elemSplited));
                if(elemSplited.length >= 3){ //// "0671" replacement
                    elem = elem.replace(" " + elemSplited[2], " 1");

                }

                if(elemSplited.length >= 5){
                    if (Integer.parseInt(elemSplited[3]) == 0){
                        VagonHandlerThread.nvStatus = "ПОР ";
                        VagonHandlerThread.stDest = elemSplited[4];
                        VagonHandlerThread.cargoWeight = "0";
                        VagonHandlerThread.cargoDest = "";
                        VagonHandlerThread.cargo = "";
                        VagonHandlerThread.cargoRecipient = "";

                    } else {
                        VagonHandlerThread.nvStatus = "ГРУЖ";
                        VagonHandlerThread.stDest = elemSplited[4];
                        VagonHandlerThread.cargoWeight = String.valueOf(Integer.parseInt(elemSplited[3]));
                        VagonHandlerThread.cargoDest = elemSplited[4];
                        VagonHandlerThread.cargo = elemSplited[5];
                        VagonHandlerThread.cargoRecipient = elemSplited[6];
                    }

                    if (elem.length() == 3) {
                        VagonHandlerThread.nvStatus = "ПОР ";
                        VagonHandlerThread.cargoWeight = "0";
                        VagonHandlerThread.cargo = "";
                    }


                    operationDataList = VagonHandlerThread.dataToList(nv, nvOwner, mess, operation, elem);

                } else {
                    if(elemSplited.length > 3){
                        if(Integer.parseInt(elemSplited[3]) == 0){
                            VagonHandlerThread.nvStatus = "ПОР ";
                            VagonHandlerThread.cargoWeight = "0";
                            VagonHandlerThread.cargo = "";
                        } else {
                            VagonHandlerThread.nvStatus = "ГРУЖ";
                            VagonHandlerThread.cargoWeight = String.valueOf(Integer.parseInt(elemSplited[3]));
                        }
                    //OLD VERSION
                    } else {
                        VagonHandlerThread.nvStatus = "ПОР ";
                        VagonHandlerThread.cargoWeight = "0";
                        VagonHandlerThread.cargo = "";
                    }
                    //OLD VERSION

                    operationDataList = VagonHandlerThread.dataToList(nv, nvOwner, mess, operation, elem);

                }

            }
        }

        return operationDataList;
    }



}