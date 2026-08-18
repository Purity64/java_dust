package src;

import java.util.ArrayList;

public class Calculate {
    public  static ArrayList<ArrayList> calculate_area(int x , int y){

        ArrayList<String> arr , arr2 , arr3 ;
        arr = new ArrayList<>();
        arr2 = new ArrayList<>();
        arr3 = new ArrayList<>();
        ArrayList<ArrayList> maiArrayList = new ArrayList<>();
        arr.add(x + "," + y);
        arr.add( x + "," + ( ChackMin(y, 1)));
        arr.add( x + "," +  (ChackMax(y, 1, 40)));

        arr2.add(x + "," + (ChackMin(y, 1)));
        arr2.add( x + "," + (ChackMax(y, 1, 40)));

        int initialSize = arr.size(); 
        for (int i = 0; i < initialSize; i++) {
            int temparr[] = spiltBox(arr.get(i));
            int temparrX = temparr[0];
            int temparrY = temparr[1];

            arr2.add((ChackMax(temparrX, 1, 20)) + "," + temparrY);
            arr2.add((ChackMin(temparrX, 1)) + "," + temparrY);
        }

        for (int i = 0; i < initialSize; i++) {
            String full_location = arr.get(i);
            int temparr[] = spiltBox(full_location);
            int temp_x = temparr[0];
            int temp_y = temparr[1];
            if (i == 0) {
                arr3.add( ( ChackMin(temp_x,  2)) + "," + temp_y);
                arr3.add( ( ChackMax(temp_x, 2, 20) ) + "," + temp_y);

            }else {
              
                ArrayList<String> tempArr = new ArrayList<>();
                tempArr.add(full_location);
                tempArr.add(( ChackMin(temp_x, 1)) + "," + temp_y);
                tempArr.add(( ChackMin(temp_x,  2)) + "," + temp_y);
                tempArr.add(( ChackMax(temp_x , 1 , 20)) + "," + temp_y);
                tempArr.add(( ChackMax(temp_x, 2, 20) ) + "," + temp_y);

                    for (int j = 0; j < tempArr.size(); j++) {
                        int temparrSplit[] = spiltBox(tempArr.get(j));
                        int tempArrX = temparrSplit[0];
                        int tempArrY = temparrSplit[1];

                        if (i == 1) {
                            arr3.add(tempArrX + "," + (ChackMin(tempArrY, 1)));
                        }else{
                             arr3.add(tempArrX + "," + (ChackMax(tempArrY, 1, 40)));
                        }
                    }

                    arr3.add(( ChackMin(temp_x, 2) ) + "," + temp_y );
                    arr3.add(( ChackMax(temp_x, 2, 20)) + "," + temp_y);

                
            }
        }
        maiArrayList.add(arr);
        maiArrayList.add(arr2);
        maiArrayList.add(arr3);
       return maiArrayList;

    }

    private static int[] spiltBox(String text){
        String[] c = text.split(",");
        int[] result = new int[2];

        result[0] = Integer.parseInt(c[0]);
        result[1] = Integer.parseInt(c[1]);

        return result;
    }

    private static int ChackMin(int main , int num){
        int result = main - num;
        if (result < 0) {
            return -1;
        }

        return result;
    }

    private static int ChackMax(int main , int num , int max){
        int result = main + num;
        if (result > max) {
            return -1;
        }

        return result;
    }
}
