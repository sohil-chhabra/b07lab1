import java.lang.Math; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
public class Polynomial{
	double[] coeff;
	int[] exp;
	public Polynomial(){
		coeff = null;
		exp = null;
	}
	public Polynomial(double[] coeff, int[] exp){

		this.coeff = coeff;
		this.exp = exp;
	}
	public Polynomial(File file){
		try {
			Scanner myReader = new Scanner(file);
			String data = myReader.nextLine();
			String[] split1 = data.split("(?=[+-])");
			int len = split1.length;
			this.exp = new int[len];
			this.coeff = new double[len];
			int current = 0;
			for(int i = 0; i < len; i++){
				System.out.println(i+" term of split1: "+ split1[i]+"\n");
				if(split1[i].contains("+")){
					split1[i] = split1[i].substring(1);
				}
				if(!split1[i].contains("x")){ // constant term
					this.coeff[current] = Double.parseDouble(split1[i]);
					this.exp[current] = 0;
					current++;
					continue;
				}
				String[] split2 = split1[i].split("x");
				if(split2.length == 1){
					this.coeff[current] = Double.parseDouble(split2[0]);
					this.exp[current] = 1;
					current++;
				}
				else{
					this.coeff[current] = Double.parseDouble(split2[0]);
					this.exp[current] = Integer.parseInt(split2[1]);
					current++;
				}
			}
			myReader.close();
		  } catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		  }

	}
	public Polynomial add(Polynomial poly){
		if(poly == null){
			return this;
		}
		int i = 0;
		int j = 0;
		int current = 0;
		int array_raw_length = this.exp.length + poly.exp.length;
		int [] array_raw_exp = new int[array_raw_length];
		double[] array_raw_coeff = new double[array_raw_length];
		Polynomial greater = null;
		Polynomial smaller = null;
		
		if(this.exp.length >= poly.exp.length){
			greater = this;
			smaller = poly;
		}
		else{
			greater = poly;
			smaller = this;
		}
		while(i < smaller.exp.length && j < greater.exp.length){
			if(smaller.exp[i] == greater.exp[j]){
				if(smaller.coeff[i] + greater.coeff[i] != 0){
					array_raw_exp[current] = smaller.exp[i];
					array_raw_coeff[current] = smaller.coeff[i] + greater.coeff[j];
					current++;
					j++;
					i++;
					}
			}
			else if(smaller.exp[i] > greater.exp[j]){
				array_raw_exp[current] = greater.exp[j];
				array_raw_coeff[current] = greater.coeff[j];
				current++;
				j++;
			}
			else if(greater.exp[j] > smaller.exp[i]){
				array_raw_exp[current] = smaller.exp[i];
				array_raw_coeff[current] = smaller.coeff[i];
				i++;
				current++;
			}
		}
		
		for(int k = j; k < greater.exp.length; k++){
			array_raw_exp[current] = greater.exp[k];
			array_raw_coeff[current] = greater.coeff[k];
			current++;
		}
		for(int m = i; m < smaller.exp.length; m++){
			array_raw_exp[current] = smaller.exp[m];
			array_raw_coeff[current] = smaller.coeff[m];
			current++;
		}
		int[] array_final_exp = new int[current];
		double[] array_final_coeff = new double[current];
		for(int l = 0; l < current; l++){
			array_final_exp[l] = array_raw_exp[l];
			array_final_coeff[l] = array_raw_coeff[l];
		}
		Polynomial added_poly = new Polynomial(array_final_coeff, array_final_exp);
		return added_poly;
	}
	public double evaluate(double arg){
		double result = 0;
		if(this.coeff == null || this.exp == null){
			return 0;
		}
		for(int i = 0; i < this.coeff.length; i++){
			result += this.coeff[i] * Math.pow(arg, this.exp[i]);
		}
		return result;
	}
	public boolean hasRoot(double toCheck){
		return this.evaluate(toCheck) == 0 ;
	}
	 
	public Polynomial multiply(Polynomial toMultiply){
		Polynomial result = null;
		for(int i = 0; i < toMultiply.coeff.length; i++){
			double[] inter_coeff = new double[this.coeff.length];
			int[] inter_exp = new int[this.exp.length];
			double[] single_coeff = {toMultiply.coeff[i]};
			int[] single_exp = {toMultiply.exp[i]};
			for(int j = 0; j < this.coeff.length; j++){
				inter_coeff[j] = single_coeff[0] * this.coeff[j];
				inter_exp[j] = single_exp[0] + this.exp[j];
			}
			Polynomial intermediate = new Polynomial(inter_coeff, inter_exp);
			result = intermediate.add(result);
		}
		return result;
	}
	public void saveToFile(String filename) {
		try {
			FileWriter myWriter = new FileWriter(filename);
			String polynomial = "";
			for(int i = 0; i < this.coeff.length; i++){
				if(i!=0 && this.coeff[i] > 0){
					polynomial+="+";
				}
				if(this.exp[i] == 0){ // constant term
					polynomial+=this.coeff[i];
					continue;
				}
				polynomial+=this.coeff[i];
				polynomial+='x';
				if(this.exp[i] != 1){
					polynomial+=this.exp[i];
				}
			}
			myWriter.write(polynomial);
			myWriter.close();
		  } catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		  }
		}
	  }
