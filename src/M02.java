import java.util.ArrayList;
import java.util.List;

public class M02 {

    private List<String> operationDataList = new ArrayList<>();


    public List<String> m02Parsing(String text, String nv, String nvOwner, Fields fields){

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


        for (String elem : textArray) {
            if(elem.contains(nv)){
                String mess = "02 ";
                String operation = "ФОРМ  ";
                String[] elemSplited = elem.split(" ");

                if(elemSplited.length >= 3){ //// "0671" replacement
                    elem = elem.replace(elemSplited[2], "1");

                }

                if(elemSplited.length >= 5){
                    if (Integer.parseInt(elemSplited[3]) == 0){
                        fields.setNvStatus("ПОР ");
                        fields.setStDest(elemSplited[4]);
                        fields.setCargoWeight("0");
                        //fields.setCargoDest("");
                        fields.setCargo("");
                        //fields.setCargoRecipient("");

                    } else {
                        fields.setNvStatus("ГРУЖ");
                        fields.setStDest(elemSplited[4]);
                        fields.setCargoWeight(String.valueOf(Integer.parseInt(elemSplited[3])));
                        //fields.setCargoDest(elemSplited[4]);
                        fields.setCargo(elemSplited[5]);
                        //fields.setCargoRecipient(elemSplited[6]);

                    }

                    if (elem.length() == 3) {
                        fields.setNvStatus("ПОР ");
                        fields.setCargoWeight("0");
                        //fields.setCargoDest("");
                        fields.setCargo("");
                        //fields.setCargoRecipient("");
                    }


                    operationDataList = fields.dataToList(nv, nvOwner, mess, operation, elem);
                    return operationDataList;

                } else {
                    if(elemSplited.length > 3){
                        if(Integer.parseInt(elemSplited[3]) == 0){
                            fields.setNvStatus("ПОР ");
                            fields.setCargoWeight("0");
                            //fields.setCargoDest("");
                            fields.setCargo("");
                            //fields.setCargoRecipient("");
                        } else {
                            fields.setNvStatus("ГРУЖ");
                            fields.setCargoWeight(String.valueOf(Integer.parseInt(elemSplited[3])));
                        }
                    //OLD VERSION
                    } else {
                        fields.setNvStatus("ПОР ");
                        fields.setCargoWeight("0");
                        //fields.setCargoDest("");
                        fields.setCargo("");
                        //fields.setCargoRecipient("");
                    }
                    //OLD VERSION

                    operationDataList = fields.dataToList(nv, nvOwner, mess, operation, elem);
                    return operationDataList;

                }

            }
        }

        return null;
    }



}