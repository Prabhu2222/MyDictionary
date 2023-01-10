package com.example.mydictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Dictionary {

    HashMap<String,String> dictionary;
    public Dictionary() throws Exception {
        this.dictionary=new HashMap<>();
        loadWords();
    }

    public String getMeaning(String word) {
        try{
        for(String key:dictionary.keySet()){
            String lowerCase=word.toLowerCase();
            if(key.equals(lowerCase)){
                String meaning=dictionary.get(key);
                String meaningarr[]=meaning.split("[.][ \"][,][\"]");
                if(meaningarr.length==1)
                    return key+" :-"+"\n"+"-> "+meaning;
                else
                {
                StringBuilder toBeReturned=new StringBuilder();
                for(int i=0;i<meaningarr.length-1;i++){
                    toBeReturned.append("-> "+meaningarr[i]+"\n");
                }
                toBeReturned.append("-> "+meaningarr[meaningarr.length-1]);
                return key+" :-"+"\n"+toBeReturned.toString();

                }

            }

        }}catch(Exception e){
            System.out.println("word can be null in dictionary class getMeaning() method");
        }
        return null;
    }
    public void loadWords() throws Exception{
        File f=new File("C:\\AccioProject\\MY BUILD2\\MyDictionary\\src\\main\\wordDictionary\\dictionary.txt");
        BufferedReader br=new BufferedReader(new FileReader(f));
        String line=br.readLine();
        while(line!=null){
            String arr[]=line.split("[*]{4}");
            String words=arr[0];
            String meaning=arr[1];
            dictionary.put(words,meaning);
            line=br.readLine();
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {
        Dictionary d=new Dictionary();
        String ans=d.getMeaning("bird");
        System.out.println(ans);
    }

}
