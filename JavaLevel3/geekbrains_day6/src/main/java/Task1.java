public class Task1 {

    int[] method1(int[] array){
        if (array.length == 0) return null;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 4) break;

            if (i == array.length - 1) {
                throw new RuntimeException("Woo-hoo");
            }
        }

        int[] newArray = new int[0];

        for (int i = array.length - 1; i >= 0 ; i--) {
            if (array[i] == 4) { ;
                newArray = new int[array.length - i - 1];
                for (int j = 0; j < newArray.length; j++) {
                    newArray[j] = array[i + j + 1];
                }
            break;
            }
        }
        return newArray;
    }
}
