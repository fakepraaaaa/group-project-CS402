

public class GameSelect {
    public static void main(String[] args) {

        String template = "========================================\n"
                + "Group members:\n"
                + "SID: %s    Name: %s\n"
                + "SID: %s    Name: %s\n"
                + "SID: %s    Name: %s\n"
                + "========================================";

        String formattedString = String.format(template,
                "id1", "name1",
                "id2", "name2",
                "id3", "name3");

        System.out.println(formattedString);

        Games.GameSelect(args);
    }
}
