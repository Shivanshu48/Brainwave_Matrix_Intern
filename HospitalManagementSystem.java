import java.util.*;

class Patient {
    int id;
    String name;
    String gender;
    int age;
    String ailment;
    List<String> medicalHistory;

    public Patient(int id, String name, String gender, int age, String ailment) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.ailment = ailment;
        this.medicalHistory = new ArrayList<>();
    }

    public void addMedicalHistory(String record) {
        medicalHistory.add(record);
    }

    public List<String> getMedicalHistory() {
        return medicalHistory;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Ailment: " + ailment;
    }
}

class Staff {
    int id;
    String name;
    String role;
    String specialization;  // For doctors, e.g., Cardiology, Neurology
    
    public Staff(int id, String name, String role, String specialization) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.specialization = specialization;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Role: " + role + ", Specialization: " + specialization;
    }
}

class Appointment {
    int id;
    int patientId;
    int doctorId;
    String date;
    String status;

    public Appointment(int id, int patientId, int doctorId, String date) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.status = "Scheduled";
    }

    public void reschedule(String newDate) {
        this.date = newDate;
        this.status = "Rescheduled";
    }

    public void cancel() {
        this.status = "Cancelled";
    }

    public String toString() {
        return "Appointment ID: " + id + ", Patient ID: " + patientId + ", Doctor ID: " + doctorId + ", Date: " + date + ", Status: " + status;
    }
}

class Bill {
    int id;
    int patientId;
    double treatmentCost;
    double medicineCost;
    boolean isPaid;

    public Bill(int id, int patientId, double treatmentCost, double medicineCost) {
        this.id = id;
        this.patientId = patientId;
        this.treatmentCost = treatmentCost;
        this.medicineCost = medicineCost;
        this.isPaid = false;
    }

    public double calculateTotal() {
        return treatmentCost + medicineCost;
    }

    public void markAsPaid() {
        this.isPaid = true;
    }

    public String toString() {
        return "Bill ID: " + id + ", Patient ID: " + patientId + ", Total: $" + calculateTotal() + ", Paid: " + (isPaid ? "Yes" : "No");
    }
}

class Inventory {
    String itemName;
    int quantity;
    double pricePerItem;

    public Inventory(String itemName, int quantity, double pricePerItem) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    public void updateStock(int quantity) {
        this.quantity += quantity;
    }

    public String toString() {
        return "Item: " + itemName + ", Quantity: " + quantity + ", Price: $" + pricePerItem;
    }
}

public class HospitalManagementSystem {
    List<Patient> patients = new ArrayList<>();
    List<Staff> staffMembers = new ArrayList<>();
    List<Appointment> appointments = new ArrayList<>();
    List<Bill> bills = new ArrayList<>();
    List<Inventory> inventoryList = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    // Register a patient
    public void registerPatient() {
        System.out.println("Enter Patient ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.println("Enter Patient Name:");
        String name = scanner.nextLine();
        
        System.out.println("Enter Gender (Male/Female):");
        String gender = scanner.nextLine();
        
        System.out.println("Enter Age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.println("Enter Ailment:");
        String ailment = scanner.nextLine();
        
        Patient patient = new Patient(id, name, gender, age, ailment);
        patients.add(patient);
        System.out.println("Patient Registered: " + patient);
    }

    // Schedule an appointment
    public void scheduleAppointment() {
        System.out.println("Enter Appointment ID:");
        int appointmentId = scanner.nextInt();
        
        System.out.println("Enter Patient ID:");
        int patientId = scanner.nextInt();
        
        System.out.println("Enter Doctor ID:");
        int doctorId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.println("Enter Appointment Date (YYYY-MM-DD):");
        String date = scanner.nextLine();
        
        Appointment appointment = new Appointment(appointmentId, patientId, doctorId, date);
        appointments.add(appointment);
        System.out.println("Appointment Scheduled: " + appointment);
    }

    // Generate a bill for a patient
    public void generateBill() {
        System.out.println("Enter Bill ID:");
        int billId = scanner.nextInt();
        
        System.out.println("Enter Patient ID:");
        int patientId = scanner.nextInt();
        
        System.out.println("Enter Treatment Cost:");
        double treatmentCost = scanner.nextDouble();
        
        System.out.println("Enter Medicine Cost:");
        double medicineCost = scanner.nextDouble();
        
        Bill bill = new Bill(billId, patientId, treatmentCost, medicineCost);
        bills.add(bill);
        System.out.println("Bill Generated: " + bill);
    }

    // Add inventory
    public void addInventory() {
        System.out.println("Enter Item Name:");
        String itemName = scanner.next();
        
        System.out.println("Enter Quantity:");
        int quantity = scanner.nextInt();
        
        System.out.println("Enter Price per Item:");
        double price = scanner.nextDouble();
        
        Inventory item = new Inventory(itemName, quantity, price);
        inventoryList.add(item);
        System.out.println("Inventory Updated: " + item);
    }

    // Display all patients
    public void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
        } else {
            for (Patient p : patients) {
                System.out.println(p);
            }
        }
    }

    // Display all appointments
    public void displayAllAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment a : appointments) {
                System.out.println(a);
            }
        }
    }

    // Display all bills
    public void displayAllBills() {
        if (bills.isEmpty()) {
            System.out.println("No bills generated.");
        } else {
            for (Bill b : bills) {
                System.out.println(b);
            }
        }
    }

    // Display all inventory items
    public void displayAllInventory() {
        if (inventoryList.isEmpty()) {
            System.out.println("No inventory items.");
        } else {
            for (Inventory i : inventoryList) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        HospitalManagementSystem hospital = new HospitalManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHospital Management System Menu:");
            System.out.println("1. Register Patient");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. Generate Bill");
            System.out.println("4. Add Inventory");
            System.out.println("5. Display All Patients");
            System.out.println("6. Display All Appointments");
            System.out.println("7. Display All Bills");
            System.out.println("8. Display All Inventory");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    hospital.registerPatient();
                    break;
                case 2:
                    hospital.scheduleAppointment();
                    break;
                case 3:
                    hospital.generateBill();
                    break;
                case 4:
                    hospital.addInventory();
                    break;
                case 5:
                    hospital.displayAllPatients();
                    break;
                case 6:
                    hospital.displayAllAppointments();
                    break;
                case 7:
                    hospital.displayAllBills();
                    break;
                case 8:
                    hospital.displayAllInventory();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
