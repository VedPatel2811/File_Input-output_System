package matrices;

public class NamedMatrix extends Matrix {
    String name; //instance variable 
    
    
    public NamedMatrix(String name,double[][] array){ //the name of the matrix 
        super(array);
        this.name = name;
    }
    
    public String getName(){ //getter for the name 
        return name;
    }
    
}
