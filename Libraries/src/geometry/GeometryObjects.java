package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class GeometryObjects {
	static double EPS = 0.0000000001;
	static boolean equals(double d1, double d2){return Math.abs(d1-d2)<EPS;}
	static boolean isOrthogonal(Vector v1, Vector v2) {return equals(v1.dotProduct(v2),0.0);}
	static boolean isOrthogonal(Segment s1, Segment s2) {return equals(s1.dotProduct(s2),0.0);}
	static boolean isParallel(Vector v1, Vector v2) {return equals(v1.crossProduct(v2),0.0);}
	static boolean isParallel(Segment s1, Segment s2) {return equals(s1.crossProduct(s2),0.0);}
	static Point project(Segment s, Point p) {
		Vector base = s.p2.diff(s.p1);
		double r = p.diff(s.p1).dotProduct(base) / base.norm();
		return s.p1.sum(base.mult(r));
	}
	static Point reflect(Segment s, Point p) {return p.sum(project(s,p).diff(p).mult(2.0));}
	static double getDistance(Point p1, Point p2) {return p1.diff(p2).abs();}
	static double getDistance(Line l, Point p) {return Math.abs(l.p2.diff(l.p1).crossProduct(p.diff(l.p1))) / l.p2.diff(l.p1).abs();}
	static double getDistance(Segment s, Point p) {
		if (s.p2.diff(s.p1).dotProduct(p.diff(s.p1))<0.0) {return p.diff(s.p1).abs();}
		if (s.p1.diff(s.p2).dotProduct(p.diff(s.p2))<0.0) {return p.diff(s.p2).abs();}
		return getDistance(s.toLine(),p);
	}
	static double getDistance(Segment s1, Segment s2) {
		if (intersect(s1,s2)) {return 0.0;}
		return Math.min(Math.min(getDistance(s1,s2.p1), getDistance(s1,s2.p2)), Math.min(getDistance(s2,s1.p1),getDistance(s2,s1.p2)));
	}
	static int ccw(Point lp0, Point lp1, Point p) {
		Vector lv = lp1.diff(lp0);
		Vector pv = p.diff(lp0);
		double cp=0.0;
		if ((cp=lv.crossProduct(pv))>EPS) {return -1;} // counter-clockwise
		if (cp<-EPS) {return 1;} // clockwise
		if (lv.dotProduct(pv)<-EPS) return 2; // online-back
		if (lv.norm() < pv.norm()) return 3; // online-front
		return 0; // on-segment
	}
	static boolean intersect(Point p0, Point p1, Point p2, Point p3) {
		int ccw0=ccw(p0,p1,p2)*ccw(p0,p1,p3);int ccw1=ccw(p2,p3,p0)*ccw(p2,p3,p1);
		return ccw0<=0&&ccw1<=0||ccw0==0&&ccw1==6||ccw0==6&&ccw1==0;
	}
	static boolean intersect(Segment s1, Segment s2){return intersect(s1.p1,s1.p2,s2.p1,s2.p2);}
	static Point getCrossPoint(Segment s1, Segment s2) {
		Vector base = s2.p2.diff(s2.p1);
		double d1 = Math.abs(base.crossProduct(s1.p1.diff(s2.p1)));
		double d2 = Math.abs(base.crossProduct(s1.p2.diff(s2.p1)));
		double t = d1/(d1+d2);
		return s1.p1.sum(s1.p2.diff(s1.p1).mult(t));
	}
	static Point[] getCrossPoints(Circle c, Line l) {
		Point pr = project(l, c.c);
		Vector e = l.p2.diff(l.p1).div(l.p2.diff(l.p1).abs()); 
		double base = Math.sqrt(c.r*c.r-pr.diff(c.c).norm());
		return new Point[]{pr.sum(e.mult(base)), pr.diff(e.mult(base))};
	}
	static double arg(Point p) {return Math.atan2(p.y, p.x);}
	static Point polar(double r, double a){return new GeometryObjects().new Point(r*Math.cos(a),r*Math.sin(a));}
	static Point[] getCrossPoints(Circle c1, Circle c2) {
		double d = c1.c.diff(c2.c).abs();
		double a = Math.acos((c1.r*c1.r+d*d-c2.r*c2.r)/(2*c1.r*d));
		double t = arg(c2.c.diff(c1.c));
		return new Point[]{c1.c.sum(polar(c1.r,t+a)), c1.c.sum(polar(c1.r,t-a))};
	}
	static int contains(Polygon poly, Point p) {
		int nCross=0;
		for (int i=0;i<poly.size();i++) {
			Vector a = poly.get(i).diff(p); 
			Vector b = poly.get((i+1)%poly.size()).diff(p); 
			if (Math.abs(a.crossProduct(b))<EPS && a.dotProduct(b)<EPS) return 1;// 1:on
			if (a.y>b.y){Vector tmp=a;a=b;b=tmp;}
			if (a.y<EPS && b.y>EPS && a.crossProduct(b)>EPS) nCross++;
		}
		return nCross%2==1 ? 2 : 0;// 2:in, 0:out
	}
	static Polygon convexHull(Polygon s) {
		if (s.size()<3) return s;
		Point[] arP = s.toArray(new Point[0]);
		Arrays.sort(arP, new Comparator<Point>() {
			public int compare(Point p0, Point p1) {
				return p0.x==p1.x ? (p0.y<p1.y?-1:p0.y>p1.y?1:0) : p0.x < p1.x ? -1:1;
			}
		});
		
		Polygon high = new GeometryObjects().new Polygon();
		Polygon low  = new GeometryObjects().new Polygon();
		
		high.add(arP[0]);high.add(arP[1]);
		for (int i=2;i<arP.length;i++) {
			int cur=high.size()-1;
			while (high.size()>=2 && ccw(arP[i], high.get(cur-1), high.get(cur))==-1) {
				high.remove(cur);
				cur--;
			}
			high.add(arP[i]);
		}
		
		low.add(arP[arP.length-1]);
		low.add(arP[arP.length-2]);
		for (int i=arP.length-3;i>=0;i--) {
			int cur = low.size()-1;
			while (low.size()>=2 && ccw(arP[i], low.get(cur-1), low.get(cur))==-1) {
				low.remove(cur);
				cur--;
			}
			low.add(arP[i]);
		}
		
//		// clockwise
//		for (int i=0;i<low.size()-1;i++) {
//			high.add(low.get(i));
//		}
//		return high;

		// counter-clockwise
		Polygon res = new GeometryObjects().new Polygon();
		for (int i=low.size()-1;i>=0;i--) {
			res.add(low.get(i));
		}
		for (int i=high.size()-2;i>=0;i--) {
			res.add(high.get(i));
		}
		return res;
	}

	class Point {
		double x; double y;
		public Point(double ax, double ay){x=ax;y=ay;}
		public Vector sum(Point p) { return new Vector(x+p.x,y+p.y); }
		public Vector diff(Point p) { return new Vector(x-p.x,y-p.y); }
		public Vector mult(double k) { return new Vector(x*k,y*k); }
		public Vector div(double k) { return new Vector(x*(1.0/k),y*(1.0/k)); }
		public boolean equals(Point p){return Math.abs(x-p.x)<EPS && Math.abs(y-p.y)<EPS;}
		public String toString(){return "("+x+","+y+")";}
	}
	class Vector extends Point {
		public Vector(double ax, double ay) {super(ax,ay);}
		public Vector(Point p1, Point p2) {super(p1.x-p2.x,p2.x-p2.y);}
		public double norm() {return x*x+y*y;}
		public double abs() {return Math.sqrt(norm());}
		// TODO:
		public boolean isSmallerThan(Vector v) {return x!=v.x?x<v.x:y<v.y;}
		public double dotProduct(Vector v) {return x*v.x+y*v.y;}
		public double crossProduct(Vector v) {return x*v.y - y*v.x;}
	}

	class Segment {
		Point p1; Point p2;
		public Segment(Point ap1, Point ap2){p1=ap1;p2=ap2;}
		public double dotProduct(Segment s) {return (p1.x-p2.x)*(s.p1.x-s.p2.x)+(p1.y-p2.y)*(s.p1.y-s.p2.y);}
		public double crossProduct(Segment s) {return (p1.x-p2.x)*(s.p1.y-s.p2.y) - (p1.y-p2.y)*(s.p1.x-s.p2.x);}
		public String toString(){return p1.toString()+"->"+p2.toString();}
		public Line toLine() {return new Line(p1,p2);}
	}
	class Line extends Segment{public Line(Point ap1, Point ap2){super(ap1,ap2);}}

	class Circle {Point c;double r;Circle(Point ac, double ar){c=ac;r=ar;}public String toString(){return "c:"+c.toString()+",r:"+r;}}
	class Polygon extends ArrayList<Point>{}
	
	
}
