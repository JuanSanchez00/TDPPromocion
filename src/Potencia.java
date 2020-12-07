public class Potencia implements PluginFunction {
	
	public float doOperation(float n1, float n2) throws InvalidOperationException {
		float result = 0;
		result = (float) Math.pow(n1, n2);
		return result;
	}

	public String getPluginName() {
		return "Potencia";
	}
}
