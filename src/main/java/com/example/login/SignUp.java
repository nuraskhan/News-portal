package com.example.login;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

public class SignUp extends Application {

    private TextField usernameField;
    private TextField passwordField;
    private TextField emailField;
    private CheckBox maleCheckBox;
    private CheckBox femaleCheckBox;
    private String randomText;
    public void start(Stage primaryStage) {
        //SIGN UP PAGE PROPERTIES
//********************************************************************************

        usernameField = new TextField();
        passwordField = new TextField();
        emailField = new TextField();
        maleCheckBox = new CheckBox("Male");
        femaleCheckBox = new CheckBox("Female");

        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        Label emailLabel = new Label("Email:");
        Label genderLabel = new Label("Gender:");

        Label ulabel1 = new Label("Field is empty");
        Label ulabel2 = new Label("Field is empty");
        ulabel1.setVisible(false);
        ulabel2.setVisible(false);

        usernameField.setOnMouseClicked(e -> {
            ulabel1.setVisible(usernameField.getText().isEmpty());
        });

        usernameField.setOnKeyTyped(e -> {
            ulabel1.setVisible(usernameField.getText().isEmpty());
        });
        VBox ulabelus = new VBox(usernameField, ulabel1);



        passwordField.setOnMouseClicked(e -> {
            ulabel2.setVisible(passwordField.getText().isEmpty());
        });

        passwordField.setOnKeyTyped(e -> {
            ulabel2.setVisible(passwordField.getText().isEmpty());
        });
        VBox ulabelpas = new VBox(passwordField, ulabel2);

        Label ulabel3 = new Label("Field is empty");
        ulabel3.setVisible(false);

        emailField.setOnMouseClicked(e -> {
            ulabel3.setVisible(emailField.getText().isEmpty());
        });

        emailField.setOnKeyTyped(e -> {
            ulabel3.setVisible(emailField.getText().isEmpty());
        });
        VBox ulabelem = new VBox(emailField, ulabel3);

        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();
            SystemController.CheckData(username,password,email);
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> primaryStage.close());

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(ulabelus, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(ulabelpas, 1, 1);
        gridPane.add(emailLabel, 0, 2);
        gridPane.add(ulabelem, 1, 2);
        gridPane.add(genderLabel, 0, 3);
        gridPane.add(maleCheckBox, 1, 3);
        gridPane.add(femaleCheckBox, 2, 3);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);
        Label technoNewsLabel = new Label("Techno news");
        Button loginButton = new Button("Login");
        loginButton.setAlignment(Pos.TOP_RIGHT);
        HBox.setHgrow(technoNewsLabel, Priority.ALWAYS);
        hbox.getChildren().addAll(technoNewsLabel, loginButton);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(signUpButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(hbox,gridPane, buttonBox);
        vbox.setAlignment(Pos.CENTER);

        Stage loginPage = new Stage();
        Stage news = new Stage();
        Stage fp = new Stage();

        loginButton.setOnAction(e -> {
            SystemController.logic("000","000");
            if(SystemController.getLogged()){
                news.show();
            }else{
                primaryStage.hide();
                loginPage.show();
            }
        });
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sign Up Page");

        // LOGIN PAGE PROPERTIES
//********************************************************************************
        Label usernameLabelL= new Label("Username:");
        TextField usernameFieldL = new TextField();
        Label loginPasswordLabelL= new Label("Password:");
        PasswordField passwordFieldL = new PasswordField();
        Button loginbuttonL = new Button("Login");
        CheckBox rememberMeCheckBox = new CheckBox("Remember me");

        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");

        forgotPasswordLink.setStyle("-fx-border-style: hidden;");
        forgotPasswordLink.setOnAction(event ->{
            loginPage.hide();
            fp.show();
        });
//FORGET PASSWORD? FIRSTLY VERIFICATION JUTS BY TEXTING RANDOM GENERATED WORD FROM SCREEN
//********************************************************************************

        Button generateButton = new Button("Generate Random Text");
        TextField randomnum = new TextField();
        Button resetButton = new Button("Reset Password");
        resetButton.setDisable(true);
        Label statusLabel = new Label("");

        generateButton.setOnAction(event -> {
            Random random = new Random();
            randomText = Integer.toString(random.nextInt(10000));
            statusLabel.setText("Random Text: " + randomText);
            resetButton.setDisable(false);
        });

        resetButton.setOnAction(event -> {
            String enteredText = randomnum.getText();
            if (enteredText.equals(randomText)) {
                Stage resetStage = new Stage();
                resetStage.setTitle("Reset Password");
                Label resetLabel = new Label("Enter new password:");
                PasswordField passwordField = new PasswordField();
                Button submitButton = new Button("Submit");
                submitButton.setOnAction(submitEvent -> {
                    resetStage.close();
                    statusLabel.setText("Password reset successfully!");
                });
                VBox resetLayout = new VBox(resetLabel, passwordField, submitButton);
                resetLayout.setAlignment(Pos.CENTER);
                resetLayout.setSpacing(10);
                Scene resetScene = new Scene(resetLayout, 300, 200);
                resetStage.setScene(resetScene);
                resetStage.show();
            } else {
                statusLabel.setText("Entered text does not match!");
            }
        });
        Button back = new Button("Back");
        back.setOnAction(event ->{
            fp.hide();
            loginPage.show();
        });
        VBox lays = new VBox(forgotPasswordLink, generateButton, randomnum, resetButton, statusLabel, back);
        lays.setAlignment(Pos.CENTER);
        lays.setSpacing(10);
        Scene scenefp = new Scene(lays, 300, 200);

        fp.setTitle("Forget Password");
        fp.setScene(scenefp);
// YOU ARE WELCOME TO MAIN PAGE
//********************************************************************************
        Label label1 = new Label("Field is empty");
        Label label2 = new Label("Field is empty");
        label1.setVisible(false); // Initially label is hidden
        label2.setVisible(false);

        usernameFieldL.setOnMouseClicked(e -> {
            label1.setVisible(usernameFieldL.getText().isEmpty());
        });

        usernameFieldL.setOnKeyTyped(e -> {
            label1.setVisible(usernameFieldL.getText().isEmpty());
        });
        VBox Llabelus = new VBox(usernameFieldL, label1);

        passwordFieldL.setOnMouseClicked(e -> {
            label2.setVisible(passwordFieldL.getText().isEmpty());
        });

        passwordFieldL.setOnKeyTyped(e -> {
            label2.setVisible(passwordFieldL.getText().isEmpty());
        });
        VBox Llabelpas = new VBox(passwordFieldL, label2);


        GridPane paneL = new GridPane();
        paneL.setHgap(10);
        paneL.setVgap(10);
        paneL.setPadding(new Insets(25, 25, 25, 25));
        paneL.setAlignment(Pos.CENTER);

        paneL.add(usernameLabelL, 0, 0);
        paneL.add(Llabelus, 1, 0);
        paneL.add(loginPasswordLabelL, 0, 1);
        paneL.add(Llabelpas, 1, 1);
        paneL.add(rememberMeCheckBox,0,2);
        paneL.add(loginbuttonL , 1, 2);
        paneL.add(forgotPasswordLink,0,3);


        HBox hboxLogin = new HBox();
        hboxLogin.setAlignment(Pos.TOP_LEFT);
        hboxLogin.setPadding(new Insets(10, 10, 10, 10));
        hboxLogin.setSpacing(10);
        Label technoNews = new Label("Techno news");
        Button SignRedirect = new Button("Sign Up");
        SignRedirect.setAlignment(Pos.TOP_RIGHT);
        HBox.setHgrow(technoNews, Priority.ALWAYS);
        hboxLogin.getChildren().addAll(technoNews, SignRedirect);


        BorderPane borderPaneL = new BorderPane();
        borderPaneL.setCenter(paneL);
        borderPaneL.setTop(hboxLogin);

        Scene sceneL = new Scene(borderPaneL, 400, 300);

        loginPage.setTitle("Login Page");
        loginPage.setScene(sceneL);
        SignRedirect.setOnAction(e -> {
            primaryStage.show();
            loginPage.hide();
        });
        loginbuttonL.setOnAction(event -> {
            String username = usernameFieldL.getText();
            String password = passwordFieldL.getText();
            SystemController.logic(username, password);
            if(SystemController.getLogged()){
                news.show();
                loginPage.hide();
            }
            if (rememberMeCheckBox.isSelected()) {
                if(SystemController.getLogged()){
                    SystemController.addData("000","000","000");
                }
            }




        });
        //
//**********************************************************************************
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        HBox topBar = new HBox();
        topBar.setSpacing(10);
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.setPadding(new Insets(10, 0, 10, 0));

        Button technologyButton = new Button("Technology Button");
        MenuBar menuBar = createMenuBar();
        technologyButton.setOnAction(event -> {
            menuBar.setVisible(true);
        });

        Button logoutButton = new Button("Log out");
        logoutButton.setOnAction(event -> {
            SystemController.setLogged();
            news.hide();
            loginPage.show();
            SystemController.logic("000","000");
            if(SystemController.getLogged()){
                news.show();
            }else{
                loginPage.show();
            }

        });

        Button contactUsButton = new Button("Contact us");
        contactUsButton.setOnAction(event ->{
            Stage newStage = new Stage();

            Label contentLabe1 = new Label("created by Nuraskhan Aldongarov");
            Label contentLabe2 = new Label("gmail: 220107085@stu.sdu.edu.kz");

            VBox vbox1 = new VBox(contentLabe1,contentLabe2);
            vbox1.setAlignment(Pos.CENTER);

            Scene scene1 = new Scene(vbox, 300, 200);
            newStage.setScene(scene1);
            newStage.show();
        });
        topBar.getChildren().addAll(technologyButton, logoutButton, contactUsButton);

        GridPane newsPane = new GridPane();
        newsPane.setPadding(new Insets(10));
        newsPane.setHgap(10);
        newsPane.setVgap(10);

        Image image1 = new Image("https://en.wikipedia.org/wiki/File:Dampfturbine_Montage01.jpg");
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(150);
        imageView1.setFitHeight(150);
        newsPane.add(imageView1, 0, 1);

        Image image2 = new Image("https://s0.rbk.ru/v6_top_pics/resized/1200xH/media/img/1/30/346972286065301.webp");
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(150);
        imageView2.setFitHeight(150);
        newsPane.add(imageView2, 1, 1);

        Image image3 = new Image("https://www.google.com/url?sa=i&url=https%3A%2F%2Fgadgetstripe.com%2Fthe-best-apps-for-entrepreneurs-grow-your-buisness%2F&psig=AOvVaw0fPJC0VqKeMPW4BpdUPImR&ust=1680441441752000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCODYqaviiP4CFQAAAAAdAAAAABAI");
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitWidth(150);
        imageView3.setFitHeight(150);
        newsPane.add(imageView3, 2, 1);

        Image image4 = new Image("https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.edology.com%2Fuploads%2Fmedia%2Fsulu-700x450%2F06%2F2166-blog-accounting-finance_how-does-the-global-economy-work-s.png%3Fv%3D1-0&tbnid=i9VpbmkiiX0D5M&vet=12ahUKEwisyL2J4Yj-AhUUtSoKHTbSBSEQMygBegUIARDjAQ..i&imgrefurl=https%3A%2F%2Fwww.edology.com%2Fblog%2Faccounting-finance%2Fhow-does-global-economy-work%2F&docid=lwPVSvPli9I7fM&w=615&h=396&q=economy&client=safari&ved=2ahUKEwisyL2J4Yj-AhUUtSoKHTbSBSEQMygBegUIARDjAQ");
        ImageView imageView4 = new ImageView(image4);
        imageView4.setFitWidth(150);
        imageView4.setFitHeight(150);
        newsPane.add(imageView4, 3, 1);

        Text newsText = new Text("News");
        newsText.setFont(Font.font("Arial", 20));
        HBox newsHbox = new HBox(newsText);
        newsHbox.setAlignment(Pos.CENTER);
        StackPane stackPane = new StackPane(newsHbox);
        stackPane.setPadding(new Insets(10));
        newsPane.add(stackPane, 0, 0, 4, 1);


        menuBar.setVisible(false);

        root.setTop(topBar);
        root.setCenter(newsPane);
        root.setBottom(menuBar);

        Scene scene2 = new Scene(root, 800, 600);
        news.setScene(scene2);


//********************************************************************************

        SystemController.logic("000","000");
        if(SystemController.getLogged()){
            news.show();
        }else{
            loginPage.show();
        }

    }
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu technologyMenu = new Menu("Technology");
        MenuItem technologyItem = new MenuItem("Technology Section");
        technologyItem.setOnAction(event -> {
            Stage newStage = new Stage();

            Label contentLabel = new Label("This is the technology section.");

            VBox vbox = new VBox(contentLabel);
            vbox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(vbox, 300, 200);
            newStage.setScene(scene);
            newStage.show();
        });
        technologyMenu.getItems().add(technologyItem);

