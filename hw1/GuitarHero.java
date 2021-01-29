import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {

    public static void main(String[] args){
        int numOfKey = 37;
        GuitarString[] keys = new GuitarString[numOfKey];
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        for(int i = 0;i < numOfKey;i ++){
            double frequency = 440 * Math.pow(2, (i - 24)/12);
            keys[i] = new GuitarString(frequency);
        }
        GuitarString currentKey = keys[0];
        while (true) {

            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int pos = keyboard.indexOf(key);
                //zSystem.out.println(key + " " + pos);
                if(pos != -1){
                    currentKey = keys[pos];
                    currentKey.pluck();
                } else{
                    //do nothing
                }
            }
            double sample = currentKey.sample();
            //System.out.println("sample: " + sample);
            StdAudio.play(sample);
            currentKey.tic();
        }


    }
}
