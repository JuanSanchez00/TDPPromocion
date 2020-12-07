public class Division implements PluginFunction {
	
	public float doOperation(float n1, float n2) throws InvalidOperationException {
		float result = 0;
		if (n2 == 0) {
			throw new InvalidOperationException("ERROR: No es posible dividir por 0.");
		}
		result = n1 / n2;
		return result;
	}

	public String getPluginName() {
		return "División";
	}
}
