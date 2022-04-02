package com.example;

/**
 * Takes in and evaluates a string representing a tic tac toe board.
 */
public class TicTacToeBoard {
  String board_lower;
  public TicTacToeBoard(String board) {
     board_lower=board.toLowerCase();
  }
public Evaluation evaluate(){
    int x_count=0;
    int o_count=0;
    if(board_lower.length()!=9)
    {
      return Evaluation.InvalidInput;
    }
    for(int i=0;i<board_lower.length();i++)
    {
        if(board_lower.charAt(i)== 'x')
        {
            x_count++;
        }
        else{
            o_count++;
        }
    }
    
    boolean player_x=winner('x',board_lower);
    boolean player_o=winner('o',board_lower);
    
  
    if(Math.abs(x_count - o_count)<=2){
      return Evaluation.UnreachableState;
    }

    if(player_o && player_x){
      return Evaluation.UnreachableState;
    }
    else if(player_x){
      return Evaluation.Xwins;
    }
    else if(player_o){
      return Evaluation.Owins;
    }
    else{
      return Evaluation.NoWinner;
    }

}

public static boolean winner(char c,String board1){
  
  if((board1.charAt(0)==c)&&(board1.charAt(1)==c)&&(board1.charAt(2)==c))
      return true;
  if((board1.charAt(3)==c)&&(board1.charAt(4)==c)&&(board1.charAt(5)==c))
      return true;
  if((board1.charAt(6)==c)&&(board1.charAt(7)==c)&&(board1.charAt(8)==c))
      return true;
  if((board1.charAt(0)==c)&&(board1.charAt(3)==c)&&(board1.charAt(6)==c))
      return true;
  if((board1.charAt(1)==c)&&(board1.charAt(4)==c)&&(board1.charAt(7)==c))
      return true;
  if((board1.charAt(2)==c)&&(board1.charAt(5)==c)&&(board1.charAt(8)==c))
      return true;
  if((board1.charAt(0)==c)&&(board1.charAt(4)==c)&&(board1.charAt(8)==c))
      return true;
  if((board1.charAt(2)==c)&&(board1.charAt(4)==c)&&(board1.charAt(6)==c))
      return true;
  return false;
}

public static void main(String args[]){
  TicTacToeBoard v1=new TicTacToeBoard("XXX...oo.");
  System.out.println(v1.evaluate());

}
}