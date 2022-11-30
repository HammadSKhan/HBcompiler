public class Token {

    private TokenName name;
    private String  value;

    public Token(){

    }

}
enum TokenValue{
    LE, LT,EQ,NE,GE,GT,AD,SB,ML,DV,AS,OP,CP,OB,CB,TR
}
enum TokenName{
    INT,CHAR,STRING,IF,ELSE,DO,WHILE,ROP,AOP,OOP,ID,SL,IV
}