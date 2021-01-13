import java.util.*; 



public class Game2048
{ 
   
   public static void main(String args[])
   {
      
      Scanner scan = new Scanner(System.in); 
      
      RunGame run = new RunGame();
      
      
      
     
   }
}   


class RunGame
{
   
   private static int moves = 0; 
   private Tile[][] matrix = new Tile[4][4];
   private Scanner keyboard = new Scanner(System.in); 
   private boolean gameFinished = false;  
   
   public RunGame()
   {
   
      int direction = 0; 
   
      for (int i = 0; i < 4; i++){
         for (int x = 0; x < 4 ; x++){
         
            matrix[i][x] = new Tile(); 
         
         }
      }
   
      startingTiles();
      System.out.println(printBoard(matrix)); 
   
      do 
      {
         
         String choice = keyboard.next(); 
      
      
         if(choice.toLowerCase().equals("d")) 
         {
            direction = 1; 
         }
         else if(choice.toLowerCase().equals("a")) 
         {
            direction = 2; 
         }
         else if(choice.toLowerCase().equals("w")) 
         {
            direction = 3; 
         }
         else if(choice.toLowerCase().equals("s") )
         {
            direction = 4; 
         }
         else
         {
         
            direction = 5; 
         
         }
      
      
      
      
         switch (direction)
         {
         
         
            case 1 : moveRight(false); moves++; 
               break; 
            case 2 : moveLeft(false); moves++; 
               break;
            case 3 : moveUp(false); moves++; 
               break;
            case 4 : moveDown(false); moves++;  
               break;
            default : System.out.println("Invalid command");
         
         }
         
         System.out.println(printBoard(matrix));
         
      }while(!isGameOver());
      
      System.out.println("\nGAME OVER :: You took " + moves + " turns" );
      /*
      System.out.println(printBoard(matrix)); 
      moveRight(); 
      System.out.println("RIGHT \n" + printBoard(matrix)); 
      moveLeft(); 
      System.out.println("LEFT \n" + printBoard(matrix)); 
      moveUp();
      System.out.println("UP \n" + printBoard(matrix)); 
      moveDown(); 
      System.out.println("DOWN \n" + printBoard(matrix)); 
   */
   
   }
   
   
   public void startingTiles()
   {
   
      matrix[(int)(Math.random() * (3))][(int)(Math.random() * (3))].setValue(Math.random() > .5 ? 2 : 4); 
   
   
      Tile temp = matrix[(int)(Math.random() * (3))][(int)(Math.random() * (3))];
   
      while(!temp.isFilled())
      {
      
         if(!temp.isFilled()){
         
            temp.setValue(Math.random() > .5 ? 2 : 4); 
         
         
         }
         else 
         {
         
            temp = matrix[(int)(Math.random() * (3))][(int)(Math.random() * (3))];
         
         
         }
      
      }
   }

   public void addTile(ArrayList<Tile> tiles)
   {
   
      tiles.get((int) (Math.random() * (tiles.size()))).setValue(Math.random() > .5 ? 2 : 4);
   
   
   }
   
   public boolean matrixIsFull(boolean add)
   {
   
      boolean hasEmptySpaces = false; 
   
      ArrayList<Tile> tiles = new ArrayList<Tile>(); 
   
      for(Tile[] rows : matrix) 
      {
         for(Tile t: rows) 
         { 
            if(t.isFilled())
               continue;  
            else
            {
            
               tiles.add(t); 
               hasEmptySpaces = true; 
            
            } 
         }
                  
      }  
      if(hasEmptySpaces && add)
      {
      
         addTile(tiles);
         return false;
         
      }
      else if(hasEmptySpaces && !add)
      {
      
         return false;
      
      }
      else
      {
      
         return true; 
         
      }
      
   
      
   }
   
