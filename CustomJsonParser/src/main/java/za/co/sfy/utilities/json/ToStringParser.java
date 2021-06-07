package za.co.sfy.utilities.json;

import java.beans.*;
import java.lang.reflect.*;
import java.util.*;
import kiss.util.Reflect;

public class ToStringParser {

	public String parseObjectToString(Object classInstanceToStringify) {
		StringBuilder sb = new StringBuilder("{");
		for (String methodName : findAllGetMethodsOnObject(classInstanceToStringify)) {
			Class<?> rt = findReturnTypeForMethodOnObject(methodName, classInstanceToStringify);
			switch (rt.toString()) {
			case "long":
				String pd = primitiveDecoder(classInstanceToStringify, methodName);
				sb.append(pd);
				break;
			case "class java.lang.String":
				String sd = stringDecoder(classInstanceToStringify, methodName);
				sb.append(sd);
				break;
			case "interface java.util.List":
				sb.append("\"" + findAttributeNameForMethodOnObject(methodName, classInstanceToStringify) + "\":[{");
				List<?> listObject = listObject(classInstanceToStringify, methodName);
				if (listObject.isEmpty()) {
					sb.append("");
				} else {
					String ls = listDecoder(mapOfObject(rt, methodName, classInstanceToStringify), methodName);
					sb.append(ls);
				}
				break;
			default:
				String ob = objectDecoder(mapOfObject(rt, methodName, classInstanceToStringify), methodName,
						classInstanceToStringify);
				sb.append(ob);
				break;
			}
		}
		return checks(sb).toString();
	}

	private List<String> findAllGetMethodsOnObject(Object classInstanceToStringify) {
		List<String> retList = new ArrayList<String>();
		for (Method method : Reflect.getDeclaredMethodsInOrder(classInstanceToStringify.getClass())) {
			if (method.getName().startsWith("get")) {
				retList.add((String) method.getName());
			}
		}
		return retList;
	}

