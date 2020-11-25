
/**
 * this is my test method
 * 
 * @author (Sonam Yangtso) 
 * @version (03/03/2017)
 */
import java.util.*;
import java.io.*;
public class Test
{
    public static void main(String[]args)throws FileNotFoundException{
        File name = new File("MovieReviews.txt");
        //calling MovieReviews constructor
        MovieReviews mine = new MovieReviews(name);
        System.out.println();
        mine.displayRawReviews();
        System.out.println();
        mine.displayRankedReviews();
        System.out.println();
        
        mine.sortByRanking();
        System.out.println();
        mine.displayRawReviews();
        System.out.println();
        mine.displayRankedReviews();
        System.out.println();
        
        
        
        
        
        
        
        
    }
}