        Menu sportsMenu = new Menu("Sports");
        MenuItem sportsItem = new MenuItem("Sports Section");
        sportsItem.setOnAction(event -> {
            Stage newStage = new Stage();

            Label contentLabel = new Label("This is the sports section.");

            VBox vbox = new VBox(contentLabel);
            vbox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(vbox, 300, 200);
            newStage.setScene(scene);
            newStage.show();
        });
        sportsMenu.getItems().add(sportsItem);

        Menu businessMenu = new Menu("Business");
        MenuItem businessItem = new MenuItem("Business Section");
        businessItem.setOnAction(event -> {
            Stage newStage = new Stage();

            Label contentLabel = new Label("This is the business section.");

            VBox vbox = new VBox(contentLabel);
            vbox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(vbox, 300, 200);
            newStage.setScene(scene);
            newStage.show();
        });
        businessMenu.getItems().add(businessItem);

        Menu economyMenu = new Menu("Economy");
        MenuItem economyItem = new MenuItem("Economy Section");
        economyItem.setOnAction(event -> {
            Stage newStage = new Stage();

            Label contentLabel = new Label("This is the economy section.");

            VBox vbox = new VBox(contentLabel);
            vbox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(vbox, 300, 200);
            newStage.setScene(scene);
            newStage.show();
        });
        economyMenu.getItems().add(economyItem);
        menuBar.getMenus().addAll(technologyMenu, sportsMenu, businessMenu, economyMenu);

        return menuBar;
    }
}
