/*
 * (C) 2016 CSE2010 HW #2
 * 
 * Complete the codes ...
 */
import java.util.*;
import java.lang.Math;

/*
 * class DLinkedPolynomial
 */
public class DLinkedPolynomial implements Polynomial {
	DLinkedList<Term> poly;
    DLinkedPolynomial() {
    	poly = new DLinkedList<Term>();
    }

    @Override
    public int getDegree() {
    	int max = 0;
    	Term a = new Term(0,max);
    	DNode<Term> b = new DNode<Term>(null,null,null);
    	b = poly.getFirstNode();
    	for(int i=0;i<poly.getSize();i++){
    		if(i == poly.getSize()-1){
    			break;
    		}
    		if(b.getInfo().expo > max){
    			max = b.getInfo().expo;
    		}
    		b = poly.getNextNode(b);
    	}
    	return max;
    }

    @Override
    public double getCoefficient(int exponent) {
    	Term t = new Term(0,exponent);
    	DNode<Term> n = new DNode<Term>(t,null,null);
    	DNode<Term> f = new DNode<Term>(null,null,null);
    	int count = 0;
    	if(poly.isEmpty()){
    		return 0;
    	}
    	else{
    		f = poly.getFirstNode();
    		for(int i=0;i<poly.getSize();i++){
    			if(f.getInfo().expo == n.getInfo().expo){
    				break;
    			}else{
    				f = poly.getNextNode(f);
    				count ++;
    				if(count == poly.getSize()){
    					return 0;
    				}
    			}
    		}
    		return f.getInfo().coeff;
    	}
    }

    @Override
    public Polynomial padd(Polynomial p) {
    	Polynomial f = PolyFactory.createPolyImpl(Kind.DOUBLE);
    	Term a = new Term(poly.getFirstNode().getInfo().coeff,poly.getFirstNode().getInfo().expo);
    	DNode<Term> b = new DNode(null,null,null);
    	b = poly.getFirstNode();
    	for(int i=0; i<poly.getSize();i++){
    		f.addTerm(a);
    		if(i == poly.getSize()-1){
    			break;
    		}
    		b = poly.getNextNode(b);
    		a = new Term(b.getInfo().coeff,b.getInfo().expo);
    	}
    	for(int i=0; i<=p.getDegree();i++){
    		Term n = new Term(p.getCoefficient(i),i);
    		if(p.getCoefficient(i)==0){
    		}
    		else{
    			f.addTerm(n);
    		}
    	}
    	return f;
    }

    Term multTerms(Term x, Term y) {
        return new Term(x.coeff * y.coeff, x.expo + y.expo);
    }

    @Override
    public Polynomial pmult(Polynomial p) {
    	Polynomial f = PolyFactory.createPolyImpl(Kind.DOUBLE);
    	Term a = new Term(poly.getFirstNode().getInfo().coeff,poly.getFirstNode().getInfo().expo);
    	DNode<Term> b = new DNode(null,null,null);
    	b = poly.getFirstNode();
    	for(int i=0; i<poly.getSize();i++){
    		f.addTerm(a);
    		if(i == poly.getSize()-1){
    			break;
    		}
    		b = poly.getNextNode(b);
    		a = new Term(b.getInfo().coeff,b.getInfo().expo);
    	}
    	Polynomial g = PolyFactory.createPolyImpl(Kind.DOUBLE);
    	for(int i=0; i<=p.getDegree();i++){
    		Term n = new Term(p.getCoefficient(i),i);
    		if(p.getCoefficient(i)==0){
    		}
    		else{
    			g.addTerm(n);
    			
    		}
    	}
    	Polynomial h = PolyFactory.createPolyImpl(Kind.DOUBLE);
    	for(int i=0; i<=f.getDegree();i++){
    		for(int j=0;j<=g.getDegree();j++){
    			if(f.getCoefficient(i)==0 || g.getCoefficient(j)==0){
    				
    			}
    			else{
    				Term temp = new Term(f.getCoefficient(i)*g.getCoefficient(j),i+j);
    				h.addTerm(temp);
    			}
    		}
    	}
    	return h;
        
    }

    @Override
    public void addTerm(Term term) {
    	DNode<Term> n = new DNode<Term>(term,null,null);
    	DNode<Term> f = new DNode<Term>(null,null,null);
    	int count =0;
    	if(poly.getSize() == 0){
    		poly.addFirst(n);
    	}else{
    		f = poly.getFirstNode();
    		if(poly.getSize() == 1){
    			if(f.getInfo().expo>n.getInfo().expo){
    				poly.addLast(n);
    			}else if(f.getInfo().expo<n.getInfo().expo){
    				poly.addFirst(n);
    			}else if(f.getInfo().expo == n.getInfo().expo){
    				f.getInfo().coeff += n.getInfo().coeff;
    				if (f.getInfo().coeff == 0){
						poly.remove(f);
					}
    			}
    		}else{
    			for(int i=0; i<poly.getSize();i++){
    				if(count ==poly.getSize()-1){
    					if(f.getInfo().expo>n.getInfo().expo){
    						poly.addLast(n);
    						break;
    					}else if(f.getInfo().expo < n.getInfo().expo){
    						poly.addBefore(f, n);
    						break;
    					}else if(f.getInfo().expo == n.getInfo().expo){
    						f.getInfo().coeff += n.getInfo().coeff;
    						if (f.getInfo().coeff == 0){
    							poly.remove(f);
    						}
    						break;
    					}
    				}else{
    					if(f.getInfo().expo>n.getInfo().expo){
    						f = poly.getNextNode(f);
    						count ++;
    					}else if(f.getInfo().expo<n.getInfo().expo){
    						poly.addBefore(f, n);
    						break;
    					}else if(f.getInfo().expo == n.getInfo().expo){
    						f.getInfo().coeff += n.getInfo().coeff;
    						if (f.getInfo().coeff == 0){
    							poly.remove(f);
    						}
    						break;
    					}
    				}
    			}
    		}
    	}
    	
    }	

    @Override
    public double evaluate(double val) {
        double sum = 0;

        DNode<Term> current = poly.getFirstNode();
        do {
            sum += current.getInfo().coeff * Math.pow(val, current.getInfo().expo);
            current = poly.getNextNode(current);
        } while (current != null);

        return sum;
    }

    @Override
    public String dump() {
        if (poly.isEmpty())
            return "Empty Polynomial";
        else {
            StringBuilder builder = new StringBuilder();
            DNode<Term> current = poly.getFirstNode();
            do {
            	builder.append("(" + current.getInfo().coeff + ", " + current.getInfo().expo + ") ");
                current = poly.getNextNode(current);
            } while (current != null);
            builder.append("\n");
            return builder.toString();
        }
    }

}
