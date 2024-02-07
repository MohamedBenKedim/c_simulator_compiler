package parserexemple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;




public class Scanner {
    public static ArrayList<Character> fluxCaracteres;
    public ArrayList<String> mot_cle= new ArrayList<String>(Arrays.asList("if","else","for","while", "return"));
    public ArrayList<String> type= new ArrayList<String>(Arrays.asList("int","float","bool"));
    public ArrayList<String> boolwords= new ArrayList<String>(Arrays.asList("true","false"));
    private int indiceCourant;
    private char caractereCourant;
    private boolean eof;

    public Scanner() {
        this("");
    }

    public Scanner(String nomFich) {
        BufferedReader f=null;
        int car=0;
        fluxCaracteres=new ArrayList<Character>();
        indiceCourant=0;
        eof=false;
        try {
            f=new BufferedReader(new FileReader(nomFich));
        }
        catch(IOException e) {
            System.out.println("taper votre texte ci-dessous (ctrl+z pour finir)");
            f=new BufferedReader(new InputStreamReader(System.in));
        }

        try {
            while((car=f.read())!=-1)
                fluxCaracteres.add((char)car);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void caractereSuivant() {
        if(indiceCourant<fluxCaracteres.size())
            caractereCourant=fluxCaracteres.get(indiceCourant++);
        else
            eof=true;
    }

    public void reculer() {
        if(indiceCourant>0)
            indiceCourant--;
    }

    public UniteLexicale lexemeSuivant() {
        caractereSuivant();

        while(eof || Character.isWhitespace(caractereCourant)) {
            if (eof)
                return new UniteLexicale(Categorie.EOF, "");
            caractereSuivant();
        }

        if(Character.isLetter(caractereCourant))
            return getID();

       else if(Character.isDigit(caractereCourant))
            return getNombre();

        else if(caractereCourant==':')
            return getOPPAff();

        else if(caractereCourant==';')
            return new UniteLexicale(Categorie.PV, ";");

        else if(caractereCourant=='<' || caractereCourant=='>' ||caractereCourant=='=')
            return getOPPRel();
        else if(caractereCourant=='+'|| caractereCourant=='-' || caractereCourant=='*' || caractereCourant=='/')
            return getOppArith();
        else if(caractereCourant=='&'|| caractereCourant=='|' || caractereCourant=='!'  )
            return getOppLog();
        else if(caractereCourant=='{')
            return new UniteLexicale(Categorie.Debut,"{");
        else if(caractereCourant=='}')
            return new UniteLexicale(Categorie.Fin,"}");
        else if(caractereCourant=='(')
            return new UniteLexicale(Categorie.ParOuv,"(");
        else if(caractereCourant==')')
            return new UniteLexicale(Categorie.ParFer,")");
        else if(caractereCourant==',')
            return new UniteLexicale(Categorie.v,",");
        else
            return new UniteLexicale(Categorie.NUL,caractereCourant+" mal place");
    }

    public UniteLexicale getID() {
        int etat=0;
        StringBuffer sb=new StringBuffer();
        while(true) {
            switch(etat) {
                case 0 : etat=1;
                    sb.append(caractereCourant);
                    break;
                case 1 : caractereSuivant();
                    if(eof)
                        etat=3;
                    else
                    if(Character.isLetterOrDigit(caractereCourant))
                        sb.append(caractereCourant);
                    else
                        etat=2;
                    break;
                case 2 : reculer();
                    if (mot_cle.contains(sb.toString())){
                        return new UniteLexicale(Categorie.Mot_Cle, sb.toString());
                    }
                    else if(type.contains(sb.toString())){
                        return new UniteLexicale(Categorie.Type, sb.toString());
                    }
                    else if(boolwords.contains(sb.toString())){
                        return new UniteLexicale(Categorie.boolean_literal, sb.toString());
                    }
                    else{
                        return new UniteLexicale(Categorie.Identifier, sb.toString());
                    }
                    case 3 :
                        if (mot_cle.contains(sb.toString())){
                            return new UniteLexicale(Categorie.Mot_Cle, sb.toString());
                        }
                        else if(type.contains(sb.toString())){
                            return new UniteLexicale(Categorie.Type, sb.toString());
                        }
                        else if(boolwords.contains(sb.toString())){
                            return new UniteLexicale(Categorie.boolean_literal, sb.toString());
                        }
                        else{
                            return new UniteLexicale(Categorie.Identifier, sb.toString());
                        }
                default: return new UniteLexicale(Categorie.NUL,caractereCourant+" mal place");

            }
        }
    }

    public UniteLexicale getNombre() {
        int etat=0;
        StringBuffer sb=new StringBuffer();
        while(true) {
            switch(etat) {
                case 0 : etat=1;
                    sb.append(caractereCourant);
                    break;
                case 1 : caractereSuivant();
                    if(eof)
                        etat=3;
                    else
                    if(Character.isDigit(caractereCourant))
                        sb.append(caractereCourant);
                    else
                    if (caractereCourant=='.')
                    {
                        sb.append(caractereCourant);
                        etat=4;
                    }
                    else
                        etat=2;
                    break;
                case 2 :
                    if(Character.isWhitespace(caractereCourant)){
                        reculer();
                        return new UniteLexicale(Categorie.integer_literal, sb.toString());
                    }
                    return new UniteLexicale(Categorie.NUL,caractereCourant+" erreur place");


                case 3 :
                    return new UniteLexicale(Categorie.integer_literal, sb.toString());

                case 4 :
                    caractereSuivant();
                    if(eof)
                        etat=5;
                    else
                    if(Character.isDigit(caractereCourant))
                        sb.append(caractereCourant);
                    else
                        etat=6;
                    break;

                case 5 :
                    return new UniteLexicale(Categorie.float_literal, sb.toString());

                case 6 :
                {
                    reculer();
                    return new UniteLexicale(Categorie.float_literal,sb.toString());
                }
                default: return new UniteLexicale(Categorie.NUL,caractereCourant+" mal place");
            }
        }
    }







    public UniteLexicale getOPPAff() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0:
                    if (eof)
                        break;
                    else if (caractereCourant == ':') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 1;

                    } else
                        break;

                case 1:
                    if (eof)
                        break;
                    else if (caractereCourant == '=') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 2;

                    } else
                        break;

