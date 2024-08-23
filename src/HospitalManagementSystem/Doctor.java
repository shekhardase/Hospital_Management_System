package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private Connection connection;

    public Doctor(Connection connection) throws SQLException {
        this.connection = connection;
    }


    public void viewDoctors() {
        String query = "SELECT * FROM Doctors";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors:");
            System.out.println("___________________________________________________________");
            System.out.printf("| %-10s | %-20s | %-20s |\n", "Doc Id", "Name", "Specialization");
            System.out.println("___________________________________________________________");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specilization");

                System.out.printf("| %-10d | %-20s | %-20s |\n", id, name, specialization);
                System.out.println("___________________________________________________________");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int id)
    {
        String query = "SELECT * FROM Doctors WHERE id = ?";
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
