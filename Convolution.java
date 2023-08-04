package matrices;

public class Convolution {
public Matrix Kernel; //Instance variables
    
    public Convolution(Matrix Kernel){  //constructor for a given kernel
        this.Kernel = Kernel;
    }
    public Matrix filter(Matrix image){
        int rows,columns; //declarations 
        rows = image.rows - Kernel.rows + 1; //equation for the rows 
        columns = image.columns - Kernel.columns + 1; //equation for columns 
        double[][] outputImage = new double[rows][columns]; 

        for(int r =0; r< rows; r++){ //for loop to multiply and add two matrix 
            for(int c = 0; c< columns; c++){
                Matrix subMatrix = image.subMatrix(r, c, Kernel.rows, Kernel.columns);
                Matrix multiplyMatrix = subMatrix.mult(Kernel); //this will multiply 
                outputImage[r][c] = multiplyMatrix.sum(); //to add 
            }
        }
        return new Matrix(outputImage); //return values 
        
    }
}