                case 2:
                    if (eof)
                        etat = 3;
                    else
                        etat = 5;
                case 3:

                    return new UniteLexicale(Categorie.OPAff, sb.toString());
                case 4:
                    reculer();
                    return new UniteLexicale(Categorie.OPAff, sb.toString());

                default: return new UniteLexicale(Categorie.NUL,caractereCourant+" mal place");

            }

        }
    }


    public UniteLexicale getOPPRel() {
        int etat = 0;
        StringBuffer sb = new StringBuffer();
        while (true) {
            switch (etat) {
                case 0 :
                    if (caractereCourant == '=') {
                        sb.append(caractereCourant);
                        etat=1;}

                    else if (caractereCourant == '>') {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat=2;}

                    else {
                        sb.append(caractereCourant);
                        caractereSuivant();
                        etat = 5;}
                    break;

                case 1 :
                    return new UniteLexicale(Categorie.oprel, "=");

                case 2:
                    if(caractereCourant == '=')
                        etat=3;
                    else
                        etat=4;
                case 3:
                    return new UniteLexicale(Categorie.oprel, ">");

                case 4:
                    reculer();
                    return new UniteLexicale(Categorie.oprel, ">");

                case 5 :
                    if(caractereCourant == '=')

                    {sb.append(caractereCourant);
                        etat=6;}

                    else if (caractereCourant == '>')
                    {
                        sb.append(caractereCourant);
                        etat=7;
                    }
                    else
                        etat=8;
                    break;
                case 6 :
                    return new UniteLexicale(Categorie.oprel, "<");
                case 7 :
                    return new UniteLexicale(Categorie.oprel, "<>");
                case 8 :
                    reculer();
                    return new UniteLexicale(Categorie.oprel, "<");
                default: return new UniteLexicale(Categorie.NUL,caractereCourant+" mal place");
            }}}

    private UniteLexicale getOppLog() {
        int etat=0;
        StringBuffer sb=new StringBuffer();
        while(true) {
            switch(etat)
            {
                case 0 :
                    sb.append(caractereCourant);
                    caractereSuivant();
                    etat=1;

                case 1 :
                    if(eof || Character.isWhitespace(caractereCourant)){
                        {	reculer();
                            if (sb.charAt(0)=='&')
                                return new UniteLexicale(Categorie.oplog, "And");
                            else if (sb.charAt(0)=='|')
                                return new UniteLexicale(Categorie.oplog, "Or");
                            else if (sb.charAt(0)=='!')
                                return new UniteLexicale(Categorie.oplog, "Not");
                        }

                    }
                default: return new UniteLexicale(Categorie.NUL,caractereCourant+" mal place");
            }}}

    private UniteLexicale getOppArith() {
        int etat=0;
        StringBuffer sb=new StringBuffer();
        while(true) {
            switch(etat)
            {
                case 0 :
                    sb.append(caractereCourant);
                    caractereSuivant();
                    etat=1;

                case 1 :
                    if(eof || Character.isWhitespace(caractereCourant)){
                        {	reculer();
                            if (sb.charAt(0)=='+')
                                return new UniteLexicale(Categorie.opar, "+");
                            else if (sb.charAt(0)=='-')
                                return new UniteLexicale(Categorie.opar, "-");
                            else if (sb.charAt(0)=='*')
                                return new UniteLexicale(Categorie.opar, "*");
                            else if (sb.charAt(0)=='/')
                                return new UniteLexicale(Categorie.opar, "/");
                        }
                    }
                default: return new UniteLexicale(Categorie.NUL,caractereCourant+" mal place");
            }}}

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return fluxCaracteres.toString();
    }


}