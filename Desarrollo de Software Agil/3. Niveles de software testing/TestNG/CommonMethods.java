package proyecto_testng;

import java.io.File;
import java.util.Scanner;

public class CommonMethods {

	public void redirectStdOutput(String path) {

	}

	public String readFileContents(String path) {
		String line="";
		try 
		{
			Scanner input = new Scanner(new File(path));
			while (input.hasNextLine()) 
			{
				line = input.nextLine();
				System.out.println(line);
			}
			input.close();
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return line;
	}

	public void redirectStdInput(String stdInputPath) {
		// TODO Auto-generated method stub

	}
}
