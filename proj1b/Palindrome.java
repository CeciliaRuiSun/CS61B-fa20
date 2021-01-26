public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        //return a Deque where the characters appear in the same order as in the String

        int len = word.length();
        LinkedListDeque<Character> wordList = new LinkedListDeque<Character>();

        for(int i = len - 1; i >= 0; i --){
            wordList.addFirst(word.charAt(i));
        }
        return wordList;
    }

    public boolean isPalindrome(String word){
        int len = word.length();
        if(word == null){
            return false;
        }

        Deque d = wordToDeque(word);
        String reverse = "";

        for(int i = 0;i < len;i ++){
            reverse += d.removeLast();
        }

        if(!word.equals(reverse)){
            return false;
        }
        return true;

    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if(word == null || cc == null){
            return isPalindrome(word);
        }

        Deque<Character> d = wordToDeque(word);
        return helper(d,cc);
    }

    public boolean helper(Deque<Character> d,CharacterComparator cc){
        if(d.size() == 0 || d.size() == 1){
            return true;
        }

        Character head = d.removeFirst();
        Character tail = d.removeLast();

        return cc.equalChars(head,tail) && helper(d, cc);
    }

}
