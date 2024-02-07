package compilateurnewversion;

import java.util.ArrayList;

import parserexemple.Scanner;
import parserexemple.UniteLexicale;

public class Main {

    public static void main( String[] args) {
    	String path = ControlerFx.file.toURI().toString().substring(6);
    	System.out.println(path);
    	Scanner anaLex=new Scanner(path);


      System.out.println("remplissage du tableaullll");
      for( int i=0; i<anaLex.fluxCaracteres.size();i++)

    	  System.out.println(" " +	anaLex.fluxCaracteres.get(i));
    	        System.out.println("***********************Analyse lexical*************************");
    	        ArrayList<String> tablex= new ArrayList<String>();
    	        ArrayList<String> tabcat= new ArrayList<String>();
    	        UniteLexicale ul=null;

    	        do{
    	            ul=anaLex.lexemeSuivant();
    	            tablex.add(ul.getLexeme());
    	            tabcat.add(ul.getCat());
    	            if (ul.getCat().equals("nul"))
    	                break;
    	        }while(tablex.get(tablex.size()-1) != "");

    	        int taille = tablex.size();
    	        String[] tabfinal= new String[taille];

    	        for( int i=0; i<taille-1;i++){
    	            if(anaLex.mot_cle.contains(tablex.get(i)) || tablex.get(i).equals("(") || tablex.get(i).equals(")") || tablex.get(i).equals("{") || tablex.get(i).equals("}")|| tablex.get(i).equals(",")|| tablex.get(i).equals(";")){
    	                tabfinal[i]=(tablex.get(i));
    	            }
    	            else
    	                tabfinal[i]=tabcat.get(i);
    	        }
    	        tabfinal[taille-1] = "$";
    	        for( int j=0; j<taille-1;j++)
    	            System.out.print(tabfinal[j]+" ");

    	        System.out.println("***********************Analyse Syntaxique*************************");
    	        if (ul.getCat().equals("nul")){
    	            System.out.println("Analyse lexicale erronnée ");
    	        }else{
    	            parsernew test22 = new parsernew();
    	            test22.analyzeSLnew(tabfinal);
    	        }




    	    }
    	}

