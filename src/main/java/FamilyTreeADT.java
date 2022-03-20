public interface FamilyTreeADT {
    public class NotUniqueSiblingException extends RuntimeException {
    }

    public abstract void addChild(String name) throws NotUniqueSiblingException;
}
