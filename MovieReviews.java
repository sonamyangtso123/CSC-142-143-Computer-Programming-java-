import java.io.*;
import java.util.*;
import java .util.Arrays;
/**
 *  MovieReviews class reads movie reviews from several reviewers and sort them in ranking order.
 * 
 * @author (Sonam Yangtso ) 
 * @version (2/25/2017)
 */
public class MovieReviews
{
    //declare the instance variable that will hold t array
    private int [][]rawReviews;
    private String [] movieTitles;
    private String [] reviewers;
    private int [][]rankedReviews;
    private double []avgRanks;
    // instance fields
    private int reviewCount;
    private int movieCount;

    /**
     * constructor MovieReviews  calls readReviews, generateRankings, and calculateAvgRankings, all private methods.
     *
     * @param  fileName;
     * @throws FileNotFoundException
     */
    public MovieReviews(File fileName)throws FileNotFoundException
    {
        readReviews(fileName);
        generateRankings();
        calculateAvgRankings();
        displayReviews1();

    }

    /**
     *accessor  getReviewerCount method get the number of reviewers from the file.
     *
     * 
     * @return     number of reviewers(row).
     */

    public int getReviewerCount(){
        return this.reviewCount;
    }

    /**
     *accessor  getMovieCount method get the number of movies  from the file to review.
     *
     * 
     * @return     number of movies(column).
     */

    public int getMovieCount(){
        return this.movieCount;
    }

    /**
     * Accessor getRawReview method gets you rawReview 2D arrray.
     * @param  int r(number of reviewers ) ,int m(number of movies)
     * @return   rawReviews[r][m]  
     * @throws  an Illegal Argument Exception
     */
    public int getRawReview(int r,int m ){
        if((r<0 || r> reviewCount) && (m<0 || m >movieCount))
        {
            throw  new IllegalArgumentException("Your parameters are outside array bounds");

        }
        return rawReviews[r][m];

    }

    /**
     * Accessor getRankedReview  method gets you rankedReviews 2D arrray.
     * @param  int r(number of reviewers ) ,int m(number of movies)
     * @return  rankedReviews [r][m]  
     * @throws  an Illegal Argument Exception
     */


    public int getRankedReview(int r,int m){
        if((r<0 || r>reviewCount) && (m<0 || m >movieCount))
        {
            throw  new IllegalArgumentException("Your parameters are outside array bounds");

        }
        return rankedReviews[r][m];
    }
    /**
     * Accessor getMovieTitle  method gets you movieTitles arrray.
     * @param  int m(number of movies)
     * @return  movieTitles[m]  
     * @throws  an Illegal Argument Exception
     */
    

    public String getMovieTitle(int m){
        if(m<0 ||m>movieCount)
        {
            throw  new IllegalArgumentException("Your parameters are outside array bounds");
        }
        return  movieTitles[m];
    } 
    /**
     * Accessor getReviewerName method gets you reviewers arrray.
     * @param  int r(number of reviewers )
     * @return   reviewers[r]
     * @throws  an Illegal Argument Exception
     */
    public String getReviewerName(int r){
        if(r<0 || r>reviewCount)
        {
            throw  new IllegalArgumentException("Your parameters are outside array bounds");
        }
        return reviewers[r];
    }

    public double getAvgRank(int m) {
        if(m < 0 ||m>movieCount)
        {
            throw  new IllegalArgumentException("Your parameters are outside array bounds");

        }
        return avgRanks[m];

    }

    public void displayRawReviews(){
        displayReviews(rawReviews);

    }

    public void displayRankedReviews(){
        displayReviews( rankedReviews); 
    }
    
    /**
     * displayReviews method is private method,it takes a parameter 
     * referring to the review array to display (raw or ranked)
     *
     * @param int[][] rawDisplay
     */


    private void displayReviews(int[][] rawDisplay){
        for(int i=0;i<reviewCount;i++){
            for(int j=0;j<movieCount;j++){
                System.out.print(rawDisplay[i][j]+" ");
            }
            System.out.println();
        }

    }

    private void displayReviews1(){
        System.out.println("MOVIE \t\t\t\t RANKS");
        System.out.println("------------------\t\t---------");
        for(int i=0;i<movieCount;i++){

            System.out.print(movieTitles[i]+"\t\t"+avgRanks[i]+"\n");
            
        }

    }
    
    /**
     * readReviews is a private method and use Scanner to read the file.
     * creates the raw , movie title and reviewers arrays.
     *
     * @ throws FileNotFoundException
     */
    private void readReviews( File fileName) throws FileNotFoundException{
        Scanner input = new Scanner(fileName);
        this.reviewCount = input.nextInt();
        this.movieCount = input.nextInt();

        rawReviews = new int [reviewCount][movieCount];
        for(int i=0;i< reviewCount;i++){
            for(int j=0;j<movieCount;j++){

                rawReviews[i][j] = input.nextInt();

            }

        }

        input.nextLine();

        movieTitles = new String [movieCount];
        for(int i=0;i< movieCount; i++){
            movieTitles[i] = input.nextLine();

        }

        reviewers = new String [reviewCount];
        for(int j=0;j<reviewCount;j++){
            reviewers[j] = input.nextLine();
        }

    }
    
    /**
     *  generateRankings is a private method creates the  rankedReviews array using 
     *copyOf method and sort method.
     * */


    private void  generateRankings(){
        rankedReviews = new int [reviewCount][movieCount];

        for(int row =0;row< reviewCount;row++){
            int[] temp= Arrays.copyOf(rawReviews[row],rawReviews[row].length);

            Arrays.sort(temp);
            int rating =1;
            for(int i = temp.length-1;i>=0;i--){
                for(int c = 0;c<rawReviews[row].length;c++){
                    if(temp[i]==rawReviews[row][c]){
                        rankedReviews[row][c] =rating;
                    }

                }
                if (i>0 && temp[i]!=temp[i-1]) rating++;
            }

        }

    }
    
    /**
     * calculateAvgRankings is a private method and create an array to hold
     * average rankings per movie
     *
     * 
     */
    private void calculateAvgRankings(){
        avgRanks = new double[movieCount];
        double sum;

        for(int col =0;col<movieCount;col++){
            sum=0;
            for(int row=0;row<reviewCount;row++){
                sum+=rankedReviews[row][col];
            }

            avgRanks[col]=sum/reviewCount;

        }

    }
    
    /**
     *  sortByRanking methodsort the movies such that the movie with the highest ranking comes first 
     *  and the one with the lowest ranking last,maintaining parallel data in the avgRanks, 
     *  movieTitles, rawReviewers, and rankedReviews arrays
     *
     */
    public void sortByRanking() {
        double temp1;
        String temp2;
        int temp3;

        for(int i=0;i<movieCount-1;i++){
            for(int k=i+1;k<movieCount;k++){

                if (avgRanks[i]> avgRanks[k]){
                    temp1=avgRanks[i];
                    avgRanks[i]=avgRanks[k];
                    avgRanks[k]=temp1;

                    temp2=movieTitles[i];
                    movieTitles[i]=movieTitles[k];
                    movieTitles[k]=temp2;
                    for(int j=0;j<reviewCount;j++){
                        temp3=rawReviews[j][i];
                        rawReviews[j][i]=rawReviews[j][k];
                        rawReviews[j][k]=temp3;

                        temp3=rankedReviews[j][i];
                        rankedReviews[j][i]=rankedReviews[j][k];
                        rankedReviews[j][k]=temp3;

                    }
                }
            }
        }

    }
}

