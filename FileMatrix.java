package matrices;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;


public class FileMatrix extends Matrix {
    
    private FileMatrix(Scanner k){  //to create the FileMatrix from a File easier 
        super (k.nextInt(),k.nextInt()); //calling Matrix class
        
        for (int r = 0; r < rows; r++){
            for (int c=0; c< columns;c++){
                array[r][c] = k.nextDouble();
            }
        }
    }

    public FileMatrix(File file) throws FileNotFoundException { //this is chained to previous constructor and it will create a file 
       this(new Scanner(file));
    }

    public FileMatrix(Matrix matrix){ //it will create FileMatrix from an existing Matrix 
        super(matrix.array);
    }

    public boolean save(File file){ //this will write the values to the text file 
        try{
            PrintWriter p = new PrintWriter(file);
            p.println(rows + " " + columns);
            for (int r = 0; r < rows; r++){
                for (int c = 0; c < columns; c++){
                    p.print(array[r][c]+ " ");
                }
            p.println();
        }
        p.close();
        return true;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
