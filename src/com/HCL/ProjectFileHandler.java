package com.HCL;

import java.io.*;
import java.util.Scanner;

public class ProjectFileHandler {
    private File myFile;
    private final Scanner sc = new Scanner(System.in);

    public ProjectFileHandler(String directory, String name){
        myFile = new File(directory, name);
    }

    public void createNewFile(){
        boolean execute = false;

        try{
            execute = myFile.createNewFile();
            System.out.println("File \"" + myFile.getName() + "\" Created");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (execute == true){
            System.out.println("Would you like to write to \"" + myFile.getName() + "\" now? (y/n)");
            String input = sc.nextLine();
            if (input.equals("y")){
                try{
                    updateAnExistingFile();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else if (input.equals("n")){
            }
            else {
                System.out.println("Invalid Selection");
            }
        }
    }

    public void readAnExistingFile(){

        StringBuilder text = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(myFile));

            System.out.println("Document Title: \"" + myFile.getName() + "\"\n" );
            String line = in.readLine();
            while (line != null){
                text.append(line + "\n");
                line = in.readLine();
            }

            System.out.println(text);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateAnExistingFile(){

        StringBuilder text = new StringBuilder();
        System.out.println("Please write lines of text (Enter \"e\" to end):");
        boolean more = true;
        while (more){
            String s = sc.nextLine();
            if (s.equals("e")){
                more = false;
                break;
            }
            else{
                text.append(s + "\n");
            }
        }

        System.out.println("");

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(myFile));
            out.append(text.toString());
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteAnExistingFile(){
        System.out.println("Are you sure you want to delete \"" + myFile.getName() + "\" (y/n)");
        String input = sc.nextLine();
        if (input.equals("y")){
            try{
                myFile.delete();
                System.out.println("File \"" + myFile.getName() + "\" was deleted");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (input.equals("n")){
            System.out.println("File deletion was aborted");
        }
        else {
            System.out.println("Invalid Selection");
        }
    }






}
