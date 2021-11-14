package ua.com.alevel.config;

import org.reflections.Reflections;
import ua.com.alevel.db.BookDB;

import java.util.Map;
import java.util.Set;
import java.util.Map;
import java.util.Set;

public class ObjectFactory {

	private static ObjectFactory instance;

	private static final Reflections scan = new Reflections("ua.com.alevel");

	public static ObjectFactory getInstance() {
		if (instance == null) {
			instance = new ObjectFactory();
		}
		return instance;
	}

	public static <IFC> IFC getCurrentObject(Class<IFC> ifc) {
		if (ifc.isInterface()) {
			Set<Class<? extends IFC>> imls = scan.getSubTypesOf(ifc);
			for (Class<? extends IFC> iml : imls) {

				if (!iml.isAnnotationPresent(Deprecated.class)) {
					try {
						return iml.getDeclaredConstructor().newInstance();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		throw new RuntimeException("class not found by interface");
	}
}