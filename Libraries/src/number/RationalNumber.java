package number;

// óLóùêî
public class RationalNumber {

	public long numerator;
	public long denominator;
	
	public RationalNumber(long numerator, long denominator) {
		long gcd = _gcd(numerator, denominator);
		this.numerator = numerator/gcd;
		this.denominator = denominator/gcd;
	}
	public boolean equals(Object that) {
		RationalNumber target = (RationalNumber)that;
		return this.numerator==target.numerator && this.denominator==target.denominator;
	}
	public int hashCode() {return toString().hashCode();}
	public String toString() {return numerator+"/"+denominator;}
	public double toDouble() {return (double)numerator/(double)denominator;}
	public RationalNumber add(RationalNumber rb) {
		long n = this.numerator*rb.denominator + this.denominator*rb.numerator;
		long d = this.denominator*rb.denominator;
		return new RationalNumber(n, d);
	}
	public RationalNumber mul(RationalNumber rb) {
		return new RationalNumber(this.numerator*rb.numerator, this.denominator*rb.denominator);
	}
	static long _gcd(long x, long y) {if (y==0L) {return x;}return _gcd(y,x%y);}
}
