
public class Body {
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;
	
	public Body(double xP , double yP, double xV,
			double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName =  img;
	}

	//A constuctor that makes a copy of the taken in object.
	public Body(Body b) {
		 xxPos = b.xxPos;
		 yyPos = b.yyPos;
		 xxVel = b.xxVel;
		 yyVel = b.yyVel;
		 mass = b.mass;
		 imgFileName =  b.imgFileName;
	}
	
	//this method should do the calculate the distance between the supplied body and the current body.
	public double calcDistance(Body b) {
		Body a = this;
		double xdiff = b.xxPos - a.xxPos;
		double ydiff = b.yyPos - a.yyPos;
		double rsquared = Math.pow(xdiff, 2) + Math.pow(ydiff, 2);

		return Math.sqrt(rsquared);
	}
	
	//force exerted on this body by the given body.
	public double calcForceExertedBy(Body b) {
		double g = 6.67 * Math.pow(10,-11); 
		Body a = this;
		double dist = a.calcDistance(b);
		double f = (g * a.mass * b.mass)/Math.pow(dist, 2);
		return f;
	}
	
	public double calcForceExertedByX(Body b) {
		Body a = this;
		double f = a.calcForceExertedBy(b);
		double xdiff = b.xxPos - a.xxPos;
		double r = b.calcDistance(a);
		double f1 = f * xdiff / r;
		return f1;
	}
	
	public double calcForceExertedByY(Body b) {
		Body a = this;
		double f = a.calcForceExertedBy(b);
		double ydiff = b.yyPos - a.yyPos;
		double r = a.calcDistance(b);
		double f1 = f * ydiff / r;
		return f1;
	}
	
	public double calcNetForceExertedByX(Body[] bods) {
		double totalForce = 0;
		Body a = this;
		for(int i=0; i<bods.length; i++) {
			if(a.equals(bods[i])) {
				continue;
			}
			totalForce += a.calcForceExertedByX(bods[i]);
		}
		return totalForce;
	}
	
	public double calcNetForceExertedByY(Body[] bods) {
		double totalForce = 0;
		Body a = this;
		for(int i=0; i<bods.length; i++) {
			if(a.equals(bods[i])) {
				continue;
			}
			totalForce += a.calcForceExertedByY(bods[i]);
		}
		return totalForce;
	}
	
	//how much forces exerted on the body will cause that body to accelerate.
	//resulting change in the body's velocity and position in a small period of time.
	//current intuition: it returns the updated position.

	public void update(double dt, double fX, double fY) {
		//calculating the new velocity
		double vx = this.xxVel;
		double vy = this.yyVel;
		double xPos = this.xxPos;
		double yPos = this.yyPos;
		double m = this.mass;
		
		double ax = fX/m;
		double ay = fY/m;
		
		double newVelX = vx + (dt * ax);
		double newVelY = vy + (dt * ay);
		
		double newPosX = xPos + dt*newVelX;
		double newPosY = yPos + dt*newVelY;

		this.xxVel = newVelX;
		this.yyVel = newVelY;

		this.xxPos = newPosX;
		this.yyPos = newPosY;
	}
	
	public void draw() {
		Body a = this;
		StdDraw.picture(a.xxPos, a.yyPos, a.imgFileName);
	}
	
}
