import java.util.ArrayList;
import java.util.List;

public class M09 {


    private String operation;
    private List<String> operationDataList = new ArrayList<>();


    public List<String> m09Parsing(String text, String nv, String nvOwner){
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
            if (elem.contains(nv)) {

                String mess = "09 ";

                switch (textArray[2].substring(0, 2)) {
                    case "11": {
                        operation = "ОТЦ   ";
                        break;
                    }
                    case "14": {
                        operation = "ПРИЦ  ";
                        break;
                    }
                    case "03":
                    case "04": {
                        operation = "КОРР  ";
                        break;
                    }
                    case "33":
                    case "83":
                    case "08":
                    case "01":
                    case "81": {
                        operation = "UNKNOW";
                        break;
                    }
                    case "02": {
                        operation = "ЗAM.ИH";
                        break;
                    }
                    case "91": {
                        //System.out.println(file + " " + text);
                        operation = "ОТЦ.ГР";
                        break;
                    }
                }


                //if (!elem.equals(textArray[2])) {

                    if(operation.equals("ОТЦ   ") || operation.equals("ПРИЦ  ") || operation.equals("КОРР  ")){ //// "0671" replacement
                        //System.out.println("ELEMENT FROM FILE : " + file + " - " + elem);
                        String[] elemSplited = elem.split(" ");
                        //System.out.println("ELEMENT SPLITED FROM FILE : " + " - " + Arrays.toString(elemSplited));
                        if (elemSplited.length > 2 ){
                            elem = elem.replace(" " + elemSplited[2], " 1");
                        }
                        if (elemSplited.length > 3 ){
                            if(Integer.parseInt(elemSplited[3]) > 0){
                                VagonHandlerThread.nvStatus = "ГРУЖ";
                                VagonHandlerThread.cargoWeight = String.valueOf(Integer.parseInt(elemSplited[3]));
                            } else {
                                VagonHandlerThread.nvStatus = "ПОР ";
                                VagonHandlerThread.cargoWeight = "0";
                                VagonHandlerThread.cargo = "";
                            }

                        }
                        if (elemSplited.length > 4 ){
                            VagonHandlerThread.stDest = elemSplited[4];
                        }
                        if (elemSplited.length > 5 ){
                            VagonHandlerThread.cargo = elemSplited[5];
                        }
                    }


                    operationDataList = VagonHandlerThread.dataToList(nv, nvOwner, mess, operation, elem);

                    if (operation.equals("ОТЦ   ") || operation.equals("ОТЦ.ГР")) {

                        VagonHandlerThread.pNum = "";
                        VagonHandlerThread.pInd = "---- x ----";
                        VagonHandlerThread.pIndInt = "---- x ----";
                        VagonHandlerThread.pInd2Zeros = "---- xx ----";
                        VagonHandlerThread.pInd3Zeros = "---- xxx ----";
                    }
                //}

            }
        }

        return operationDataList;




    }
}