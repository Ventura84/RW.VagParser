import org.apache.poi.util.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    public static List<List<List<String>>> mainOperations = new ArrayList<>();



    private static String[] readFileToArray(String fileName) throws IOException {

        Charset cp866 = Charset.forName("cp866");


        File file = new File(fileName);
        FileInputStream inFile = new FileInputStream(file);
        byte[] fileB = new byte[inFile.available()];
        inFile.read(fileB);
        String fileList = new String(fileB, cp866);
        String[] fileToArray = fileList.split("\n");
        System.out.println("TOTAL ELEMENTS : " + fileToArray.length);

        return fileToArray;

    }

    private static List<String> readFilesForSearching(String pathToFiles) throws IOException {

        System.out.println("\nREADING FILES...");
        File dir = new File(pathToFiles);

        //List<File> fileLst = new ArrayList<>();
        List<String> fileContents = new ArrayList<>();
        //Charset utf8 = StandardCharsets.UTF_8;
        Charset cp866 = Charset.forName("cp866");
        int fileCounter = 0;
        int filesInDir = 0;
        for (String dirList : Objects.requireNonNull(dir.list())) {
            File subDir = new File(dir + "/" + dirList);
            System.out.print(subDir);
            for ( File file : Objects.requireNonNull(subDir.listFiles())){
                if (file.isFile()){
                    fileCounter++;
                    filesInDir++;
                    //fileLst.add(file);

                    // VERSION UPDATE
                    FileInputStream inFile = new FileInputStream(file);
                    byte[] fileContent = new byte[inFile.available()];
                    inFile.read(fileContent);
                    fileContents.add(new String (fileContent, cp866));
                    //fileContents.add(new String(fileContent, ));
                    // VERSION UPDATE
                }
            }
            System.out.println(" (" + filesInDir +")");
            filesInDir = 0;
        }
        System.out.println("TOTAL FILES : " + fileCounter + "");

        return fileContents;

    }

    public static void main(String[] args) throws IOException, InterruptedException {


        long startTimeN = System.nanoTime();


        File fileToDelete = new File("out.txt");
        fileToDelete.delete();

        File fileToDeleteXls = new File("out.xlsx");
        fileToDeleteXls.delete();



        System.out.println("\nCREATING OUTPUT FILE");
        File outputFile = new File("out.txt");
        FileOutputStream outFile = new FileOutputStream(outputFile, true);

        System.out.println("\nCREATING OUTPUT FILE XLS");
        File outputFileXls = new File("out.xlsx");
        FileOutputStream outFileXls = new FileOutputStream(outputFileXls, true);

        System.out.println("\nREADING VAGON_LIST...");
        String[] vagonListAr = readFileToArray("vagon_list.txt");

        System.out.println("\nREADING STAN_CODES...");
        String[] stanCodesAr = readFileToArray("stan_codes.txt");

        System.out.println("\nREADING CARGO_CODES...");
        String[] cargoCodesAr = readFileToArray("cargo_codes.txt");

        List<String> fileContents = readFilesForSearching("FILES");

        System.out.println("\nSTART SEARCHING PROCESS");
        System.out.println("\tWAIT PLEASE...");
        System.out.println("\t...TAKE A SMOKE BREAK");


        List<Thread> threadList = new ArrayList<>();
        
        for (String vag : vagonListAr) {

            Thread thread;
            thread = new VagonHandlerThread(vag, fileContents);

            thread.start();
            threadList.add(thread);

        }

        for (Thread thread : threadList) {
            thread.join();
        }


        System.out.println("\nSEARCHING FINISHED\n");

        System.out.println("WRITING DATA INTO FILES...\n");

        System.out.println("WRITING DATA INTO TXT FILE...");

        String textForOutputFileHeader = String.format("%9s %20s %5s %8s %7s %7s %7s %7s %8s %7s %7s %15s %7s %1s %2s %n", "ВАГ", "КОМПАНИЯ", "СООБЩ", "СТ.ФОРМ", "ОПЕР", "ДАТА", "ВРЕМЯ", "СОСТ", "СТ.НАЗН", "ВЕС.ГР", "КОД.ГР", "ИНД.П", "НОМ.П", " ", "КОНТЕНТ");
        outFile.write((textForOutputFileHeader).getBytes());

        for (List<List<String>> operations : mainOperations) {
            for (List<String> op : operations) {
                //String textForOutputFile = String.format("%9s %20s %5s %8s %7s %7s %7s %7s %8s %7s %7s %15s %7s %1s %2s", NV, nv_company.substring(9), operation[0], operation[1], operation[2], operation[3], operation[4], operation[5], operation[6], operation[7], operation[8], operation[9], operation[10], "|", operation[11]);
                String textForOutputFile = String.format("%9s %20s %5s %8s %7s %7s %7s %7s %8s %7s %7s %15s %7s %1s %2s %n", op.get(0), op.get(1), op.get(2), op.get(3), op.get(4), op.get(5), op.get(6), op.get(7), op.get(8), op.get(9), op.get(10), op.get(11), op.get(12), "|", op.get(13));
                outFile.write((textForOutputFile).getBytes());
            }

        }

        outFile.close();
        System.out.println("DONE\n");


        System.out.println("WRITING DATA INTO XLSX FILE...");
        IOUtils.copy(ExcelFileCreator.infoToExcelFile(vagonListAr, stanCodesAr, cargoCodesAr, mainOperations), outFileXls);
        outFileXls.close();
        System.out.println("DONE\n");




        System.out.println("\nALL DONE ! " + (double)((System.nanoTime() - startTimeN)/1000000)/1000 + " sec.");


    }
}