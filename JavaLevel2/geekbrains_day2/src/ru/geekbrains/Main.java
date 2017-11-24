package ru.geekbrains;

public class Main {

    public static void main(String[] args) {
        String[][] s = new String[][]{
                {"1", "2", "3", "b"},
                {"1", "2", "3", "", "2"},
                {"1", "3", "4"},
                {"1", "2", "3", "4"}};

        Main main = new Main();

        boolean flag = true;
        for(;flag;){
            flag = false;
            try{
                main.arrayProcessor(s);
            }
            catch (MyArraySizeException e){
                System.out.println("Size was fixed by String[4][4] with defaults values.");
                s = new String[][]{
                        {"1", "2", "3", "4"},
                        {"1", "2", "", "4"},
                        {"1", "", "3", "4"},
                        {"", "2", "3", "4"}};
                flag = true;
            }
            catch (MyArrayDataException e){
                System.out.println("Value at [" + e.getI() + "][" + e.getJ() + "] was fixed by 0.");
                s[e.getI()][e.getJ()] = "0";
                flag = true;
            }
        }
    }

    private void arrayProcessor(String[][] strings) throws MyArraySizeException, MyArrayDataException {
        if (strings.length != 4) throw new MyArraySizeException("ROWs count is not 4");
        for (int i = 0; i < strings.length; i ++) {
            for (int j = 0; j < strings[i].length; j++) {
                if (strings[i].length != 4) throw new MyArraySizeException("COL " + i + " size is not 4");
            }
        }

        int tmp = 0;

        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                try {
                    tmp += Integer.parseInt(strings[i][j]);
                }
                catch (NumberFormatException e){
                    throw  new MyArrayDataException("Exception data in row " + i + " col " + j, i, j);
                }
            }
        }
        System.out.println("sum = " + tmp);
    }
}
