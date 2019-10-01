
public class NBody {

	private static double T, dt;
	private static String filename;
	private static String imgFile = "./images/starfield.jpg";

	public static void main(String[] args) {

		T = Double.parseDouble(args[0]);
		dt = Double.parseDouble(args[1]); 
		filename = args[2];

		double radius = readRadius(filename);
		Body[] bods = readBodies(filename);
		//System.out.println(bods.length);
		
		//System.out.println(bods[0].mass);
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius,  radius);

		//this i for adding the images.
		for(int i = 0; i<bods.length; i++) {
			bods[i].imgFileName = "./images/" + bods[i].imgFileName ;
			//bods[i].draw();
		}
		

		double[] xForces = new double[bods.length];
		double[] yForces = new double[bods.length];

		for(double t = 0; t < T; t = t+dt) {
			StdDraw.clear();
			StdDraw.picture(radius/2, radius/2, imgFile);

			//storing all the forces for all the bodies
			for (int u = 0; u < bods.length; u++) { 
				xForces[u] = bods[u].calcNetForceExertedByX(bods);
				yForces[u] = bods[u].calcNetForceExertedByY(bods);
			}

			for(int v = 0; v < bods.length; v++) {
				//System.out.println(xForces[v]);
				//System.out.println(yForces[v]);
				bods[v].update(t, xForces[v], yForces[v]);
				bods[v].draw();
			}

			StdDraw.show();
			StdDraw.pause(50);

		}
		
		StdOut.printf("%d\n", bods.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bods.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  bods[i].xxPos, bods[i].yyPos, bods[i].xxVel,
		                  bods[i].yyVel, bods[i].mass, bods[i].imgFileName);   
		}

	}
	
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		in.readInt(); //just to advance the in. there might be a better way.
		double r = in.readDouble();

		
		return r;
	}
	
	public static Body[] readBodies(String fileName) {
		In in  = new In(fileName);
		int n = in.readInt();
		in.readDouble();
		  Body[] bodArr = new Body[n];
		  double xP, yP,xV,yV,m;
		  String img;
		  for(int i=0; i<n; i++) {
			  xP = in.readDouble();
			  yP = in.readDouble();
			  xV = in.readDouble();
			  yV = in.readDouble();
			  m = in.readDouble();
			  img = in.readString();
			  bodArr[i] = new Body(xP, yP, xV, yV, m, img);
		  }

		//System.out.println(bodArr.length);
		return bodArr;
	}
}
