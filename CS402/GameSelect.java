package CS402;

public class GameSelect {
    public static void main(String[] args) {

        String template = "========================================\n"
                + "Group members:\n"
                + "SID: %s    Name: %s\n"
                + "SID: %s    Name: %s\n"
                + "SID: %s    Name: %s\n"
                + "========================================";

        String formattedString = String.format(template,
                "12471043", "mok king lam",
                "12471044", "Pong Tsz Cheuk James",
                "12471046", "Lam Yu Hin");

        System.out.println(formattedString);

        Games.GameSelect(args);
    }
}
