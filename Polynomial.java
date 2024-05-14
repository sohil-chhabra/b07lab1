import java.lang.Math; 
public class Polynomial{
	double[] coeff;
	public Polynomial(){
		coeff = new double[1];
		coeff[0] = 0;
	}
	public Polynomial(double[] coeff){
		this.coeff = coeff;
	}
	public Polynomial add(Polynomial poly){
		Polynomial greater;
		Polynomial smaller;
		if(this.coeff.length >= poly.coeff.length){
			greater = this;
			smaller = poly;
		}
		else{
			greater = poly;
			smaller = this;
		}
		double [] result = new double[greater.coeff.length];
		for(int i = 0; i < smaller.coeff.length; i++){
			result[i] = smaller.coeff[i] + greater.coeff[i];
		}
		for(int i = smaller.coeff.length; i < greater.coeff.length; i++){
			result[i] = greater.coeff[i];
		}
		Polynomial added_poly = new Polynomial(result);
		return added_poly;
	}
	public double evaluate(double arg){
		double result = 0;
		for(int i = 0; i < this.coeff.length; i++){
			result += this.coeff[i] * Math.pow(arg, i);
		}
		return result;
	}
	public boolean hasRoot(double toCheck){
		return this.evaluate(toCheck) == 0 ;
	}


}