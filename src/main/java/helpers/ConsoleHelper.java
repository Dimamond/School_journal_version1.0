package helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void print(String message){
        System.out.print(message);
    }

    public static void println(String message){
        System.out.println(message);
    }

    public static String readString(){
        while (true){
            String s = "";
            try {
                s = reader.readLine();
                return s;
            }catch (IOException e){
            }
        }
    }

    public static void Close(){
        try {
            reader.close();
        } catch (IOException e) {

        }
    }
}
