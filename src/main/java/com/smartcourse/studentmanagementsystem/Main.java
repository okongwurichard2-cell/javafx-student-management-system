
package com.smartcourse.studentmanagementsystem;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Main extends Application{
    private final ObservableList<Student> students = FXCollections.observableArrayList();
    private Student selectedStudent;
    
    private void showAlertWarning(String title, String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
    }
    
    private void showAlertConfirmation(String title, String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
    }
   
    
    @Override
    @SuppressWarnings("unchecked")
    public void start(Stage stage){
        Label title = new Label("Student Registration");
        title.getStyleClass().add("title");
        
        Label idLabel= new Label("Student ID: ");
        TextField idField = new TextField();
        
        Label lastNameLabel= new Label("Last name: ");
        TextField lastNameField = new TextField();
        
        Label firstNameLabel= new Label("First name: ");
        TextField firstNameField = new TextField();
        
        Label courseLabel= new Label("Course label: ");
        TextField courseField = new TextField();
        
        Button addButton = new Button("Add student");
        Button updateButton = new Button("Update Data");
        Button deleteButton = new Button("Delete");
        Button clearButton = new Button("Clear");
        
        addButton.getStyleClass().add("add-button");
        updateButton.getStyleClass().add("update-button");
        deleteButton.getStyleClass().add("delete-button");
        clearButton.getStyleClass().add("clear-button");
        
        TableView<Student> table = new TableView<>();
        
        
        addButton.setOnAction(event ->{
            String id = idField.getText();
            String lastName = lastNameField.getText();
            String firstName = firstNameField.getText();
            String course = courseField.getText();
            
            if(id.isEmpty() || lastName.isEmpty() || firstName.isEmpty() 
                    || course.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all field.");
                alert.showAndWait();
                
                return;  
            }
            
            if(id.length() < 3){
                showAlertWarning("Invalid student ID", "ID cannot be less than 3");
                return;
            }
            
            if(firstName.length() < 2){
                showAlertWarning("Invalid first name", "First name cannot be less than 2");
                return;
            }
            
            if(lastName.length() < 2){
                showAlertWarning("Invalid last name", "Lastname cannot be less than 2");
            }
            
            if(course.length() < 5){
                showAlertWarning("Invalid course", "Course cannot be less than 5");
                return;
            }
            
            Student student = new Student(id, firstName, lastName, course);
            students.add(student);
            
            showAlertConfirmation("Student added Success", "Student: " + firstName + " " + lastName + " added successfully");
            
            idField.clear();
            lastNameField.clear();
            firstNameField.clear();
            courseField.clear();
                  
            System.out.println("ID: " + id);
            System.out.println("Names: " + lastName + " " + firstName);
            System.out.println("Course: " + course);
        });
        
        
        updateButton.setOnAction(event -> {
            Student selectedStudent = table.getSelectionModel().getSelectedItem();
            if(selectedStudent == null){
                showAlertWarning(
                "No student selected",
                "Please select a student to update record."
                );
                return;
            }
            
            String id = idField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String firstName =  firstNameField.getText().trim();
            String course = courseField.getText().trim();
            
            if(id.isEmpty() || lastName.isEmpty() || firstName.isEmpty() || course.isEmpty()){
                showAlertWarning(
                        "Empty Fields",
                        "Fields must not be empty."
                );
            }
            
            if(id.length() < 3){
                showAlertWarning("Invalid student ID", "ID cannot be less than 3");
                return;
            }
            
            if(firstName.length() < 2){
                showAlertWarning("Invalid first name", "First name cannot be less than 2");
                return;
            }
            
            if(lastName.length() < 2){
                showAlertWarning("Invalid last name", "Lastname cannot be less than 2");
            }
            
            if(course.length() < 5){
                showAlertWarning("Invalid course", "Course cannot be less than 5");
            }
            
            selectedStudent.setID(id);
            selectedStudent.setLastName(lastName);
            selectedStudent.setFirstName(firstName);
            selectedStudent.setCourse(course);
            
            table.refresh();
            showAlertConfirmation(
                    "Updated succssfully",
                    "Fields has been updated."
            );
            
            idField.clear();
            lastNameField.clear();
            firstNameField.clear();
            courseField.clear();
            
            table.getSelectionModel().clearSelection();
            selectedStudent = null;
            
        });
        
        clearButton.setOnAction(event ->{
            idField.clear();
            lastNameField.clear();
            firstNameField.clear();
            courseField.clear();
        });
        
        deleteButton.setOnAction(event -> {
            Student selectedStudent = 
                    table.getSelectionModel().getSelectedItem();
            if(selectedStudent == null){
                showAlertWarning(
                        "No student selected",
                        "Please select a student to delete"
                );
            }
            else{
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setTitle("Delete student");
                confirmation.setHeaderText(null);
                confirmation.setContentText(
                        "Are you sure you want to delete " +
                        selectedStudent.getFirstName() + 
                        " " +
                        selectedStudent.getLastName() + "?"
                );
                confirmation.showAndWait().ifPresent(response -> {
                    if(response == ButtonType.OK){
                        students.remove(selectedStudent);
                        idField.clear();
                        firstNameField.clear();
                        lastNameField.clear();
                        courseField.clear();
                        
                        table.getSelectionModel().clearSelection();
                        
                        showAlertConfirmation(
                        "Student deleted",
                                "Student was deleted successfully"
                        );
                    }
                });
                        
            }
            
        });
        
        
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        
        form.add(idLabel, 0, 0);
        form.add(idField, 1, 0);
        
        form.add(lastNameLabel, 0, 1);
        form.add(lastNameField, 1, 1);
        
        form.add(firstNameLabel, 0, 2);
        form.add(firstNameField, 1, 2);
        
        form.add(courseLabel, 0, 3);
        form.add(courseField, 1, 3);
        
        form.add(addButton, 0, 4);
        form.add(updateButton, 1, 4);
        form.add(deleteButton, 2, 4);
        form.add(clearButton, 3, 4);
        
        // javafx:run
        
        // TABLE SETUP
        TableColumn<Student, String> idColumn = new TableColumn<>("Student ID");
        TableColumn<Student, String> firstNameColumn = new TableColumn<>("First name");
        TableColumn<Student, String> lastNameColumn = new TableColumn<>("Last name");
        TableColumn<Student, String> courseColumn = new TableColumn<>("Student Course");
        
        // Tell column which properties to display
        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        
        // connect our list to the table
        table.getColumns().addAll(
                idColumn,
                firstNameColumn,
                lastNameColumn,
                courseColumn
        );
        
        table.setItems(students);
        
        table.setOnMouseClicked(event -> {
            Student selectedStudent = table.getSelectionModel().getSelectedItem();
            if(selectedStudent != null){
                idField.setText(selectedStudent.getStudentID());
                lastNameField.setText(selectedStudent.getLastName());
                firstNameField.setText(selectedStudent.getFirstName());
                courseField.setText(selectedStudent.getCourse());
            }
        });
        
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(title, form, table);
        
        
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(
                getClass().getResource("style.css").toExternalForm()
        );
        stage.setTitle("Student Management System");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args){
        launch();
    }
}
