import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN{
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator obn = new OffByN(5);

    @Test
    public void testOffByN(){
        assertTrue(obn.equalChars('a', 'f'));  // true
    }
    // Your tests go here.
}