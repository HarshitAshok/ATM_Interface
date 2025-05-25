import java.io.*;
import java.util.*;

import static java.lang.System.exit;
//import java.util.logging.Logger;

class Account{
    static int acc_num = 1111;

    String acc_holder_name;

    int pin;

    double balance;

    String unique_id;

    int a_no;

    void createAcc() {
        a_no = acc_num;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter account holder name? ");
        acc_holder_name = in.nextLine();
        System.out.println("\nEnter Username? ");
        unique_id = in.nextLine();
        int length_pin;

        do{
            System.out.println("Enter secret 4 digit pin? ");
            pin = in.nextInt();
            length_pin = String.valueOf(pin).length();
        } while(length_pin != 4);

        System.out.println("Enter initial deposit account? ");
        balance = in.nextDouble();
        System.out.println("Congratulations Account successfully created!!\n");
        System.out.println("---Account details----\n" + "Account Number: " + a_no + "\nAccount Holder Name: " + acc_holder_name + "\nBalance: " + balance + "\nThank You!!");
        System.out.println("--------------------------\n");
        // create a file with the account number
        String fileName = acc_num + ".txt";
        File file = new File(fileName);

        try{
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write("Account Created!\n");
            writer.write("Account Number: " + acc_num + "\n");
            writer.write("USER ID- : " + unique_id + "\n");
            writer.write("Account Holder Name: " + acc_holder_name + "\n");
            writer.write("PIN: " + pin + "\n");
            writer.write("Balance: " + balance + "\n");
            writer.write("Date: " + new Date() + "\n\n\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred while creating this file: "+ fileName);
            e.printStackTrace();
//            logger.log("error in creating file",e);
        }
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        acc_num++;
    }
}

