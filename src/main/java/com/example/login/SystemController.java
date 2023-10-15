package com.example.login;


import javafx.scene.control.Alert;

import java.io.*;

public class SystemController {

    public static void createFolder() {
        String folderPath = "DB";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (created) {
                System.out.println("Folder created: " + folder.getAbsolutePath());
            } else {
                System.out.println("Error creating folder!");
            }
        } else {
            System.out.println("Folder already exists: " + folder.getAbsolutePath());
        }
    }

    public static void readFile() {
        try(FileInputStream input = new FileInputStream("db.txt");) {

            int data = input.read();
            while (data != -1) {
                System.out.print((char) data);
                data = input.read();
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    public static void addData(String username, String password, String email) {
        try {
            RandomAccessFile randomAccess = new RandomAccessFile("db.txt", "rw");

            randomAccess.seek(randomAccess.length());

            randomAccess.writeBytes("Username-" + username + ":");
            randomAccess.writeBytes("Password-" + password +":");
            randomAccess.writeBytes("Email-" + email +"\n");

            randomAccess.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void CheckData(String username, String password, String email) {

        try (FileInputStream input = new FileInputStream("db.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));){
            String line;
            String inp = "Username-" + username + ":"+"Password-" + password +":"+"Email-" + email;
            boolean match = false;
            boolean corr = false;
            while((line = reader.readLine()) != null){

                if(username.length()==0 || password.length()==0 || email.length()==0){
                    corr = true;
                }

                if(line.equals(inp)){
                    match = true;
                }
                if(username.length()>9 || password.length()>9 || email.length()>9){
                    match = true;
                    corr = true;
                }
            }
            if(match==true && corr == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Username and password already registered");
                alert.showAndWait();
            }else if(match == false && corr == true) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("all fields should be filled");
                alert.showAndWait();
            }else if(match == true && corr == true){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail");
                alert.setHeaderText("Fields should not contain more than 9 symbols  ");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Password matched");
                alert.showAndWait();
                addData(username,password,email);
            }
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText("File not found");
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText("Error reading file");
            alert.showAndWait();
        }


    }

    public static int CountLines() {
        int numLines = 0;
        try (FileInputStream fileStream = new FileInputStream("db.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));){
            String line = reader.readLine();
            while (line != null) {
                numLines++;
                line = reader.readLine();
            }
            return numLines;
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return 0;
        } catch (IOException e) {
            System.out.println("Error reading file.");
            return 0;
        }
    }
    private static boolean logged = false;
    public static boolean getLogged(){
        return logged;
    }
    public static void setLogged(){logged = false;}
    public static void logic(String username, String password) {
        try {
            RandomAccessFile file = new RandomAccessFile("db.txt", "rw");
            int numLines = CountLines();
            boolean a = false;
            boolean found = false;
            for (int i = 0; i < numLines; i++) {
                String line = file.readLine();
                String[] parts = line.split(":");
                String[] usernameParts = parts[0].split("-");
                String[] passwordParts = parts[1].split("-");
                String[] emailParts = parts[2].split("-");
                String storedUsername = usernameParts[1];
                String storedPassword = passwordParts[1];
                String fileEmail = emailParts[1];

                if (storedUsername.equals(username)) {
                    found = true;


                    if (storedPassword.equals(password)) {
                        if(username.equals("000")&&password.equals("000")){
                            System.out.println("User is remembered");
                        }else{
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Success");
                            alert.setHeaderText("Password matched");
                            alert.showAndWait();
                        }

                        logged = true;
                        break;

                    } else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Incorrect password");
                        alert.showAndWait();
                        break;
                    }
                }
            }

            if (!found && a ==false) {
                if(username.equals("000")&&password.equals("000")){
                    System.out.println("user is not remembered");
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Username not found");
                    alert.showAndWait();
                }

            }

            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
