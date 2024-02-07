package parserexemple;

public enum Categorie{
    Identifier,
    Mot_Cle,
    Type,
    integer_literal,
    float_literal,
    boolean_literal,
    OPAff,
    oprel,
    oplog,
    opar,
    Debut,
    Fin,
    ParOuv,
    ParFer,
    v,
    PV,
    EOF,
    NUL,

    ;




/*La m�thode java string toLowerCase () renvoie la cha�ne en minuscules. En d'autres termes,
il convertit tous les caract�res de la cha�ne en minuscules. */


    public String toString() {
        return this.name().toLowerCase();
    }
    /*
La m�thode equalsIgnoreCase() compare deux cha�nes en ignorant les diff�rences entre
minuscules et majuscules et renvoie � true � si les cha�nes sont �gales sinon renvoie � false �.
*/
    public static Categorie toCategorie(String s) {
        for(Categorie c:Categorie.values())
            if(c.toString().equalsIgnoreCase(s))
                return c;
        return null;
    }


    /*La m�thode ordinal() permet de retrouver le num�ro d'ordre d'un �l�ment �num�r�,
     dans la liste de tous les �l�ments d'une �num�ration. Le premier num�ro d'ordre est 0.
    */
    /*public boolean estTerminal(int MIN, int MAX) {
        return ordinal()>=MIN && ordinal()<=MAX;
    }

    public boolean estNonTerminal(int MAX) {
        return ordinal()>MAX;
    }*/
}




