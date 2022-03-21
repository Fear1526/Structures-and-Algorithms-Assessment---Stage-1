import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import org.junit.jupiter.api.Test;

class FamilyTreeUnitTests {

    private FamilyTree underTest;


    @Test
    void shouldAddAncestorAndPartner(){
        // given
        underTest = new FamilyTree("James", "Mary");

        //when
        boolean expected = underTest.contains("James");
        boolean expected2 = underTest.contains("Mary");

        // then
        assertTrue(expected);
        assertTrue(expected2);
    }

    @Test
    void shouldAddChild() throws Exception{
        // given
        underTest = new FamilyTree("James", "Mary");
        underTest.addChild("John");
        underTest.addChild("Amy");
        underTest.addChild("Alan");

        // when
        boolean expected1 = underTest.contains("John");
        boolean expected2 = underTest.contains("Amy");
        boolean expected3 = underTest.contains("Alan");
        boolean expected4 = underTest.contains("test");

        // then
        assertTrue(expected1);
        assertTrue(expected2);
        assertTrue(expected3);
        assertFalse(expected4);
    }

    @Test
    void toStringShouldHaveNoChildren(){
        // given
        underTest = new FamilyTree("James", "Mary");

        // when
        boolean expected = underTest.toString().contains("No Children");

        // then
        assertTrue(expected);
    }

    @Test
    void toStringShouldHaveChildren() throws Exception{
        // given
        underTest = new FamilyTree("James", "Mary");
        underTest.addChild("John");
        underTest.addChild("Amy");
        underTest.addChild("Alan");

        // when
        boolean expected = !underTest.toString().contains("no children");

        // then
        assertTrue(expected);
    }

    @Test
    void shouldThrowNotUniqueException(){
        // given
        underTest = new FamilyTree("James", "Mary");
        underTest.addChild("Amy");

        // then
        assertThrows(FamilyTreeADT.NotUniqueSiblingException.class, () -> {
            underTest.addChild("amy");
        });
    }
}