class ATMClass {
    void withdraw(Account acc){
        Scanner in = new Scanner(System.in);

        System.out.println("---Withdraw money mode---\n");
        System.out.print("Enter amount in multiples of 100: ");
        double amount = in.nextDouble();
        if(amount % 100 ==0){
            if(acc.balance >= amount){
                acc.balance -= amount;
                System.out.print("\n\nYour transaction is processing!--\n");
                try{
                    String fileName = acc.a_no + ".txt";
                    FileWriter fileWriter = new FileWriter(fileName,true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    bufferedWriter.write("Date: " + new Date() + "\n");
                    bufferedWriter.write("Withdrawal: " + amount + "\n");
                    bufferedWriter.write("Account Number: " + acc.a_no + "\n");
                    bufferedWriter.write("Remaining Amount: " + acc.balance + "\n");
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (Exception e) {
                    System.out.println("An error occurred while writing to this file!");
                    e.printStackTrace();
                }
                System.out.println("Thank you for banking with Us!!");
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            } else {
                System.out.println("Insufficient Funds!!");
            }
        } else {
            System.out.println("Amount not in multiples in 100!!");
            System.out.println("Try Again!!");
        }
    }

    void deposit_by_transfer(Account acc, double amount){
        acc.balance += amount;
        try{
            String fileName = acc.a_no + ".txt";
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Deposit: " + amount + "\n");
            bufferedWriter.write("Date: "+ new Date() + "\n");
            bufferedWriter.write("Amount Number: " + acc.a_no + "\n");
            bufferedWriter.write("Remaining Amount: " + acc.balance + "\n");
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while creating this file: ");
            e.printStackTrace();
        }
    }

    void deposit(Account acc){
        Scanner in = new Scanner(System.in);

        System.out.println("---Money Deposit Mode---\n");
        System.out.println("Enter amount to deposit? ");
        double amount = in.nextDouble();
        acc.balance += amount;
        try {
            String fileName = acc.a_no + ".txt";
            System.out.println("The File Name: " + fileName);
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Deposit: " + amount + "\n");
            bufferedWriter.write("Date: " + new Date() + "\n");
            bufferedWriter.write( "Account Number: " + acc.a_no + "\n");
            bufferedWriter.write( "Remaining Balance: " + acc.balance + "\n");
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while creating this file: ");
            e.printStackTrace();
        }
        System.out.println("Processing your Request! Please wait");
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Transaction completed Successfully!");
        System.out.println("\n\nThank you with banking with us!!");
        System.out.println("---Going to Login Page---");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void transfer(Account acc1, Account acc2, double amount){
        if(acc1.balance >= amount){
            acc1.balance -= amount;
            ATMClass a = new ATMClass();
            a.deposit_by_transfer(acc2,amount);
            try {
                String fileName = acc1.a_no + ".txt";
                System.out.println("The File Name: " + fileName);
                FileWriter fileWriter = new FileWriter(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("Transferred: " + amount + "\n");
                bufferedWriter.write("Date: " + new Date() + "\n");
                bufferedWriter.write( "Account Number: " + acc1.a_no + "\n");
                bufferedWriter.write( "Remaining Balance: " + acc1.balance + "\n");
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e){
                System.out.println("An error occurred while creating this file: ");
                e.printStackTrace();
            }
            System.out.println("Processing your request, Please wait!!");
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("\nAccount transfer completed successfully!\n---Going to the Login Page---");
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    void display_details(Account acc){

        System.out.println("\nDisplaying Account Details");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        String file_name = acc.a_no + ".txt";
        File file = new File(file_name);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }
        System.out.println("Screen will automatically timeout after 10 seconds!. . . .");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    void quit(){
        System.out.println("Thank you for Banking with us!!\n");
        exit(1);
    }
}

class run_atm{
    int account_search_by_unique_id(Account[] ac, String unique_id){
        for (int i = 0; i < 3; i++) {
            if(Objects.equals(unique_id, ac[i].unique_id))
                return i;
        }
        return -1;
    }

    int account_search_by_unique_id(Account[] ac, int account_number){
        for (int i = 0; i < 3; i++) {
            if(Objects.equals(account_number, ac[i].a_no))
                return i;
        }
        return -1;
    }

    void demo(Account[] ac){
        Scanner in = new Scanner(System.in);
        System.out.print("\n---Welcome to ATM---\n\n");
        System.out.println("Enter your card/Unique_id");
        String unique_id = in.nextLine();
        int i = account_search_by_unique_id(ac, unique_id);
        if(i == -1){
            System.out.println("Account not registered Yet!!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
//            return;
        } else {
            System.out.print("Enter your PIN: ");
            int pin = in.nextInt();
            if(pin == ac[i].pin) {
                System.out.println("\n\nSelect the Options as Given Below!\nWithdraw?---1\nDeposit?---2\nAccount Transfer---3\nDisplay Account Details---4\nQuit---5");
                int ch;
                ATMClass atm = new ATMClass();
                ch = in.nextInt();
                switch (ch){
                    case 1-> atm.withdraw(ac[i]);
                    case 2-> atm.deposit(ac[i]);
                    case 3-> {
                        System.out.print("Enter Account number to transfer funds?: ");
                        int account_transfer = in.nextInt();
                        int j = account_search_by_unique_id(ac, account_transfer);
                        if(j == -1){
                            System.out.println("\nAccount not yet Registered!!");
//                            return;
                        } else {
                            System.out.println("Enter Amount for Transferring funds?: ");
                            double amount = in.nextDouble();
                            atm.transfer(ac[i],ac[j], amount);
                        }
                    }
                    case 4-> atm.display_details(ac[i]);
                    case 5->atm.quit();
                }
            } else {
                System.out.println("Wrong PIN entered\nTry Again!!");
                try{
                    Thread.sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
//                return;
            }
        }
    }
}
public class ATM {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
        Account[] ac = new Account[3];
        for (int i = 0; i < 3; i++) {
            System.out.println("---Creating Account Mode---");
            ac[i] = new Account();
            ac[i].createAcc();
        }
        run_atm ob = new run_atm();
        while(true){
            ob.demo(ac);
        }
    }
}