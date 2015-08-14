package com.example.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by phirayu on 13/8/2558.
 */
public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        //String text = input.nextLine();
        String text = "1+2*4/5-7+3/6";
        System.out.println("input :"+text);
        Main main = new Main();
        String postfix = main.infixToPostFix(text);
        System.out.println("postfix :"+postfix);
        System.out.println("answer : "+main.calculate(postfix));




    }

    private Double calculate(String postfix){

        String operation = "+-*/";
        List<Integer> integers = new ArrayList<Integer>();

        for(int i=0;i<postfix.length();i++){
            for(int j=0;j<operation.length();j++){
                if(postfix.charAt(i)==(operation.charAt(j))){
                    integers.add(i);
                    break;
                }
            }
        }
        String[] postArray = postfix.split("");
        List<String> postfixs = new ArrayList<String>(Arrays.asList(postArray));
        postfixs.remove("");
        int count =0;
        for(Integer i:integers){
            int x= i-(count*2);
            double z=0;
            if (postfixs.get(x).equals("+")){
                z = Double.parseDouble(postfixs.get(x - 2))+Double.parseDouble(postfixs.get(x - 1));

            }else if(postfixs.get(x).equals("-")){
                z = Double.parseDouble(postfixs.get(x - 2))-Double.parseDouble(postfixs.get(x - 1));

            }else if(postfixs.get(x).equals("*")){
                z = Double.parseDouble(postfixs.get(x - 2))*Double.parseDouble(postfixs.get(x - 1));

            }else if(postfixs.get(x).equals("/")){
                z = Double.parseDouble(postfixs.get(x - 2))/Double.parseDouble(postfixs.get(x - 1));

            }
            List<String> temp = new ArrayList<String>();

            for(int xx=0;xx<postfixs.size();xx++){
                if(xx==x){
                    temp.add(z+"");
                }else if(xx == x-1 || xx== x-2){

                }else{
                    temp.add(postfixs.get(xx));
                }
            }
            postfixs=temp;
            count+=1;

        }

        return Double.parseDouble(postfixs.get(0));

    }

    private String infixToPostFix(String text){
        String operation = "-+/*";
        List<String> strings = new ArrayList<String>();
        String result = "";
        Boolean character ;
        for(char a:text.toCharArray()){
            character=true;
            for(char oper:operation.toCharArray()){
                if(a == oper){
                    character=false;
                    if(strings.size()>0){
                        while(strings.size()>0&&operation.indexOf(strings.get(strings.size()-1))>operation.indexOf(a)){
                            result=result+strings.get(strings.size()-1);
                            strings.remove(strings.get(strings.size()-1));
                        }
                        strings.add(a+"");
                    }else{
                        strings.add(a+"");
                    }
                }
            }
            if(character){
                result=result+a;
            }
        }
        for (int i=strings.size()-1;i>=0;i--){
            result=result+strings.get(i);
        }
        return result;
    }
}
