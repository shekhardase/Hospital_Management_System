package HospitalManagementSystem;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.xml.namespace.QName;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection , Scanner scanner) throws SQLException {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient()
    {
        System.out.println("Enter patient name: ");
        String name = scanner.next();
        System.out.println("Enter patient Age : ");
        int age = scanner.nextInt();

        System.out.println("Enter patient gender : ");
        String gender = scanner.next();
        try{
            String query ="INSERT INTO PATIENTS(name,age,gender) VALUES(? , ? , ? )";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0)
            {
                System.out.println("Patient added Succesfully");
            }
            else{
                System.out.println("Failed to add patients");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void viewPatients() {
        String query = "SELECT * FROM Patients";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients:");
            System.out.println("_____________________________________________________________");
            System.out.printf("| %-12s | %-20s | %-10s | %-12s |\n", "Patient Id", "Name", "Age", "Gender");
            System.out.println("_____________________________________________________________");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");

                // Corrected the printf statement to include all arguments
                System.out.printf("| %-12d | %-20s | %-10d | %-12s |\n", id, name, age, gender);
                System.out.println("_____________________________________________________________");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean getPatientById(int id)
    {
        String query = "SELECT * FROM PATIENTS WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return true;
            }
            else {
                return false;
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }


}
