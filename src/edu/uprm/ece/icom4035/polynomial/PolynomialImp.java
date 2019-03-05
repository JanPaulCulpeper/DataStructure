package edu.uprm.ece.icom4035.polynomial;

import java.util.Iterator;
import java.util.Iterator;



import edu.uprm.ece.icom4035.list.List;



public class PolynomialImp extends TermListFactory implements Polynomial {
	
	// List that contains all terms
	private List<Term> List;
	
	 //Constructor sin parametro String
	public PolynomialImp(){
		this.List = new TermListFactory().newListFactory().newInstance();
	}
	
	// Constructor con String
	public PolynomialImp(String ps){
		this.List = new TermListFactory().newListFactory().newInstance();
		toPoly(ps);
	}
	
	 // Convierte de string a una lista de TermImp
	public void toPoly(String ps) {
		String[] terms = ps.split("\\+");
		for(String t: terms) {
			if(t.charAt(0) == 'x') {
				if(t.length() == 1) {
					this.getList().add(new TermImp(1,1));
				}else {
					this.getList().add(new TermImp (1,Integer.valueOf(t.valueOf(t.charAt(2)))));
				}
			}else if(t.contains("x")) {
				String[] g = t.split("x");
				if(g.length > 1) 
					this.getList().add(new TermImp (Double.valueOf(g[0]), Integer.valueOf(g[1].substring(1, g[1].length()))));
				else
					this.getList().add(new TermImp (Double.valueOf(g[0]), 1));
			}else{
				this.getList().add(new TermImp (Double.valueOf(t), 0));
			}
		}
	}

	public String toString() {
		PolynomialImp p = (PolynomialImp) this;
		String re = "";
		for(Term i: p.getList()) {
			if(i.getExponent() == 0) {
				re += "+" + String.format("%.2f", i.getCoefficient());
			}else if(i.getExponent() == 1) {
				if(i.getCoefficient() == 1) {
					re += "+x";
				}else {	
					re += "+" + String.format("%.2f", i.getCoefficient()) + "x";
				}
			}else {
				if(i.getCoefficient() != 1) {
					re += "+" + String.format("%.2f", i.getCoefficient())  + "x^" + String.valueOf(i.getExponent());
				}else {
					re += "+x^" + String.valueOf(i.getExponent());
				}
			}
		}
		return re.substring(1) ;
	}

	public List<Term> getList() {return this.List;}

	@Override
	public Iterator<Term> iterator() {return this.List.iterator();}

	@Override
	public int degree() {
		int hExp = 0;
		for(Term i: this.List)
			if(i.getExponent() > hExp)
				hExp = i.getExponent();
		return hExp;
	}
	@Override
	public double evaluate(double x) {
		double result = 0;
		for(Term i: this.getList()) 
			result += i.evaluate(x);
		return result;
	}
	@Override
	public Polynomial multiply(double c) {
		PolynomialImp result = new PolynomialImp();
		PolynomialImp th = this;
		if(c == 0) {
			result.List.add(new TermImp(0,0));
			return result;
		}
		for(int i = 0; i < th.List.size(); i++) {
			result.List.add(new TermImp(th.List.get(i).getCoefficient()*c,th.List.get(i).getExponent()));
		}
		return result;
	}
	@Override
	public Polynomial derivative() {
		PolynomialImp p = new PolynomialImp();
		PolynomialImp q = this;
		for(Term i: q.List) {
			if(i.getExponent() == 1) {
				p.List.add(new TermImp(i.getCoefficient(),0));
			}
			else if(i.getExponent() != 0)
			{
				p.List.add(new TermImp(i.getCoefficient()* ((double) i.getExponent()), i.getExponent() - 1));
			}
		}
		return p;
	}



















