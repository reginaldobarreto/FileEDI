package program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import entities.Product;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List <Product> product = new ArrayList<>();
		Locale.setDefault(Locale.US);		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter path of file");
		String path = scanner.nextLine();
		
		File sourceFile = new File(path);
		
		String sourceFolderStr = sourceFile.getParent();
		
		boolean success = new File(sourceFolderStr + "\\out").mkdir();
		
		String targetFileStr = sourceFolderStr + "\\out\\summary.txt";
		
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
			
			String line = bufferedReader.readLine();
			
			while(line != null) {
				String[] prod = line.split(",");
				String name = prod[0];
				Double price = Double.valueOf(prod[1]);
				int quantity = Integer.valueOf(prod[2]);
				product.add(new Product(name, price, quantity));
				line = bufferedReader.readLine();
			}
			
			
			try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetFileStr))){
				
				for (Product item : product) {
					bufferedWriter.write(item.getName() + "," + String.format("%.2f", item.totalPrice()));
					bufferedWriter.newLine();
				}

				System.out.println(targetFileStr + "\nCREATED!");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage());;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e.getMessage());;
		}
		
		
		scanner.close();
	}

}
