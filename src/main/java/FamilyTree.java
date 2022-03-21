public class FamilyTree implements FamilyTreeADT {

    private static class FamilyTreeNode {
        private String name;
        private FamilyTreeNode partner;
        private FamilyTreeNode sibling;
        private FamilyTreeNode firstChild;
    }

    private final FamilyTreeNode ancestor;

    public FamilyTree(String ancestorName, String partnerName) {
        FamilyTreeNode ancestorPartner = new FamilyTreeNode();          // Sets up new nodes
        this.ancestor = new FamilyTreeNode();

        ancestorPartner.name = partnerName;                             // Sets up partner
        ancestorPartner.partner = this.ancestor;

        this.ancestor.name = ancestorName;                              // Sets up ancestor
        this.ancestor.partner = ancestorPartner;
    }

    @Override
    public void addChild(String childName) throws NotUniqueSiblingException {
        FamilyTreeNode newChild = new FamilyTreeNode();                 // Creates new node for the new child
        newChild.name = childName;
        if (this.ancestor.firstChild == null) {                         // If no children already new child is assigned
            this.ancestor.firstChild = newChild;
            this.ancestor.partner.firstChild = newChild;
        } else {
            FamilyTreeNode furthestSibling = findFurthestSibling(this.ancestor.firstChild, childName);  // Searches for the last child
            furthestSibling.sibling = newChild;               // Adds new child to the linked list
        }
    }

    private FamilyTreeNode findFurthestSibling(FamilyTreeNode currentNode, String name) throws NotUniqueSiblingException {
        if (currentNode.name.equalsIgnoreCase(name))          // throws exception if child already added with the same name
            throw new NotUniqueSiblingException();
        if (currentNode.sibling != null)                      // If the sibling of the current node is not null recall method
            currentNode = findFurthestSibling(currentNode.sibling, name);

        return currentNode;         // Returns furthest sibling node
    }

    private StringBuilder getChildren(FamilyTreeNode currentNode) {
        StringBuilder childrenDetails = new StringBuilder();
        childrenDetails.append("\n\t" + currentNode.name);                 // adds current node's name to details
        if (currentNode.sibling != null)
            childrenDetails.append(getChildren(currentNode.sibling));       // Recalls self if current node's sibling is not null

        return childrenDetails;             // Returns all children details
    }

    private StringBuilder getFamilyTreeDetails(FamilyTreeNode currentNode) {
        StringBuilder familyDetails = new StringBuilder();
        familyDetails.append(currentNode.name + " partner " + currentNode.partner.name);        // Appends current node and partner names
        if (currentNode.firstChild != null)
            familyDetails.append(getChildren(currentNode.firstChild));      // if first child not null get all children
        else
            familyDetails.append("\n\tNo Children");                        // if no children add no children to string

        return familyDetails;       // return family details
    }

    public String toString() {
        StringBuilder familyDetails = new StringBuilder();
        familyDetails.append(getFamilyTreeDetails(this.ancestor));                              // get details for ancestor
        familyDetails.append("\n" + getFamilyTreeDetails(this.ancestor.partner) + "\n");        // get details for ancestor's partner
        return familyDetails.toString();        // return all details as a string
    }



    // Code for junit tests
    public boolean contains(String name) {
        return contains(name, this.ancestor);                   // returns result of checking all of the family tree
    }

    private boolean contains(String name, FamilyTreeNode currentNode) {
        if (currentNode.name.equalsIgnoreCase(name))                                // if current node is the target return true
            return true;
        if (currentNode.partner != null && currentNode.partner.name.equalsIgnoreCase(name))        // if current nodes partner is the target return true
            return true;
        boolean result = false;
        if (currentNode.firstChild != null)                             // if current nodes first child is not null
            result = contains(name, currentNode.firstChild);            // method self calls with the first child
        if (!result && currentNode.sibling != null)                     // if current nodes siblings is not null
            result = contains(name, currentNode.sibling);               // method self calls with current nodes sibling

        return result;          // returns result
    }

}
