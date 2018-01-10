public class Task2 {
    public boolean method2(int[] array){

        if (array.length == 0) return false;

        int one = 0;
        int four = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] != 1 && array[i] != 4) return false;
            if (array[i] == 1) one++;
            if (array[i] == 4) four++;
        }

        return one != 0 && four != 0;
    }
}
