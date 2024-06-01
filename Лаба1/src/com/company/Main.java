package com.company;


import java.io.File;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;



public class Main {

    private  String input ;
    private static char [] alphabet = new char[]{
            'а','б','в','г','д','е','ё','ж',
            'з','и','й','к','л','м','н','о',
            'п','р','с','т','у','ф','х','ц',
            'ч','ш','щ','ъ','ы','ь','э','ю','я'
    };

    public  static ArrayList<Table>frequency_table_part = new ArrayList<>();
    public  static ArrayList<Table>frequency_table_full = new ArrayList<>();





    public static char[]  Cesar(String input){
        input = input.toLowerCase();
        char [] array = input.toCharArray();
        int key=5;

        for(int i = 0;i<array.length;i++){

            for(int j = 0;j<alphabet.length;j++){

                if(alphabet[j]==array[i]){
                    j+=key;
                    if(j>32){
                        j-=33;
                    }
                    array[i] = alphabet[j];
                    break;
                }

            }

        }

        return array;
    }

    public static ArrayList<Table> frequency_analysis(char[] array){

        ArrayList<Table> frequency_table = new ArrayList<>();

        int probability = 0;
        for (int i = 0;i< alphabet.length;i++){
            for(int j = 0;j<array.length;j++){
                if(alphabet[i]==array[j]){
                    probability++;
                }
            }

            frequency_table.add(new Table(alphabet[i],probability%array.length));

            probability=0;
        }




        return  sort(frequency_table);
    }

    public static ArrayList<Table> sort(ArrayList<Table> table_sort){
        boolean sorted = false;
        Table temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < table_sort.size() - 1; i++) {
                if (table_sort.get(i).getValue() < table_sort.get(i+1).getValue()) {

                    temp = table_sort.get(i);
                    table_sort.set(i,table_sort.get(i+1));
                    table_sort.set(i+1,temp);

                    sorted = false;
                }
            }
        }
        return  table_sort;
    }


    public static String readFile(String address)
    {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileReader fr = new FileReader(address);
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine())
                stringBuilder.append(scan.nextLine()).append("\n");

            fr.close();
        } catch (Exception ignored) { }

        return stringBuilder.toString();
    }

    public static void writeFile(String data) {
        File file = new File("C:\\Users\\Asus\\Desktop\\уник\\7 сем\\Защита инф\\Лаба1\\src\\com\\company\\decrypt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        public static void main (String[]args){

            frequency_table_part = frequency_analysis(Cesar(readFile("C:\\Users\\Asus\\Desktop\\уник\\7 сем\\Защита инф\\Лаба1\\src\\com\\company\\WarAndPeace(part)")));
            frequency_table_full = frequency_analysis(readFile("C:\\Users\\Asus\\Desktop\\уник\\7 сем\\Защита инф\\Лаба1\\src\\com\\company\\WarAndPeace(All)").toCharArray());

            char[] array = Cesar(readFile("C:\\Users\\Asus\\Desktop\\уник\\7 сем\\Защита инф\\Лаба1\\src\\com\\company\\WarAndPeace(part)"));

            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < frequency_table_part.size(); j++) {
                    if (array[i] == frequency_table_part.get(j).getLetter()) {
                        array[i] = frequency_table_full.get(j).getLetter();
                        break;
                    }

                }

            }

            String s="";
            for (char c : array) {
                s+=c;
            }
            writeFile(s);

        }

    }



