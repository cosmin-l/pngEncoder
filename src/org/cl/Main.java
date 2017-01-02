package org.cl;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if(args.length !=3){
            outputArgsMessage();
            return;
        }
        EncodeInPng enc = new EncodeInPng();
        if (args[0].equalsIgnoreCase("encode")){
            enc.encodeAndWrite(new File(args[1]), new File(args[2]));
        }else if (args[0].equalsIgnoreCase("decode")){
            enc.decodeAndWrite(new File(args[1]), new File(args[2]));
        }else {
            outputArgsMessage();
            return;
        }
    }

    private static void outputArgsMessage(){
        System.out.println("Need 3 arguments: <operation> <source_file_name> <target_file_name> where <operation> is 'encode' or 'decode'.");
    }
}
