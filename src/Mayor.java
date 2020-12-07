public class Mayor  implements PluginFunction {
	
	public float doOperation(float n1, float n2) throws InvalidOperationException {
		float result = 0;
		result = Math.max(n1, n2);
		return result;
	}

	public String getPluginName() {
		return "Mayor";
	}

}