	private List<?> listObject(Object classInstanceToStringify, String methodName) {
		List<?> resultListM = null;
		Method m = null;
		try {
			m = classInstanceToStringify.getClass().getMethod(methodName);
			Object resultM = m.invoke(classInstanceToStringify);
			resultListM = (ArrayList<?>) resultM;

		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return resultListM;

	}

	private Class<?> findReturnTypeForMethodOnObject(String methodName, Object classInstanceToStringify) {
		Class<?> returnType = null;
		try {
			Method method = classInstanceToStringify.getClass().getMethod(methodName);
			returnType = method.getReturnType();
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return returnType;
	}

	private String findAttributeNameForMethodOnObject(String methodName, Object classInstanceToStringify) {
		String methodNam = null;
		try {
			Method method = classInstanceToStringify.getClass().getMethod(methodName);
			Class<?> clazz = method.getDeclaringClass();
			BeanInfo info = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] props = info.getPropertyDescriptors();
			for (PropertyDescriptor pd : props) {
				if (method.equals(pd.getWriteMethod()) || method.equals(pd.getReadMethod())) {
					methodNam = pd.getDisplayName();
				}
			}
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IntrospectionException e) {
			e.printStackTrace();
		}
		return methodNam;
	}

	private HashMap<List<String>, List<Object>> mapOfObject(Class<?> returnTypeClass, String methodName,
			Object classInstanceToStringify) {
		try {
			switch ((String) returnTypeClass.toString()) {
			case "interface java.util.List":
				HashMap<List<String>, List<Object>> hmapLi = new HashMap<List<String>, List<Object>>();
				List<String> keyObjectLi = new ArrayList<>();
				List<Object> valueObjectLi = new ArrayList<>();
				Method methodLi = classInstanceToStringify.getClass().getDeclaredMethod(methodName);
				String fieldNameLi = findAttributeNameForMethodOnObject(methodName, classInstanceToStringify);
				List<?> valueLi = (List<?>) methodLi.invoke(classInstanceToStringify);
				keyObjectLi.add(fieldNameLi);
				valueObjectLi.add(valueLi);
				hmapLi.put(keyObjectLi, valueObjectLi);
				return hmapLi;
			default:
				HashMap<List<String>, List<Object>> hmapO = new HashMap<List<String>, List<Object>>();
				List<String> keyObjectO = new ArrayList<>();
				List<Object> valueObjectO = new ArrayList<>();
				Method methodO = classInstanceToStringify.getClass().getDeclaredMethod(methodName);
				String fieldNameO = findAttributeNameForMethodOnObject(methodName, classInstanceToStringify);
				Object valueO = (Object) methodO.invoke(classInstanceToStringify);
				keyObjectO.add(fieldNameO);
				valueObjectO.add(valueO);
				hmapO.put(keyObjectO, valueObjectO);
				return hmapO;
			}
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String primitiveDecoder(Object classInstanceToStringify, String methodName) {
		StringBuilder sb = new StringBuilder();
		try {
			Method methodO = classInstanceToStringify.getClass().getDeclaredMethod(methodName);
			String fieldNameO = findAttributeNameForMethodOnObject(methodName, classInstanceToStringify);
			Object valueO = (Object) methodO.invoke(classInstanceToStringify);
			sb.append("\"" + fieldNameO + "\":").append(valueO.toString() + ",");
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	private String stringDecoder(Object classInstanceToStringify, String methodName) {
		StringBuilder sb = new StringBuilder();
		try {
			Method methodO = classInstanceToStringify.getClass().getDeclaredMethod(methodName);
			String fieldNameO = findAttributeNameForMethodOnObject(methodName, classInstanceToStringify);
			Object valueO = (Object) methodO.invoke(classInstanceToStringify);
			sb.append("\"" + fieldNameO + "\":").append("\"" + valueO.toString() + "\",");
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	private String listDecoder(HashMap<List<String>, List<Object>> map, String methodName) {
		StringBuilder sb = new StringBuilder();
		try {
			for (Object mapValues : map.values()) {
				for (Object valueArr : (List<Object>) mapValues) {
					for (Object valueObject : (List<Object>) valueArr) {
						for (String methodName1 : findAllGetMethodsOnObject(valueObject)) {
							switch (findReturnTypeForMethodOnObject(methodName1, valueObject).toString()) {
							case "long":
								sb.append(primitiveDecoder(valueObject, methodName1));
								break;
							case "class java.lang.String":
								sb.append(stringDecoder(valueObject, methodName1));
								break;
							case "interface java.util.List":
								Method methodOL = valueObject.getClass().getDeclaredMethod(methodName1);
								String fieldNameOL = findAttributeNameForMethodOnObject(methodName1, valueObject);
								Object valueOL = (Object) methodOL.invoke(valueObject);
								sb.append(",\"" + fieldNameOL + "\":").append("" + valueOL.toString() + "},{");
								break;
							default:
								String chc = findAttributeNameForMethodOnObject(methodName1, valueObject);
								sb.append("\"" + chc + "\":{");
								Method methodOB = valueObject.getClass().getDeclaredMethod(methodName1);
								Object valueOB = (Object) methodOB.invoke(valueObject);
								for (String methodNameA : findAllGetMethodsOnObject(valueOB)) {
									switch (findReturnTypeForMethodOnObject(methodNameA, valueOB).toString()) {
									case "long":
										sb.append(primitiveDecoder(valueOB, methodNameA));
										break;
									case "class java.lang.String":
										sb.append(stringDecoder(valueOB, methodNameA));
										break;
									case "interface java.util.List":
										break;
									}
								}
								sb.deleteCharAt(sb.length() - 1);
								sb.append("}");
							}
						}
					}
				}
			}
		} catch (IllegalArgumentException | SecurityException | NoSuchMethodException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		sb.append("]");
		return sb.toString();
	}

	private StringBuilder checks(StringBuilder sb) {
		if (sb.toString().contains(":[")) {
			sb.deleteCharAt(sb.length() - 3);
			sb.deleteCharAt(sb.length() - 2);
			sb.append("}");
		}
		if (sb.toString().contains(":]}")) {
			sb.insert(sb.lastIndexOf("]"), "[");
		} 

		if (!(sb.toString().contains(":{"))) {
			sb.deleteCharAt(sb.length() - 1);
			sb.append("}");
		}
		if (sb.toString().contains("\"{}")) {
			sb.deleteCharAt(sb.length() - 2);
			sb.insert(sb.length() - 1, ":[]");
		}
		return sb;
	}

	@SuppressWarnings("unchecked")
	private String objectDecoder(HashMap<List<String>, List<Object>> map, String methodName,
			Object classInstanceToStringify) {
		StringBuilder sb = new StringBuilder(); 
		try {
			for (Object mapValues : map.values()) {
				for (Object valueObject : (List<Object>) mapValues) {
					String n = findAttributeNameForMethodOnObject(methodName, classInstanceToStringify);
					sb.append("\"" + n + "\":{");
					List<String> AllGetMethodsOnObject = findAllGetMethodsOnObject(valueObject);
					for (String methodName1 : AllGetMethodsOnObject) {
						switch (findReturnTypeForMethodOnObject(methodName1, valueObject).toString()) {
						case "long":
							sb.append(primitiveDecoder(valueObject, methodName1));
							break;
						case "class java.lang.String":
							sb.append(stringDecoder(valueObject, methodName1));
							break;
						case "interface java.util.List":
							break;
						}
					}
				}
			}
		} catch (IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
		}
		if (sb.toString().contains(":{")) {
			sb.append("},");
			sb.deleteCharAt(sb.length() - 3);
		}
		return sb.toString();
	}
}
