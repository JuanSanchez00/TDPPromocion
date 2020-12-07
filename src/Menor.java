public class Menor  implements PluginFunction {
	
	public float doOperation(float n1, float n2) {
		float result = 0;
		result = Math.min(n1, n2);
		return result;
	}

	public String getPluginName() {
		return "Menor";
	}

}