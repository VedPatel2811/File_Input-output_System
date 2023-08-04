package matrices;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * This class provides the driver of the image processing 
 * application
 */
public class Main {

	/**
	 * Fields
	 * Description: Kernels that can be used to do image 
	 * processing based on https://en.wikipedia.org/wiki/Kernel_(image_processing)
	 * 
	 * kernels Array of convolution kernels
	 */
	private NamedMatrix[] kernels = {
			new NamedMatrix("identity",new double[][]{
				{0, 0, 0},
				{0, 2, 0},
				{0, 0, 0}
			}),

			new NamedMatrix("ridge",new double[][] {
				{-1, -1, -1},
				{-1,  10, -1},
				{-1, -1, -1}			
			}),

			new NamedMatrix("sharpen",new double[][] {
				{ 0, -1,  0},
				{-1,  7, -1},
				{ 0, -1,  0}			
			}),

			new NamedMatrix("unsharpen masking 5x5",new double[][] {
				{-1.0/257, -1.0/65,  -3.0/128, -1.0/65,  -1.0/257},
				{-1.0/65, -16.0/257, -3.0/65, -16.0/257, -1.0/65},
				{-3.0/128, -3.0/65, 119.0/65,  -3.0/65,  -6.0/257},
				{-1.0/65, -16.0/257, -3.0/65, -16.0/257, -1.0/65},
				{-1.0/257, -1.0/65,  -3.0/128, -1.0/65,  -1.0/257}
			}),			

			new NamedMatrix("laplace",new double[][] { // ridge + identity
				{-1, -1, -1},
				{-1,  12, -1},
				{-1, -1, -1}			
			})
	};

	/**
	 * Description: Method that opens a dialog box to load an image file
	 * @return The selected File. If the dialog box is canceled, a 
	 * non existent File is returned
	 */
	public File getImageFile()
	{
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		File file;
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			System.out.println("You chose to open this file: "+file.getAbsolutePath());
		} else {
			file = new File("noneExistent");
			System.out.println("No file chosen");
		}
		return file;
	}

	/**
	 * Description: Method that returns the selected kernel. 
	 * This method handled all invalid kernel selections 
	 * @return A NamedMatrix of the kernel
	 */
	public NamedMatrix getKernel(){
        try(Scanner k = new Scanner(System.in)){

            int choice; //initialize the variable 
            System.out.println("Please select one of the following: "); 
            do { //do while loop for looping this code until user gives input in a valid range 
                for(int i = 0; i < kernels.length; i++){  //to display prompts and list of kernel
                    System.out.println("\t" + (i +1)+ " "+ kernels[i].getName()); 
                }
                System.out.print("Select a filter: ");
                if(k.hasNextInt()){ 
                    choice = k.nextInt();
                    if (choice>=1 && choice<=kernels.length){ //if else condition for a valid user input 
                        return kernels[choice -1];
                    }else if (choice <= 0 || choice > kernels.length){
                        System.out.println("\nSelect a value in the range, Please select one of the following: ");
                    }   
                }else { 
                    System.out.println("\nSelect an integer, Please select one of the following: ");
                    k.next(); //to clear the user input 
                }
                
            }while(true); //to loop the code 
        }       
    }

	/**
	 * Description: Entry point for the application. Gets an 
	 * image file and selects kernel then applies filter to 
	 * the image and saves the file
	 * @param args Commandline parameters, not used
	 */
	public static void main(String[] args) {

		Main main = new Main();

		// Choose image file
		File imageFile = main.getImageFile();
		if(!imageFile.exists())
			return;

		// Choose kernel
		NamedMatrix kernel = main.getKernel();

		try {
			// Create filtered image using original image and kernal
			Matrix originalImage = new FileMatrix(imageFile);
			Convolution convolve = new Convolution(kernel);
			FileMatrix filteredImage = new FileMatrix(convolve.filter(originalImage));

			// Save filtered image in a file named:
			//    "image file name" "-" "kernel name" "." "image file extension"
			String fileName = imageFile.getAbsolutePath();
			int dot = fileName.indexOf('.');
			String output = fileName.substring(0,dot)+"-"+kernel.getName()+fileName.substring(dot);
			File outputFile = new File(output);
			if(filteredImage.save(outputFile))
				System.out.println("File: "+output+" succesfully written");
			else
				System.out.println("Could not write file: "+output);

		} catch (FileNotFoundException e) {
			System.out.println("Could not find file: "+imageFile.getAbsolutePath());
		}

	}

}
