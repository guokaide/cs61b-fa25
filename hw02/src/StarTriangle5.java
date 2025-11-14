public class StarTriangle5 {
   /**
     * Prints a right-aligned triangle of stars ('*') with 5 lines.
     * The first row contains 1 star, the second 2 stars, and so on. 
     */
   public static void starTriangle5() {
      for (int i = 0; i < 5; i++) {
         for (int j = 0; j < 4-i; j++) {
            System.out.print(" ");
         }
         for (int j = 4 - i; j < 5; j++) {
            System.out.print("*");
         }
         System.out.println();
      }
   }
   
   public static void main(String[] args) {
      starTriangle5();
   }
}