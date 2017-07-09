package pack;

public class Debug {
	Our_robot robot;
	
	public void debug_SideColorSensor()
	{
		float intensity = robot.SCS.getIntensity();
		System.out.print("Intensity:");
		System.out.println(intensity);
		int colorID = robot.SCS.getColorID();
		System.out.print("colorID");
		System.out.println(colorID);
		System.out.println("Press button to end");
		
	}
}
