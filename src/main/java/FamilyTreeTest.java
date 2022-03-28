public class FamilyTreeTest {
    public static void main(String[] args) {
        String ancestorName = Input.getString("Enter the ancestor's name: ");                     // asks user for names
        String partnerName = Input.getString("Enter " + ancestorName + "'s partner's name: ");
        FamilyTree familyTree = new FamilyTree(ancestorName, partnerName);                             // sets up family tree
        Integer option;
        do {
            System.out.println("1: Add Child\n2: Display Family Tree\n0: Quit\n");
            option = getInteger();                                                              // get option
            switch (option) {                                                                   // check option
                case 0 -> System.out.println("Closing application...");
                case 1 -> addChild(familyTree);
                case 2 -> System.out.println(familyTree);
                default -> System.out.println("\nError: Invalid Option\n");
            }
        } while (!option.equals(0));           // while option not 0
    }


    private static Integer getInteger() {
        Integer option = 5;                                     // sets option to invalid option
        try {
            option = Input.getInteger("Enter Option: ");        // gets option from user
        } catch (Exception ignored) {
        }

        return option;
    }

    private static void addChild(FamilyTree familyTree) {
        String childName = Input.getString("Enter the child's name: ");     // gets name from user
        try {
            familyTree.addChild(childName);                                         // attempts to add child
        } catch (FamilyTreeExceptions.NotUniqueSiblingException e) {                       // if name already exists displays error message
            System.out.println("\nError: Child name not unique\n");
        }
    }
}
