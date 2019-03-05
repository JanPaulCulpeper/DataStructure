package edu.uprm.ece.icom4035.polynomial;

public class TermImp implements Term {
	
	
	private double coefficient;
	private int exponent;
	
	public TermImp(double c, int ex){
		this.coefficient = c;
		this.exponent = ex;
	}
	
	@Override
	public double getCoefficient() {
		return this.coefficient;
		}

	@Override
	public int getExponent() {
		return this.exponent;
		}

	@Override
	public double evaluate(double x) {
		return this.getCoefficient() * Math.pow(x, (double) this.getExponent());
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public void setExponent(int exponent) {
		this.exponent = exponent;
	}

}
