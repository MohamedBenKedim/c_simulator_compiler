package compilateurnewversion;


import java.util.Objects;
import java.util.Stack;

public class parsernew {


    public String[] LRGS = {"program -> statement_list",

            "statement_list -> statement",

            "statement_list -> statement ; statement_list",

            "statement -> expression",

            "statement -> variable_declaration",

            "statement -> function_declaration",

            "statement -> if_statement",

            "statement -> while ( expression ) block",

            "statement -> for ( expression ; logical_expression ; expression ) block",

            "statement -> return expression",

            "expression -> logical_expression",

            "expression -> identifier opaff expression",

            "logical_expression -> relational_expression",

            "logical_expression -> logical_expression oplog relational_expression",

            "relational_expression -> arith_expression",

            "relational_expression -> relational_expression oprel arith_expression",

            "arith_expression -> primary_expression",

            "arith_expression -> arith_expression opar primary_expression",

            "primary_expression -> identifier",

            "primary_expression -> literal",

            "primary_expression -> ( expression )",

            "variable_declaration -> type identifier",

            "function_declaration -> type identifier ( parameter_list ) block",

            "if_statement -> if ( expression ) block",

            "if_statement -> if ( expression ) block else block",

            "parameter_list -> parameter_declaration",

            "parameter_list -> parameter_declaration , parameter_list",

            "parameter_declaration -> type identifier",

            "block -> { statement_list }",

            "literal -> integer_literal",

            "literal -> float_literal",

            "literal -> boolean_literal"
    };


