package com.example.Main;

import java.util.*;

/**
 * Created by phirayu on 13/8/2558.
 */
public class Main {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        //String text = input.nextLine();
        //String text = "1+2*4/5-7+3/6";

        String text = "(((1+1)*2)*4)*3+10";

        System.out.println("input :"+text);
        Main main = new Main();

        main.infixToPostFix(text,0);
        //[1, 1, 2, *, +]
        //System.out.println("postfix :124*5/+736/+-");
        System.out.println("postfix : " + main.resultList.toString());
        Double answer=main.calculate(main.resultList);
        if(answer == null) {
            System.out.println("answer : Error" );
        }else{
            System.out.println("answer : "+answer);
        }




    }


    List<String> resultList = new ArrayList<String>();
    private Double calculate(List<String> postfixs){

        String operation = "+-*/";
        List<Integer> indexOperation = new ArrayList<Integer>();

        for(int i=0;i< postfixs.size();i++){
            for(int j=0;j<operation.length();j++){
                if(postfixs.get(i).equals(operation.substring(j,j+1))){
                    indexOperation.add(i);
                    break;
                }
            }
        }

        int count =0;
        try {
            for (Integer i : indexOperation) {
                int x = i - (count * 2);
                double z = 0;
                if (postfixs.get(x).equals("+")) {
                    z = Double.parseDouble(postfixs.get(x - 2)) + Double.parseDouble(postfixs.get(x - 1));

                } else if (postfixs.get(x).equals("-")) {
                    z = Double.parseDouble(postfixs.get(x - 2)) - Double.parseDouble(postfixs.get(x - 1));

                } else if (postfixs.get(x).equals("*")) {
                    z = Double.parseDouble(postfixs.get(x - 2)) * Double.parseDouble(postfixs.get(x - 1));

                } else if (postfixs.get(x).equals("/")) {
                    z = Double.parseDouble(postfixs.get(x - 2)) / Double.parseDouble(postfixs.get(x - 1));

                }
                List<String> temp = new ArrayList<String>();

                for (int xx = 0; xx < postfixs.size(); xx++) {
                    if (xx == x) {
                        temp.add(z + "");
                    } else if (xx == x - 1 || xx == x - 2) {

                    } else {
                        temp.add(postfixs.get(xx));
                    }
                }
                postfixs = temp;
                count += 1;

            }

            return Double.parseDouble(postfixs.get(0));
        }catch (Exception e){
            return null;
        }

    }



    private int infixToPostFix(String text,int count){
        String operation = "-+/*";
        List<String> stackOperation = new ArrayList<String>();
        //System.out.println(text);
        String result = "";
        //System.out.println(text);
        Boolean character ;
        for(int j=0;j<text.length();j++){

            char a=text.charAt(j);
          character=true;


            for(char oper:operation.toCharArray()){
                if(a == oper){
                    character=false;
                    if(result.length()>0) {
                        resultList.add(result);
                    }
                    result="";
                    if(stackOperation.size()>0){
                        while(stackOperation.size()>0&&operation.indexOf(stackOperation.get(stackOperation.size()-1))>operation.indexOf(a)){

                            //result=result+strings.get(strings.size()-1);
                            resultList.add(stackOperation.get(stackOperation.size()-1));
                            stackOperation.remove(stackOperation.get(stackOperation.size()-1));
                        }
                        stackOperation.add(a+"");
                    }else{
                        stackOperation.add(a+"");
                    }
                }
            }
            if(a == '('){
                //character=false;
                j=infixToPostFix(text.substring(count+1),count);
                count=j;


            }else if(a == ')'){
                //character=false;

                if(result.length()>0) {resultList.add(result);}
                for (int i=stackOperation.size()-1;i>=0;i--){
                    resultList.add(stackOperation.get(i));
                }
                //System.out.println(text.substring(count));
                return count+1;
            }
            else if(character){
                result=result+a;
            }
            count+=1;


        }
        if(result.length()>0) {
            resultList.add(result);
        }
        for (int i=stackOperation.size()-1;i>=0;i--){
            resultList.add(stackOperation.get(i));
        }
        return count;
    }
}
