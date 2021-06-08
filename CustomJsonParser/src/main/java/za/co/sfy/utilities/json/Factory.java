package za.co.sfy.utilities.json;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractFactory {

	abstract Object getObject(Class<?> jsonClass);

	abstract List<?> getList();

}

class ObjectFactorySFY extends AbstractFactory {

	@Override
	Object getObject(Class<?> jsonClass) {
		Object newInst = null;
		try {
			newInst = jsonClass.newInstance();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
		return newInst;
	}

	@Override
	List<?> getList() {
		List<?> list = new ArrayList<>();
		return list;
	}
} 

class FactoryProvider {
	public static AbstractFactory getFactory() {
		return new ObjectFactorySFY();
	}
}

