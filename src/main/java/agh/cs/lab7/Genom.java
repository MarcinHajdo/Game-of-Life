package agh.cs.lab7;

import java.util.Random;

public class Genom {
    String genomGen(){
        String genom = "";
        Random generator = new Random();
        for (int i=0;i<32;i++){
            if(i==0){
                genom+=generator.nextInt(8);
            }
            else{
                genom+=" "+generator.nextInt(8);
            }
            //System.out.println(genom);
        }
        return genom;
    }
    String childGenom(String FromFather, String FromMother){
        return GenomHalf(FromFather)+" "+GenomHalf(FromMother);
    }
    String GenomHalf(String Parent){
        String[] parent=Parent.split(" ");
        String genom = "";
        Random generator = new Random();
        for (int i=0;i<16;i++){
            if(i==0){
                genom+=parent[generator.nextInt(16)];
            }
            else {
                genom += " " + parent[generator.nextInt(16)];
            }
        }
        return genom;
    }
}
