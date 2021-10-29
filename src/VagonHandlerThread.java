import java.util.ArrayList;
import java.util.List;

class VagonHandlerThread extends Thread {

    //private Exchanger<List<List<String>>> exchanger;
    private String nv;
    private String nvOwner;
    private List<String> fileContents;

    public static String nvStatus = "";
    public static String stForm = "";
    public static String stDest = "";
    public static String pNum = "";
    public static String pInd = "---- x ----";
    public static String pIndInt = "---- x ----";
    public static String pInd2Zeros = "---- xx ----";
    public static String pInd3Zeros = "---- xxx ----";
    public static String cargo = "";
    public static String cargoDest = "";
    public static String cargoWeight = "0";
    public static String cargoRecipient = "";
    public static String opDate = "";
    public static String opTime = "";

    private static final String m02Header = "(:02 ";
    private static final String m09Header = "(:09 ";
    private static final String m200Header = "(:200";
    private static final String m201Header = "(:201";
    private static final String m202Header = "(:202";
    private static final String m203Header = "(:203";
    private static final String m209Header = "(:209";
    private static final String m220Header = "(:220";
    private static final String m241Header = "(:241";
    private static final String m242Header = "(:242";

    private List<List<String>> operations = new ArrayList<>();
    private List<List<String>> operationsForMain = new ArrayList<>();


    public static List<String> dataToList(String nv, String nvOwner, String mess, String operation, String elem){

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
        operationDataList.add(elem);


        return operationDataList;
    }



    public VagonHandlerThread(String vag, List<String> fileContents){
        //this.exchanger = exchanger;
        this.nv = vag.substring(0, 8);
        this.nvOwner = vag.substring(8).trim();
        this.fileContents = fileContents;
    }


    @Override
    public void run(){



        //System.out.println("START SEARCHING " + nv + "...");

        M02 m02 = new M02();
        M09 m09 = new M09();
        M200 m200 = new M200();
        M201 m201 = new M201();
        M202 m202 = new M202();
        M203 m203 = new M203();
        M209 m209 = new M209();
        M220 m220 = new M220();
        M241 m241 = new M241();
        M242 m242 = new M242();


        for (String content : fileContents) {


            if(content.substring(0, 2).equals("(:") &&
                    (content.contains(nv) ||
//                     content.contains(" " + pInd3 + " ") ||
//                     content.contains(" " + pInd2 + " ") ||
//                     content.contains(" " + pInd1 + " ") ||
                     content.contains(" " + pInd + " ") ||
                     content.contains(" " + pIndInt + " ") ||
                     content.contains(" " + pInd2Zeros + " ") ||
                     content.contains(" " + pInd3Zeros + " ") ||
//                     content.contains(" " + pInd3 + ":") ||
//                     content.contains(" " + pInd2 + ":") ||
//                     content.contains(" " + pInd1 + ":") ||
                     content.contains(" " + pInd + ":") ||
                     content.contains(" " + pIndInt + ":") ||
                     content.contains(" " + pInd2Zeros + ":") ||
                     content.contains(" " + pInd3Zeros + ":"))
            ){
//                System.out.println(currentThread() + "\n");
//                System.out.println(nv + "\n" + pInd + "\n" + pIndInt + "\n" + pInd2Zeros + "\n" + pInd3Zeros + "\n" + content +"\n=======================");
                switch (content.substring(0, 5)){

                    case m02Header : {
                        //System.out.println("IN 02" + "CONTENT : " + content);
                        operations.add(m02.m02Parsing(content, nv, nvOwner));
                        break;
                    }

                    case m09Header : {
                        //System.out.println("IN 09" + "CONTENT : " + content);
                        operations.add(m09.m09Parsing(content, nv, nvOwner));
                        break;
                    }

                    case m200Header : {
                        //System.out.println("IN 200" + "CONTENT : " + content);
                        operations.add(m200.m200Parsing(content, nv, nvOwner));
                        break;
                    }

                    case m201Header : {
                        //System.out.println("IN 201" + "CONTENT : " + content);
                        operations.add(m201.m201Parsing(content, nv, nvOwner));
                        break;
                    }

                    case m202Header : {
                        //System.out.println("IN 202" + "CONTENT : " + content);
                        operations.add(m202.m202Parsing(content, nv, nvOwner));
                        break;
                    }

                    case m203Header : {
                        //System.out.println("IN 203" + "CONTENT : " + content);
                        operations.add(m203.m203Parsing(content, nv, nvOwner));
                        break;
                    }

                    case m209Header : {
                        //System.out.println("IN 209" + "CONTENT : " + content);
                        operations.add(m209.m209Parsing(content, nv, nvOwner));
                        break;
                    }

                    case m220Header : {
                        //System.out.println("IN 220" + "CONTENT : " + content);
                        operations.add(m220.m220Parsing(content, nv, nvOwner));
                        break;
                    }

                    case m241Header : {
                        //System.out.println("IN 241" + "CONTENT : " + content);
                        operations.add(m241.m241Parsing(content, nv, nvOwner));
                        break;
                    }

                    case m242Header : {
                        //System.out.println("IN 242" + "CONTENT : " + content);
                        operations.add(m242.m242Parsing(content, nv, nvOwner));
                        break;
                    }


                }



            }



        }




        for (List<String> str : operations) {
            if (str.size() > 0){
                operationsForMain.add(str);
            }
        }



        Main.mainOperations.add(operationsForMain);



    }//RUN



}