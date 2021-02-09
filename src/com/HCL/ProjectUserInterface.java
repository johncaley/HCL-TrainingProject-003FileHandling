package com.HCL;

import java.io.File;
import java.util.Scanner;

public class ProjectUserInterface {

    private static final String directoryPath = ".\\ProjectDocuments";
    private static final Scanner sc = new Scanner(System.in);

    public static void initializeFilehandling(){
        boolean more = true;

        System.out.println("Welcome to File Editor");

        while (more){
            System.out.println("\nPlease Select Options:");
            System.out.println("1) Create New File");
            System.out.println("2) Read Existing File");
            System.out.println("3) Update Existing File");
            System.out.println("4) Delete Existing File");
            System.out.println("5) EXIT");
            String input = sc.nextLine();

            switch (input){
                case "1":
                    createANewFile();
                    break;
                case "2":
                    readAnExistingFile();
                    break;
                case "3":
                    updateAnExistingFile();
                    break;
                case "4":
                    deleteAnExistingFile();
                    break;
                case "5":
                    more = false;
                    break;
                default:
                    System.out.println("Invalid Selection");
            }
        }
    }

    public static void createANewFile(){
        String documentName;
        StringBuilder documentContents = new StringBuilder();

        System.out.println("Create new file name:");
        documentName = sc.nextLine();

        ProjectFileHandler newFile = new ProjectFileHandler(directoryPath, documentName);
        newFile.createNewFile();
    }

    public static void readAnExistingFile(){
        String documentName = selectFile("Read");
        if (documentName == null){
            System.out.println("Directory is Empty");
        }
        else{
            ProjectFileHandler fileToReadFrom = new ProjectFileHandler(directoryPath, documentName);
            fileToReadFrom.readAnExistingFile();
        }
    }

    public static void updateAnExistingFile(){
        String documentName = selectFile("Update");
        if (documentName == null){
            System.out.println("Directory is Empty");
        }
        else{
            ProjectFileHandler fileToUpdate = new ProjectFileHandler(directoryPath, documentName);
            fileToUpdate.updateAnExistingFile();
        }

    }

    public static void deleteAnExistingFile(){
        String documentName = selectFile("Delete");
        if (documentName == null){
            System.out.println("Directory is Empty");
        }
        else{
            ProjectFileHandler fileToDelete = new ProjectFileHandler(directoryPath, documentName);
            fileToDelete.deleteAnExistingFile();
        }
    }

    public static String selectFile(String option){
        File workingDirectory = new File(directoryPath);
        String contentOfDirectory[] = workingDirectory.list();

        if (contentOfDirectory.length < 1){
            return null;
        }

        System.out.println("Please Select Document to " + option);
        for (int i = 0; i <contentOfDirectory.length; i++){
            System.out.println(i+1 + ") " + contentOfDirectory[i]);
        }
        int input = Integer.parseInt(sc.nextLine()) - 1;

        while (input > contentOfDirectory.length-1 || input < 0){
            System.out.println("Invalid selection. Select again.");
            input = Integer.parseInt(sc.nextLine()) - 1;
        }

        return contentOfDirectory[input];
    }

}
