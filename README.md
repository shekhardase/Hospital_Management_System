# Hospital Management System

This repository contains a simple Java-based application for managing hospital operations, developed using JDBC for database interaction with SQL. The system allows for efficient management of doctors, patients, and their associated details.

## Features

- **View Doctors**: Retrieve and display detailed information about doctors, including their IDs, names, and specializations.
- **View Patients**: Retrieve and display patient information, including their IDs, names, ages, and gender.
- **Database Interaction**: Utilizes JDBC for seamless integration with an SQL database, allowing for CRUD operations.

## Technologies Used

- **Java**: Core application logic.
- **JDBC**: Database connectivity.
- **SQL**: Backend database to store and manage hospital data.

## Getting Started

Follow these steps to set up and run the project on your local machine.

### Prerequisites

- **Java JDK**: Ensure that Java is installed on your system.
- **SQL Database**: MySQL or any other SQL-based database.
- **IDE**: Optional, but using an IDE like IntelliJ IDEA or Eclipse can simplify the process.

### Installation

**Clone the repository**
   ```bash
   git clone https://github.com/yourusername/hospital-management-system.git
   cd hospital-management-system
```

## Set up the Database

1. **Create a new SQL database** named `hospital_management`.
2. **Create the required tables**:
    ```sql
    CREATE TABLE Doctors (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100),
        specialization VARCHAR(100)
    );

    CREATE TABLE Patients (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100),
        age INT,
        gender VARCHAR(10)
    );

    CREATE TABLE Appointments (
        id INT PRIMARY KEY AUTO_INCREMENT,
        date DATE,
        patient_id INT,
        doc_id INT,
        FOREIGN KEY (patient_id) REFERENCES Patients(id),
        FOREIGN KEY (doc_id) REFERENCES Doctors(id)
    );
    ```
3. **Populate the tables** with initial data if necessary.

## Configure JDBC

Update the connection settings in the `Doctor`, `Patient`, and `Appointment` classes to match your database credentials:

```java
String url = "jdbc:mysql://localhost:3306/hospital_management";
String username = "your_username";
String password = "your_password";
Connection connection = DriverManager.getConnection(url, username, password);
```


## Run application
```
javac HospitalManagementSystem.java
java HospitalManagementSystem
```


## Usage

- **View Doctors**: The application will display a list of all doctors stored in the database.
- **View Patients**: The application will display a list of all patients stored in the database.
- **Manage Appointments**: Handle and view patient appointments, including doctor and patient details.

## Future Enhancements

- Add functionality to manage prescriptions and billing.
- Implement a graphical user interface (GUI) for easier interaction.
- Expand the database to include more hospital-related data.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or new features.

