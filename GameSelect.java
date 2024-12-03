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
                "12471043", "MOK KING LAM",
                "id2", "name2",
                "id3", "name3");

        System.out.println(formattedString);

        Games.GameSelect(args);
    }
}
