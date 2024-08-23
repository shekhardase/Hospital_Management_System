package HospitalManagementSystem;

import javax.print.Doc;
import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem
{
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "Kakashi@2004";


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url , username , password);
            Patient patient = new Patient(connection,scanner);
            Doctor doctor = new Doctor(connection);
            while(true)
            {
                System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. Add Patients");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.println("Please enter your choice....");
                int choice = scanner.nextInt();

                switch (choice){
                    case 1:
                        // add patient
                        patient.addPatient();
                        break;
                    case 2:
                        // view patient
                        patient.viewPatients();
                        break;
                    case 3:
                        // view doctors
                        doctor.viewDoctors();
                        break;
                    case 4:
                        BookAppointment(patient,doctor,connection,scanner);
                        break;
                    case 5:
                        System.out.println("ENDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD   :)");
                        return;
                    default:
                        System.out.println("Enter valid choice");
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }




    public static void BookAppointment(Patient patient , Doctor doctor , Connection connection , Scanner scanner)
    {
        System.out.println("Enter patient id");
        int patientid = scanner.nextInt();
        System.out.println("Enter doctor id");
        int docid = scanner.nextInt();
        System.out.println("Enter Appointment Date (YYYY-MM-DD)");
        String AppointmentDate = scanner.next();

        if(patient.getPatientById(patientid) && doctor.getDoctorById(docid))
        {
            if(checkDoctorAvailability(docid,AppointmentDate , connection))
            {
                String appointmentQuery = "INSERT INTO appointments(id,id , appointment_date) Values(? , ?  , ? )";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patientid);
                    preparedStatement.setInt(2, docid);
                    preparedStatement.setString(3, AppointmentDate);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected>0){
                        System.out.println("Appointment Booked!");
                    }else{
                        System.out.println("Failed to Book Appointment!");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("Doctor not available on this date!!");
            }
        }else{
            System.out.println("Either doctor or patient doesn't exist!!!");
        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection){
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                if(count==0){
                    return true;
                }else{
                    return false;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}




