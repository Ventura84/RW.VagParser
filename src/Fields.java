import java.util.ArrayList;
import java.util.List;

public class Fields {

    private String nvStatus;
    private String stForm;
    private String stDest;
    private String pNum;
    private String pInd;
    private String pIndInt;
    private String pInd2Zeros;
    private String pInd3Zeros;
    private String cargo;
    //private String cargoDest;
    private String cargoWeight;
    //private String cargoRecipient;
    private String opDate;
    private String opTime;


    public List<String> dataToList(String nv, String nvOwner, String mess, String operation, String elem){

        List<String> operationDataList = new ArrayList<>();

        operationDataList.add(nv);
        operationDataList.add(nvOwner);
        operationDataList.add(mess);
        operationDataList.add(stForm);
        operationDataList.add(operation);
        operationDataList.add(opDate);
        operationDataList.add(opTime);
        operationDataList.add(nvStatus);
        operationDataList.add(stDest);
        operationDataList.add(cargoWeight);
        operationDataList.add(cargo);
        operationDataList.add(pIndInt);
        operationDataList.add(pNum);
        if(elem.length() > 0){
            operationDataList.add(":" + elem);
        } else {
            operationDataList.add(elem);
        }


        return operationDataList;
    }





    public Fields() {
        this.nvStatus = "";
        this.stForm = "";
        this.stDest = "";
        this.pNum = "";
        this.pInd = "---- x ----";
        this.pIndInt = "---- x ----";
        this.pInd2Zeros = "---- xx ----";
        this.pInd3Zeros = "---- xxx ----";
        this.cargo = "";
        //this.cargoDest = "";
        this.cargoWeight = "";
        //this.cargoRecipient = "";
        this.opDate = "";
        this.opTime = "";
    }


    public String getNvStatus() {
        return nvStatus;
    }

    public void setNvStatus(String nvStatus) {
        this.nvStatus = nvStatus;
    }

    public String getStForm() {
        return stForm;
    }

    public void setStForm(String stForm) {
        this.stForm = stForm;
    }

    public String getStDest() {
        return stDest;
    }

    public void setStDest(String stDest) {
        this.stDest = stDest;
    }

    public String getPNum() {
        return pNum;
    }

    public void setPNum(String pNum) {
        this.pNum = pNum;
    }

    public String getPInd() {
        return pInd;
    }

    public void setPInd(String pInd) {
        this.pInd = pInd;
    }

    public String getPIndInt() {
        return pIndInt;
    }

    public void setPIndInt(String pIndInt) {
        this.pIndInt = pIndInt;
    }

    public String getPInd2Zeros() {
        return pInd2Zeros;
    }

    public void setPInd2Zeros(String pInd2Zeros) {
        this.pInd2Zeros = pInd2Zeros;
    }

    public String getPInd3Zeros() {
        return pInd3Zeros;
    }

    public void setPInd3Zeros(String pInd3Zeros) {
        this.pInd3Zeros = pInd3Zeros;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

//    public String getCargoDest() {
//        return cargoDest;
//    }
//
//    public void setCargoDest(String cargoDest) {
//        this.cargoDest = cargoDest;
//    }

    public String getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(String cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

//    public String getCargoRecipient() {
//        return cargoRecipient;
//    }
//
//    public void setCargoRecipient(String cargoRecipient) {
//        this.cargoRecipient = cargoRecipient;
//    }

    public String getOpDate() {
        return opDate;
    }

    public void setOpDate(String opDate) {
        this.opDate = opDate;
    }

    public String getOpTime() {
        return opTime;
    }

    public void setOpTime(String opTime) {
        this.opTime = opTime;
    }
}