   public boolean moveRight(boolean check)
   {
   
      boolean hasMoved = false; 
   
      for(int x = 2 ; x > -1 ; x--){
         for(int y = 0; y < 4; y++){
         
         
            if(matrix[y][x].isFilled())
            {
               int x2 = x;
               int y2 = y;
            
            
               while(!matrix[y2][x2+1].isFilled()){
               
               
                  hasMoved = true;
                  matrix[y2][x2 + 1].setValue(matrix[y2][x2].getValue());
                  matrix[y2][x2].setValue(0); 
                  x2 += 1;
                   
                  if(x2 == 3)
                     break; 
               
               }
               if((x2 + 1) < 4){
                  if(matrix[y2][x2+1].isFilled() && matrix[y2][x2+1].getValue() == matrix[y2][x2].getValue())
                  {
                     if(!check){
                     
                     matrix[y2][x2 + 1].setValue(matrix[y2][x2 + 1].getValue() * 2);
                     matrix[y2][x2].setValue(0); 
                     hasMoved = true;
                     }
                     else
                       hasMoved = false;
                  
                  }
               }
            
            
            }
         }
      }
      
   
      if(hasMoved)
      {
         matrixIsFull(true); 
         return true; 
      }
      
      return false; 
   
   
   }
   
   
   public boolean moveLeft(boolean check)
   {
   
      boolean hasMoved = false; 
   
      for(int x = 1 ; x< 4 ; x++){
         for(int y = 0; y < 4; y++){
         
         
            if(matrix[y][x].isFilled())
            {
               int x2 = x;
               int y2 = y;
            
            
               while(!matrix[y2][x2-1].isFilled() && (x2 - 1) > -1){
               
                  matrix[y2][x2 - 1].setValue(matrix[y2][x2].getValue());
                  matrix[y2][x2].setValue(0); 
                  x2 -= 1; 
                  
                  
                  hasMoved = true;
                  
                  
                  if(x2 == 0)
                     break; 
               
               }
               
               System.out.print(""); 
               if(x2 != 0){
                  if(matrix[y2][x2-1].isFilled() && matrix[y2][x2-1].getValue() == matrix[y2][x2].getValue())
                  {
                     if(!check)
                     matrix[y2][x2 - 1].setValue(matrix[y2][x2 - 1].getValue() * 2);
                     matrix[y2][x2].setValue(0); 
                     
                     
                     hasMoved = true;
                     } 
                     else
                        {
                        
                        hasMoved = false; 
                        } 
                        
                    }
                  }
               }
            }
         
      
      
      if(hasMoved)
      {
         matrixIsFull(true); 
         return true; 
      }
      
      return false; 
    
   }
   
   
   public boolean moveUp(boolean check)
   {
   
      boolean hasMoved = false; 
   
      for(int y = 1 ; y< 4 ; y++){
         for(int x = 0; x < 4; x++){
         
         
            if(matrix[y][x].isFilled())
            {
               int x2 = x;
               int y2 = y;
            
            
               while(!matrix[y2 - 1][x2].isFilled() && (y2 - 1) > -1){
               
                  matrix[y2 - 1][x2].setValue(matrix[y2][x2].getValue());
                  matrix[y2][x2].setValue(0); 
                  y2 -= 1; 
                  
                  hasMoved = true;
                  
                  if(y2 == 0)
                     break; 
               
               }
               if((y2 - 1) > -1){
                  if(matrix[y2 - 1][x2].isFilled() && matrix[y2 - 1][x2].getValue() == matrix[y2][x2].getValue())
                  {
                     if(!check){
                     
                     matrix[y2 - 1][x2].setValue(matrix[y2 - 1][x2].getValue() * 2);
                     matrix[y2][x2].setValue(0); 
                     
                     hasMoved = true;
                     }
                     else 
                     {
                        hasMoved = false; 
                     }
                  }
               }
            }
         }
      }
      
      
      if(hasMoved)
      {
         matrixIsFull(true); 
         return true; 
      }
      
      return false; 
   
   }
   
   
   public boolean moveDown(boolean check)
   {
   
      boolean hasMoved = false; 
      
   
      for(int y = 2 ; y > -1 ; y--){
         for(int x = 0; x < 4; x++){
         
         
            if(matrix[y][x].isFilled())
            {
               int x2 = x;
               int y2 = y;
            
            
               while(!matrix[y2 + 1][x2].isFilled() && (y2 +1) < 4){
               
               
                  
                  
                     hasMoved = true; 
                     matrix[y2 + 1][x2].setValue(matrix[y2][x2].getValue());
                     matrix[y2][x2].setValue(0); 
                     y2 += 1; 
                  
                  
                  
                     if(y2 == 3)
                        break; 
                  
                  
               
               }
               if((y2 +1) < 4){
                  if(matrix[y2 + 1][x2].isFilled() && matrix[y2 + 1][x2].getValue() == matrix[y2][x2].getValue())
                  {
                  if(!check){
                     matrix[y2 + 1][x2].setValue(matrix[y2 + 1][x2].getValue() * 2);
                     matrix[y2][x2].setValue(0); 
                     
                     
                     hasMoved = true; 
                     
                   }
                   {
                        hasMoved = false; 
                         
                    } 
                  }
               }
            }
         }
      
      
      }
      if(hasMoved)
      {
         matrixIsFull(true); 
         return true; 
      }
      
      return false; 
      
   }


   public boolean isGameOver()
   {
   
      if(matrixIsFull(false))
      {
      
      if(!moveDown(true) && !moveUp(true) && !moveLeft(true) && !moveRight(true))
         return true;
      else
         return false; 
      
      
      }
      
      
      for (Tile[] row : matrix){
         for(Tile tile : row){
         
         
         if(tile.getValue() == 2048){
            System.out.println("\nYou win! Would you like to keep playing? Y or N?"); 
            
            String s = keyboard.next();
      
      if(s.equals("n")){
         return true;
         }
      else{
         System.out.println("");
         return false;

         }
         }
         
         }
   
   
   
    
   
   }
   
   return false; 
   }
   public String printBoard(Tile[][] board)
   {
      
      String s = "\n" + "\n" +"\n" + 
         "| " + matrix[0][0] + " | " + matrix[0][1] + " | " + matrix[0][2] + " | " + matrix[0][3] + " |" +"\n" + "\n" +  
         "| " + matrix[1][0] + " | " + matrix[1][1] + " | " + matrix[1][2] + " | " + matrix[1][3] + " |" + "\n" + "\n" + 
         "| " + matrix[2][0] + " | " + matrix[2][1] + " | " + matrix[2][2] + " | " + matrix[2][3] + " |" +"\n" + "\n" + 
         "| " + matrix[3][0] + " | " + matrix[3][1] + " | " + matrix[3][2] + " | " + matrix[3][3] + " |"; 
      ; 
      
      return s; 
   }
   
   
   
   
}    
class Tile
{
   
   int value;  
   
   boolean filled = false; 
   
   public Tile()
   {
   
      value = 0; 
   
   }
   public int getValue()
   {
      
      return value;
      
   }
   
   public void setValue(int n)
   {
      
      value = n; 
      filled = true; 
      
   }
   
   
   public boolean isFilled()
   {
      
      if(value != 0) 
         return true;
      else
         return false;
      
      
      
   }
   
   public String toString()
   {
   
      return "" + value;
   
   }
   
   
}

   
     
