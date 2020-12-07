public interface PluginFunction {
	
	public abstract float doOperation(float n1, float n2) throws InvalidOperationException;

	public abstract String getPluginName();
}
