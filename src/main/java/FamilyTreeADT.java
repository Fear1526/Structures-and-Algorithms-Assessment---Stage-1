public interface FamilyTreeADT {
    public class NotUniqueSiblingException extends Exception {
    }

    public abstract void addChild(String name) throws NotUniqueSiblingException;
}
