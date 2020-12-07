

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PluginDemo {
	private List<PluginFunction> listaPlugins;
	
	public PluginDemo() {
		
	}
	
	public List<PluginFunction> getPlugins() {
		File archivo = new File("Plugins");
		ClassLoader classLoader;
		Class clase;
		Class[] arregloPlugins;
		PluginFunction plugin;
		String[] archivos;
		try {
			listaPlugins = new ArrayList();
			classLoader = new PluginClassLoader(archivo);
			archivos = archivo.list();
			if (archivos != null) {
				for (int i = 0; i < archivos.length; i++) {
					if(archivos[i].endsWith(".class")) {
						clase = classLoader.loadClass(archivos[i].substring(0, archivos[i].indexOf(".")));
						arregloPlugins = clase.getInterfaces();
						for (int j = 0; j < arregloPlugins.length; j++) {
							if (arregloPlugins[j].getName().equals("PluginFunction")) {
								plugin = (PluginFunction) clase.getDeclaredConstructor().newInstance();
								listaPlugins.add(plugin);
								break;
							}
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return listaPlugins;
	}
	
	public float runPlugin(float n1, float n2, String clase) throws InvalidOperationException {
		PluginFunction pluginActual = null;
		for (PluginFunction p : listaPlugins) {
			if (p.getPluginName().equals(clase)) {
				pluginActual = p;
				break;
			}
		}
		return pluginActual.doOperation(n1, n2);
	}
}
