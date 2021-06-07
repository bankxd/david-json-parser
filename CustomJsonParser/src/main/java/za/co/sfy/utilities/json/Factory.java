package za.co.sfy.utilities.json;

public class Factory {

}
//abstract class JsonObjectA {
//
//	public void setField(Object factoryInstance1, Field field, HashMap<String, String> mapifyO1, String json) {
//
//	}
//
//	private void clone(Object factoryInstance1) {
//
//	}
//
//}
//
//class JsonObject extends JsonObjectA {
//
//	public Field[] getAllFields(Class<?> jsonClass) {
//		Field[] declaredFields = jsonClass.getDeclaredFields();
//		for (Field field : declaredFields) {
//			field.setAccessible(true);
//		}
//		return declaredFields;
//	}
//
//	Object clone(Object factoryInstance1) {
//		Object cloneBean = null;
//		try {
//			new BeanUtils();
//			cloneBean = BeanUtils.cloneBean(factoryInstance1);
//		} catch (IllegalAccessException | InstantiationException | InvocationTargetException
//				| NoSuchMethodException e) {
//			e.printStackTrace();
//		}
//		return cloneBean;
//	}
//	
//
//	public void setField(Object factoryInstance1, Field field, HashMap<String, String> mapifyO1, String json) {
//		try {
//			Class<?> type = field.getType();
//			switch (type.toString()) {
//			case "long":
//				field.setLong(factoryInstance1, Long.parseLong(mapifyO1.get(field.getName())));
//				break;
//			case "class java.lang.String":
//				field.set(factoryInstance1, (String) mapifyO1.get(field.getName()));
//				break;
//			case "interface java.util.List":
//				if (json.contains(":[") && json != null) {
//					List<?> listInst = new ArrayList<>();
//					Method add = listInst.getClass().getDeclaredMethod("add", Object.class);
//					if (json.contains("},{") && json != null) {
//						int countMatches = StringUtils.countMatches(json, "},{");
//						for (int h = 0; h < (countMatches + 1); h++) {
//							Object newInstance = factoryInstance1.getClass().newInstance();
//							new ToObjectParser().setInnerListObject(newInstance,
//									new ToObjectParser().getListObjectObject(json, h),
//									new ToObjectParser().getListObject(json, h), json);
//							add.invoke(listInst, newInstance);
//						}
//						field.set(factoryInstance1, listInst);
//					}
//				}
//				break;
//			default:
//				List<String> innerObject = new ToObjectParser().stripString(json);
//				String innerObjP = innerObject.get(1);
//				HashMap<String, String> mapifyOO1 = new ToObjectParser().mapify(innerObjP);
//				new ToObjectParser().setInnerObject(json, field, factoryInstance1, mapifyOO1);
//				break;
//			}
//		} catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
//				| NoSuchMethodException | InvocationTargetException e) {
//			e.printStackTrace();
//		}
//
//	}
//}
//
//	abstract class AbstractFactory {
//
//		abstract JsonObject getObject(String json, Class<?> jsonClass);
//
//		abstract List<?> getList();
//
//	}
//
//	class ObjectFactorySFY extends AbstractFactory {
//
//		@Override
//		JsonObject getObject(String json, Class<?> jsonClass) {
//			return new JsonObject(json, jsonClass);
//		}
//
//		@Override
//		List<?> getList() {
//			List<?> list = new ArrayList<>();
//			return list;
//		}
//	}
//
//	public static class FactoryProvider {
//		public static AbstractFactory getFactory() {
//			return new ObjectFactorySFY();
//		}
//	}
//}
//