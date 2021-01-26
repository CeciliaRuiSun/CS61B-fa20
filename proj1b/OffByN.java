public class OffByN implements CharacterComparator{
    int n;
    public OffByN(int x){
        n = x;
    }
    @Override
    public boolean equalChars(char x, char y){
        if(Math.abs(x - y) == n){
            return true;
        } else {
            return false;
        }

    }
}
