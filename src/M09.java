import java.util.ArrayList;
import java.util.List;

public class M09 {


    private String operation;
    private List<String> operationDataList = new ArrayList<>();

    public List<String> m09Parsing(String text, String nv, String nvOwner, Fields fields){

        String[] textArray = text.split(":");
        String[] textArrayItem = textArray[1].split(" ");

//        fields.setStForm(textArrayItem[1]);
//        fields.setPNum(textArrayItem[2]);
//        fields.setPInd(textArrayItem[3] + " " + textArrayItem[4] + " " + textArrayItem[5]);
//        fields.setPIndInt(textArrayItem[3] + " " + Integer.parseInt(textArrayItem[4]) + " " + textArrayItem[5]);
//        fields.setPInd2Zeros(textArrayItem[3] + " " + String.format("%02d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[5]);
//        fields.setPInd3Zeros(textArrayItem[3] + " " + String.format("%03d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[5]);
//        fields.setOpDate(textArrayItem[7] + "." + textArrayItem[8]);
//        fields.setOpTime(textArrayItem[9] + ":" + textArrayItem[10]);





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

                fields.setStForm(textArrayItem[1]);
                fields.setPNum(textArrayItem[2]);
                fields.setPInd(textArrayItem[3] + " " + textArrayItem[4] + " " + textArrayItem[5]);
                fields.setPIndInt(textArrayItem[3] + " " + Integer.parseInt(textArrayItem[4]) + " " + textArrayItem[5]);
                fields.setPInd2Zeros(textArrayItem[3] + " " + String.format("%02d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[5]);
                fields.setPInd3Zeros(textArrayItem[3] + " " + String.format("%03d", Integer.parseInt(textArrayItem[4])) + " " + textArrayItem[5]);
                fields.setOpDate(textArrayItem[7] + "." + textArrayItem[8]);
                fields.setOpTime(textArrayItem[9] + ":" + textArrayItem[10]);

                //if (!elem.equals(textArray[2])) {

                    if(operation.equals("ОТЦ   ") || operation.equals("ПРИЦ  ") || operation.equals("КОРР  ")){
                        String[] elemSplited = elem.split(" ");

                        if (elemSplited.length > 2 ){   //// "0671" replacement
                            elem = elem.replace(elemSplited[2], "1");
                        }
                        if (elemSplited.length > 3 ){
                            if(Integer.parseInt(elemSplited[3]) > 0){
                                fields.setNvStatus("ГРУЖ");
                                fields.setCargoWeight(String.valueOf(Integer.parseInt(elemSplited[3])));
                            } else {
                                fields.setNvStatus("ПОР ");
                                fields.setCargoWeight("0");
                                fields.setCargo("");
                            }

                        }
                        if (elemSplited.length > 4 ){
                            fields.setStDest(elemSplited[4]);
                        }
                        if (elemSplited.length > 5 ){
                            fields.setCargo(elemSplited[5]);
                        }
                    }



                    operationDataList = fields.dataToList(nv, nvOwner, mess, operation, elem);

                    if (operation.equals("ОТЦ   ") || operation.equals("ОТЦ.ГР")) {

                        fields.setPNum("");
                        fields.setPInd("---- x ----");
                        fields.setPIndInt("---- x ----");
                        fields.setPInd2Zeros("---- xx ----");
                        fields.setPInd3Zeros("---- xxx ----");
                    }


                    return operationDataList;
                //}

            }
        }

        return null;




    }
}