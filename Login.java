import java.util.Scanner;

public class Login {
    private String username;
    private String password;
    private String cellPhoneNumber;

    public Login()
    {
        this.username = "";
        this.password = "";
        this.cellPhoneNumber = "";
    }

    public boolean checkUserName(String username)
    {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password)
    {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[!@#$%^&*()].*");
    }

    public boolean checkCellPhoneNumber(String cellPhoneNumber)
    {
        return cellPhoneNumber.startsWith("+27") && cellPhoneNumber.length() <= 13;
    }

    public String registerUser(String username, String password, String cellPhoneNumber)
    {
        if (!checkUserName(username))
        {
            return "Invalid username format, username must contain an underscore and a maximum of five characters in length.";
        }
        if (!checkPasswordComplexity(password))
        {
            return "Invalid password format; password must contain a minimum of eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellPhoneNumber))
        {
            return "Invalid cell phone number format.";
        }

        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;

        return "Registration successful!";
    }


    public boolean loginUser(String username, String password)
    {
        return this.username.equals(username) && this.password.equals(password);
    }


    public String returnLoginStatus(boolean loginSuccess)
    {
        if (loginSuccess)
        {
            return "Welcome " + username + ", you have successfully logged in!";
        }
        else
        {
            return "Username or password incorrect, please try again.";
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        //REGISTRATION

        System.out.println("Register an account:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter cell phone number: ");
        String cellPhoneNumber = scanner.nextLine();

        String registrationMessage = login.registerUser(username, password, cellPhoneNumber);
        System.out.println(registrationMessage);

        // LOGIN

        System.out.println("Login to your account:");
        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();


        boolean loginSuccess = login.loginUser(loginUsername, loginPassword);
        String loginStatus = login.returnLoginStatus(loginSuccess);
        System.out.println(loginStatus);

        scanner.close();
    }
}