    public String[][] tableSLR = {
            {"Etat",";","while","(",")","for","return","identifier","opaff","oplog","oprel","opar","type","if","else",",","{","}","integer_literal","float_literal","boolean_literal","$","program","statement_list","statement","expression","logical_expression","relational_expression","arith_expression","primary_expression","variable_declaration","function_declaration","if_statement","parameter_list","parameter_declaration","block","literal",} ,
            {"0","err","s7","s18","err","s8","s9","s11","err","err","err","err","s12","s13","err","err","err","err","s19","s20","s21","err","err","1","2","3","10","14","15","16","4","5","6","err","err","err","17",} ,
            {"1","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","ACC","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"2","s22","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r1","err","err","err","r1","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"3","r3","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r3","err","err","err","r3","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"4","r4","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r4","err","err","err","r4","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"5","r5","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r5","err","err","err","r5","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"6","r6","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r6","err","err","err","r6","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"7","err","err","s23","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"8","err","err","s24","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"9","err","err","s18","err","err","err","s11","err","err","err","err","err","err","err","err","err","err","s19","s20","s21","err","err","err","err","25","10","14","15","16","err","err","err","err","err","err","17",} ,
            {"10","r10","err","err","r10","err","err","err","err","s26","err","err","err","err","err","err","err","r10","err","err","err","r10","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"11","r18","err","err","r18","err","err","err","s27","r18","r18","r18","err","err","err","err","err","r18","err","err","err","r18","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"12","err","err","err","err","err","err","s28","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"13","err","err","s29","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"14","r12","err","err","r12","err","err","err","err","r12","s30","err","err","err","err","err","err","r12","err","err","err","r12","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"15","r14","err","err","r14","err","err","err","err","r14","r14","s31","err","err","err","err","err","r14","err","err","err","r14","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"16","r16","err","err","r16","err","err","err","err","r16","r16","r16","err","err","err","err","err","r16","err","err","err","r16","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"17","r19","err","err","r19","err","err","err","err","r19","r19","r19","err","err","err","err","err","r19","err","err","err","r19","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"18","err","err","s18","err","err","err","s11","err","err","err","err","err","err","err","err","err","err","s19","s20","s21","err","err","err","err","32","10","14","15","16","err","err","err","err","err","err","17",} ,
            {"19","r29","err","err","r29","err","err","err","err","r29","r29","r29","err","err","err","err","err","r29","err","err","err","r29","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"20","r30","err","err","r30","err","err","err","err","r30","r30","r30","err","err","err","err","err","r30","err","err","err","r30","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"21","r31","err","err","r31","err","err","err","err","r31","r31","r31","err","err","err","err","err","r31","err","err","err","r31","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"22","err","s7","s18","err","s8","s9","s11","err","err","err","err","s12","s13","err","err","err","err","s19","s20","s21","err","err","33","2","3","10","14","15","16","4","5","6","err","err","err","17",} ,
            {"23","err","err","s18","err","err","err","s11","err","err","err","err","err","err","err","err","err","err","s19","s20","s21","err","err","err","err","34","10","14","15","16","err","err","err","err","err","err","17",} ,
            {"24","err","err","s18","err","err","err","s11","err","err","err","err","err","err","err","err","err","err","s19","s20","s21","err","err","err","err","35","10","14","15","16","err","err","err","err","err","err","17",} ,
            {"25","r9","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r9","err","err","err","r9","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"26","err","err","s18","err","err","err","s37","err","err","err","err","err","err","err","err","err","err","s19","s20","s21","err","err","err","err","err","err","36","15","16","err","err","err","err","err","err","17",} ,
            {"27","err","err","s18","err","err","err","s11","err","err","err","err","err","err","err","err","err","err","s19","s20","s21","err","err","err","err","38","10","14","15","16","err","err","err","err","err","err","17",} ,
            {"28","r21","err","s39","err","err","err","err","err","err","err","err","err","err","err","err","err","r21","err","err","err","r21","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"29","err","err","s18","err","err","err","s11","err","err","err","err","err","err","err","err","err","err","s19","s20","s21","err","err","err","err","40","10","14","15","16","err","err","err","err","err","err","17",} ,
            {"30","err","err","s18","err","err","err","s37","err","err","err","err","err","err","err","err","err","err","s19","s20","s21","err","err","err","err","err","err","err","41","16","err","err","err","err","err","err","17",} ,
            {"31","err","err","s18","err","err","err","s37","err","err","err","err","err","err","err","err","err","err","s19","s20","s21","err","err","err","err","err","err","err","err","42","err","err","err","err","err","err","17",} ,
            {"32","err","err","err","s43","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"33","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r2","err","err","err","r2","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"34","err","err","err","s44","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"35","s45","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"36","r13","err","err","r13","err","err","err","err","r13","s30","err","err","err","err","err","err","r13","err","err","err","r13","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"37","r18","err","err","r18","err","err","err","err","r18","r18","r18","err","err","err","err","err","r18","err","err","err","r18","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"38","r11","err","err","r11","err","err","err","err","err","err","err","err","err","err","err","err","r11","err","err","err","r11","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"39","err","err","err","err","err","err","err","err","err","err","err","s48","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","46","47","err","err",} ,
            {"40","err","err","err","s49","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"41","r15","err","err","r15","err","err","err","err","r15","r15","s31","err","err","err","err","err","r15","err","err","err","r15","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"42","r17","err","err","r17","err","err","err","err","r17","r17","r17","err","err","err","err","err","r17","err","err","err","r17","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"43","r20","err","err","r20","err","err","err","err","r20","r20","r20","err","err","err","err","err","r20","err","err","err","r20","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"44","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s51","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","50","err",} ,
            {"45","err","err","s18","err","err","err","s37","err","err","err","err","err","err","err","err","err","err","s19","s20","s21","err","err","err","err","err","52","14","15","16","err","err","err","err","err","err","17",} ,
            {"46","err","err","err","s53","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"47","err","err","err","r25","err","err","err","err","err","err","err","err","err","err","s54","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"48","err","err","err","err","err","err","s55","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"49","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s51","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","56","err",} ,
            {"50","r7","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r7","err","err","err","r7","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"51","err","s7","s18","err","s8","s9","s11","err","err","err","err","s12","s13","err","err","err","err","s19","s20","s21","err","err","57","2","3","10","14","15","16","4","5","6","err","err","err","17",} ,
            {"52","s58","err","err","err","err","err","err","err","s26","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"53","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s51","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","59","err",} ,
            {"54","err","err","err","err","err","err","err","err","err","err","err","s48","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","60","47","err","err",} ,
            {"55","err","err","err","r27","err","err","err","err","err","err","err","err","err","err","r27","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"56","r23","err","err","err","err","err","err","err","err","err","err","err","err","s61","err","err","r23","err","err","err","r23","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"57","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s62","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"58","err","err","s18","err","err","err","s11","err","err","err","err","err","err","err","err","err","err","s19","s20","s21","err","err","err","err","63","10","14","15","16","err","err","err","err","err","err","17",} ,
            {"59","r22","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r22","err","err","err","r22","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"60","err","err","err","r26","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"61","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s51","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","64","err",} ,
            {"62","r28","err","err","err","err","err","err","err","err","err","err","err","err","r28","err","err","r28","err","err","err","r28","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"63","err","err","err","s65","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"64","r24","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r24","err","err","err","r24","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,
            {"65","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","s51","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","66","err",} ,
            {"66","r8","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err","r8","err","err","err","r8","err","err","err","err","err","err","err","err","err","err","err","err","err","err","err",} ,};






    public Stack<String> stackState = new Stack<>();


    public Stack<String> analyse = new Stack<>();

    public Stack<String> stackSymbol = new Stack<>();

    public String strInput ;


    public String action = "";



    int index = 0;


    public void analyzeSLnew(String []tt) {

        action = "";
        index = 0;

        analyse.push("0");


        System.out.println("********pile     	    Entrée            Action***********");
        this.AfficherSLRnew(tt);

        while(index<tt.length)

        {


            //  String s = stackState.peek();

            String s = analyse.peek();
            String act=Action(s,tt[index]);
            System.out.println(act);
            if (act.charAt(0) == 's') {


                //stackState.push(Action(s, ch[index]).substring(1));
                //stackSymbol.push(ch[index]);

                analyse.push(tt[index]);
                analyse.push(act.substring(1));

                index++;
                action = "shift ";

                AfficherSLRnew(tt);
            }
            // Réduction
            else if (act.charAt(0) == 'r') {
                //
                String str = LRGS[Integer.parseInt(act.substring(1))];
                int pos= str.indexOf('>');

                String tabparties[]= str.split(" -> ");

                String Partiegauche=tabparties[0];

                String Partiedroite=tabparties[1];

                String tabtoken[]= Partiedroite.split(" ");
                int taillepile= tabtoken.length +tabtoken.length;

                for (int i = 0; i < taillepile; i++) {



                    analyse.pop();

                }
                String sommetpile = analyse.peek();
                analyse.push(Partiegauche);
                String tetesucc = analyse.peek();

                analyse.push(Action(sommetpile, Partiegauche));


                action = "reduce:" + str;
                AfficherSLRnew(tt);
            }
            //acceptation
            else if (act == "ACC")
            {
                System.out.println("analyze SLR successfully");
                break;}

            else
            //erreur
            {

                //System.out.println("texterreur"+Action(s,ch[index])+s+ch[index]+index);
                System.out.println("analyze SLR failled");
                break;
            }

        }

    }




    public String Action(String s, String a) {
        for (int i = 1; i <68 ; i++) {
            if (tableSLR[i][0].equals(s)) {
                for (int j = 1; j < tableSLR[i].length; j++) {
                    if (tableSLR[0][j].equals(a)) {
                        return tableSLR[i][j];
                    }
                }
            }
        }
        return "err";
    }

    public void AfficherSLRnew(String []tt) {
        //  SLR


        String ss= "--";
        String ss1= "--";
        int taillepile=analyse.size();
        int taillepilediv2= taillepile /2;
        for(int i=0;i<taillepilediv2;i++)
            ss=ss + "--" ;
        int tailleinput=tt.length;
        for(int i=0;i<tailleinput;i++)
            ss1=ss1 + "--" ;
        strInput="";
        for(int i=index; i<tt.length;i++)
            strInput= strInput+ tt[i];

        System.out.printf("%s", analyse + ss1);
        System.out.printf("%s", strInput+ ss);
        System.out.printf("%s", action);
        System.out.println();
    }

    public void ouput() {


        System.out.println("**********Tableau SLR¨********");

        for (int i = 0; i < 68 ; i++) {
            for (int j = 0; j <tableSLR[i].length; j++) {
                System.out.printf("%6s", tableSLR[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("**********Fin tableau SLR********");

    }

}