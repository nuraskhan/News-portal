# Requirments
`module com.example.login {`

`requires javafx.controls;`

`requires javafx.fxml;`

`    opens com.example.login to javafx.fxml;`

`    exports com.example.login;`

`}`

## Overview
This JavaFX application is designed to serve as a fully functional news portal, featuring user registration, login, a main news page, and a password recovery mechanism. With a user-friendly graphical interface, this project allows users to access news articles while ensuring their data security and account recovery.

## Features
### 1. Sign-Up Page
* Users can create new accounts by providing essential information, including their name, email address, and a secure password.
* Data validation ensures that users enter a valid email address, and passwords are securely stored using encryption techniques.
### 2. Login Page
* Registered users can securely log in to access the news portal.
* User authentication is implemented to verify credentials before granting access.
### 3. Main News PAge
* Once authenticated, users can view a dynamic news feed, featuring the latest articles.
* The main page provides an intuitive and responsive interface for an enhanced user experience.
### 4. Forgot Password Functionality
* Users who forget their passwords can easily recover their accounts by clicking on the "Forgot Password" link.
* A secure password reset without email, just by entering random word from screen, this part should be upgrated by APIs