import java.util.ArrayList;
import java.util.List;

class VagonHandlerThread extends Thread {

    private String nv;
    private String nvOwner;
    private List<String> fileContents;

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





    public VagonHandlerThread(String vag, List<String> fileContents){
        this.nv = vag.substring(0, 8);
        this.nvOwner = vag.substring(8).trim();
        this.fileContents = fileContents;
    }


    @Override
    public void run(){

        currentThread().setName(nv);

        Fields fields = new Fields();

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
                     content.contains(" " + fields.getPInd() + " ") ||
                     content.contains(" " + fields.getPIndInt() + " ") ||
                     content.contains(" " + fields.getPInd2Zeros() + " ") ||
                     content.contains(" " + fields.getPInd3Zeros() + " ") ||
                     content.contains(" " + fields.getPInd() + ":") ||
                     content.contains(" " + fields.getPIndInt() + ":") ||
                     content.contains(" " + fields.getPInd2Zeros() + ":") ||
                     content.contains(" " + fields.getPInd3Zeros() + ":"))
            ){

                //System.out.println(currentThread() + "\n" + content);

                switch (content.substring(0, 5)){

                    case m02Header : {
                        //System.out.println("===\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "------------------------------");
                        operations.add(m02.m02Parsing(content, nv, nvOwner, fields));
                        //System.out.println("=====\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "----------------------------------");

                        break;

                    }

                    case m09Header : {
                        //System.out.println("===\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "------------------------------");
                        operations.add(m09.m09Parsing(content, nv, nvOwner, fields));
                        //System.out.println("=====\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "----------------------------------");
                        break;
                    }

                    case m200Header : {
                        //System.out.println("===\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "------------------------------");
                        operations.add(m200.m200Parsing(content, nv, nvOwner, fields));
                        //System.out.println("=====\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "----------------------------------");
                        break;
                    }

                    case m201Header : {
                        //System.out.println("===\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "------------------------------");
                        operations.add(m201.m201Parsing(content, nv, nvOwner, fields));
                        //System.out.println("=====\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "----------------------------------");
                        break;
                    }

                    case m202Header : {
                        //System.out.println("===\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "------------------------------");
                        operations.add(m202.m202Parsing(content, nv, nvOwner, fields));
                        //System.out.println("=====\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "----------------------------------");
                        break;
                    }

                    case m203Header : {
                        //System.out.println("===\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "------------------------------");
                        operations.add(m203.m203Parsing(content, nv, nvOwner, fields));
                        //System.out.println("=====\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "----------------------------------");
                        break;
                    }

                    case m209Header : {
                        //System.out.println("===\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "------------------------------");
                        operations.add(m209.m209Parsing(content, nv, nvOwner, fields));
                        System.out.println("=====\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "----------------------------------");
                        break;
                    }

                    case m220Header : {
                        //System.out.println("===\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "------------------------------");
                        operations.add(m220.m220Parsing(content, nv, nvOwner, fields));
                        //System.out.println("=====\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "----------------------------------");
                        break;
                    }

                    case m241Header : {
                        //System.out.println("===\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "------------------------------");
                        operations.add(m241.m241Parsing(content, nv, nvOwner, fields));
                        //System.out.println("=====\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "----------------------------------");
                        break;
                    }

                    case m242Header : {
                        //System.out.println("===\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "------------------------------");
                        operations.add(m242.m242Parsing(content, nv, nvOwner, fields));
                        //System.out.println("=====\n" + currentThread() + "\n" + fields.getPInd() + "\n" + fields.getPIndInt() + "\n" + fields.getPInd2Zeros() + "\n" + fields.getPInd3Zeros() + "\n" + content + "----------------------------------");
                        break;
                    }


                }


            }


        }



        for (List<String> str : operations) {
            if(str != null){
                if (str.size() > 0){
                    operationsForMain.add(str);
                }
            }
        }



        Main.mainOperations.add(operationsForMain);



    }//RUN



}