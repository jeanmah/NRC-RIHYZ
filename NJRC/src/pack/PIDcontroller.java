package pack;

public class PIDcontroller {
	private double P_constant,I_constant,D_constant;
	private double integral=0, previous_error=0;
	private double integral_cap=10000, integral_minimum=0;
	
	public void setConstants(double P, double I, double D)
	{
		P_constant = P;
		I_constant = I;
		D_constant = D;
	}
	
	public void setConstraints (double IC, double IM)
	{
		integral_cap=IC;
		integral_minimum=IM;
	}
	
	public void setConstants(double P, double D)  // overloaded function for cases where I is not used
	{
		P_constant = P;
		D_constant = D;
	}
	
	public void resetPID()
	{
		integral=0;
		previous_error=0;
	}
	
	public double PIDfeedback(double error)
	{
		if (Math.abs(error) >= integral_minimum)
		{
			integral=integral+error;
		}
		
		if (Math.abs(integral) < integral_minimum)
		{
			integral=0;
		}
		
		double feedback=0;
		
		return feedback;
	}
	
	
	
}