	public PolynomialImp sortByDegree(PolynomialImp p){
		for(int i = 0; i < p.List.size(); i++) 
			for(int l = i+1; l < p.List.size(); l++) 
				if(p.getList().get(i).getExponent()<p.getList().get(l).getExponent()) {
					TermImp temp = (TermImp) p.getList().get(i);
					p.getList().set(i, p.List.get(l));
					p.getList().set(l, temp);	
				}
		return p;

	}
	public Polynomial add(Polynomial P2) {
		PolynomialImp first = (PolynomialImp) P2;
		PolynomialImp second = this;
		PolynomialImp result = new PolynomialImp();


		for(Term i: first.getList()) {
			result.List.add(i);
		}
		for(Term i: second.getList()) {
			result.List.add(i);
		}
		for(int i = 0; i < result.List.size(); i++) 
			for(int l = i+1; l < result.List.size(); l++) 
				if(result.List.get(i).getExponent() == result.List.get(l).getExponent()) 
					if(result.List.get(i).getCoefficient() == result.List.get(l).getCoefficient()) {
						result.List.remove(i);
						result.List.remove(l);
					}
					else{
						result.List.set(i,new TermImp(result.getList().get(i).getCoefficient() + result.getList().get(l).getCoefficient(), result.getList().get(i).getExponent()));
						result.List.remove(l);
					}
		for (int i = 0; i < result.getList().size(); i++) {
			if (result.getList().get(i).getCoefficient() == 0)
				result.getList().remove(i);
		}
		return OS(result);

	}

	@Override
	public Polynomial multiply(Polynomial P2) {
		PolynomialImp p2 = this;
		PolynomialImp r = (PolynomialImp) P2;
		PolynomialImp result = new PolynomialImp("0");
		if(p2.equals(result) || r.equals(result)) {
			return result;
		}
		for(int i = 0; i < p2.List.size(); i++) 
			for(int l = 0; l < r.getList().size(); l++) 
				result.List.add(new TermImp(p2.List.get(i).getCoefficient()*r.List.get(l).getCoefficient(),p2.List.get(i).getExponent() + r.List.get(l).getExponent()));
		for(int i = 0; i < result.List.size(); i++) 
			for(int l = i+1; l < result.List.size(); l++) 
				if(result.List.get(i).getExponent() == result.List.get(l).getExponent()) {
					result.List.set(i, new TermImp(result.getList().get(i).getCoefficient() + result.getList().get(l).getCoefficient(),result.getList().get(i).getExponent()));
					result.List.remove(l);

				}
		return sortByDegree(result);
	}




	@Override
	public Polynomial indefiniteIntegral() {

		PolynomialImp th = this;
		PolynomialImp res = new PolynomialImp();

		for( Term i : th.getList()) {
			if( i.getExponent() != 0) {
				res.getList().add(new TermImp(i.getCoefficient() / ((double) i.getExponent() + 1), i.getExponent() + 1) );
			}else {
				res.getList().add(new TermImp(i.getCoefficient(), i.getExponent()+1));
			}
		}
		res.List.add(new TermImp(1,0));
		return res;
	}
	@Override
	public double definiteIntegral(double a, double b) {
		PolynomialImp result = (PolynomialImp) this.indefiniteIntegral();


		return result.evaluate(b) - result.evaluate(a);
	}



	@Override
	public boolean equals(Polynomial P) {
		return this.toString().equals((P.toString()));
	}
	@Override
	public Polynomial subtract(Polynomial P2) {
		PolynomialImp p2 = (PolynomialImp) P2;
		PolynomialImp res = new PolynomialImp();
		boolean tFound;//true when it finds the term
		for (Term term : this) {
			res.List.add(term);
		}

		for (Term term1 : p2) {
			tFound = false;
			for (Term term2 : res) {
				if (term1.getExponent()==term2.getExponent()) {
					if(term2.getCoefficient()-term1.getCoefficient()!=0)
						res.List.add(new TermImp(term2.getCoefficient()-term1.getCoefficient(),term1.getExponent()));
					res.List.remove(term2);
					tFound = true;
					break;
				}
			}
			if(!tFound) {
				res.List.add(new TermImp(-term1.getCoefficient(),term1.getExponent()));
			}
		}
		if(res.List.isEmpty())
			res.List.add(new TermImp(0,0));

		return OS(res);
	}

	private PolynomialImp OS(PolynomialImp polynomial){//orders and simplyfies the polynomials
		Term t1;
		Term t2;
		for(int i = 0; i < polynomial.List.size(); i++) {
			t1 = polynomial.List.get(i);
			for(int j = i+1; j < polynomial.List.size(); j++) {
				t2 = polynomial.List.get(j);
				if(t1.getExponent()<t2.getExponent()) {
					TermImp temp = (TermImp) t1;
					polynomial.List.set(i, t2);
					polynomial.List.set(j, temp);	
				}else if (t1.getExponent()==t2.getExponent()) {
					TermImp temp = (TermImp) t1;
					temp.setCoefficient(temp.getCoefficient()+t2.getCoefficient());
					polynomial.List.remove(j);
				}
			}
		}
		return polynomial;

	}
}