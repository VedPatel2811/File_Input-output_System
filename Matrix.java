package matrices;

 import java.util.Arrays;
  
public class Matrix { 
	 protected final double[][] array; //creating the matrix data that can't be change once created 
	 protected final int rows;
	 protected final int columns;
  
	 public Matrix (int rows, int columns) { //constructor to create a zero m 
		  this.rows = rows; //storing the values provided by user into final variables 
		  this.columns = columns; 
		  this.array = new double [rows][columns]; 
		  }
	 
	 
	  public Matrix(double[][] inputArray) { //constructor to create m from 2D array 
		  this.rows = inputArray.length; // the number of rows in matrix that user provided is stored in the final variables 
		  this.columns = inputArray.length; 
		  this.array = new double[rows][columns];
		  
		  for (int r = 0; r < rows; r++) { //this loop will create the matrix that have the same dimensions compared to the dimensions that user had provided 
			  for (int c = 0; c < columns; c++) {
				  array[r][c] = inputArray[r][c]; 
				  } 
			  } 
		  }
  
	  
	  public Matrix add(Matrix m) { //this is the method to add the two matrix 
		  Matrix result = new Matrix(rows, columns); //this is to store the resultant matrix 
		  if (this.rows != m.rows || this.columns != m.columns) {
			  System.out.println("Incorrect dimensions of matrix");
		 }else {
		  for (int r = 0 ; r < rows; r++) { //this loop will create a matrix of the same dimension that user provided and add two matrix 
			  for (int c = 0; c < columns; c++) {
				  result.array[r][c] = array[r][c] + m.array[r][c]; //this line will add two matrix 
				  } 
			  }
		 	}
		  return result; //final result store here 
		  }
  
	  
	  public Matrix sub(Matrix m) { //method to subtract two matrix 
		  Matrix result = new Matrix(rows, columns); //to store the result this method 
		  if (this.rows != m.rows || this.columns != m.columns) { //condition for the error 
			  System.out.println("Incorrect dimensions of matrix"); //error statement
		  }else {
		  for (int r = 0 ; r < rows; r++) { 
			  for (int c = 0; c < columns; c++) {
				  result.array[r][c] = array[r][c] - m.array[r][c]; //to subtract two matrix 
				  } 
			  }
		  	}
		  return result; //return result 
		  }
  
	  
	  
	  public Matrix mult(Matrix m) { //to multiply two matrix 
		  Matrix result = new Matrix(rows,columns); //to store the result of this method 
		  if (this.rows != m.rows || this.columns != m.columns) { 
			 System.out.println("Incorrect dimensions of matrix");
		  }else {
		  for (int r = 0 ; r < rows; r++) { 
			  for (int c = 0; c < columns; c++) { 
				  result.array[r][c] = array[r][c] * m.array[r][c]; //to multiply two matrix 
				  } 
			  	}
		  	}
		  return result; //to return the result
		  }
	  
	  
	  
	  public double sum() { //to calculate the sum of the values inside the matrix
		  double sum = 0.0; //initializing the variable 
		 
		  for (int r = 0; r < rows; r++) { 
			  for (int c = 0; c < columns; c++) { 
				  sum = sum + array[r][c]; //for the sum of the matrix 
				  } 
			  } 
		  return sum; //return the result 
	  }
  
	  
	  
	  public Matrix subMatrix(int row, int column, int length, int width) {  //to create a sub matrix from the parent matrix  
		  Matrix result = new Matrix (length, width);
		  if (row<0 || column<0 || row+length > this.rows || column+width > this.columns) {
			  System.out.println("Incorrect dimensions of matrix");
		  }else {

		  for (int l = 0; l < length; l++) { 
			  for (int w = 0; w < width; w++) {
		  result.array[l][w] = array[row + l][column + w]; //the final result of the sub matrix will calculate by this method 
			  } 
		  	}	 
		  }
  		return result; 
  }
  
	  
	  
	  public String toString(){ //this will convert the array format to proper matrix form 
		  StringBuffer result = new StringBuffer();
  
		  	for(double[] row : array){ 
		  		result.append(Arrays.toString(row));
		  		result.append('\n'); 
		  	}
		  return result.toString();
  		}


	public static double array(int r, int c) {
		return 0;
	} 	
  }
 