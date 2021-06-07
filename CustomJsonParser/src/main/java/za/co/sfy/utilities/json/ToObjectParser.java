package za.co.sfy.utilities.json;

import java.lang.reflect.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import za.co.sfy.domain.Address;
import za.co.sfy.domain.Person;

import java.util.*;
import java.util.Map.Entry;

public class ToObjectParser {

	@Test
	public Object parseStringToObject(String json, Class<?> jsonClass) {
		AbstractFactory factory = FactoryProvider.getFactory();
		HashMap<String, String> mapifyO1 = mapify(getOuterObject(json));
		Object objInst = null;
		try {
			objInst = factory.getObject(jsonClass);
			for (Field field : objInst.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Class<?> type = field.getType();
				switch (type.toString()) {
				case "long":
					field.setLong(objInst, Long.parseLong(mapifyO1.get(field.getName())));
					break;
				case "class java.lang.String":
					field.set(objInst, (String) mapifyO1.get(field.getName()));
					break;
				case "interface java.util.List":
					if (json.contains(":[") && json != null) {
						List<?> listInst = factory.getList();
						Method add = listInst.getClass().getDeclaredMethod("add", Object.class);
						if (json.contains("},{") && json != null) {
							int countMatches = StringUtils.countMatches(json, "},{");
							for (int h = 0; h < (countMatches + 1); h++) {
								Object newInstance = factory.getObject(jsonClass);
								setInnerListObject(newInstance, getListObjectObject(json, h), getListObject(json, h),
										json);
								add.invoke(listInst, newInstance);
							}
							field.set(objInst, listInst);
						}
					}
					break;
				default:
					List<String> innerObject = new ToObjectParser().stripString(json);
					String innerObjP = innerObject.get(1);
					HashMap<String, String> mapifyOO1 = new ToObjectParser().mapify(innerObjP);
					setInnerObject(json, field, objInst, mapifyOO1);
					break;
				}
			}
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return objInst;
	}

	protected Object setInnerListObject(Object newInstance, HashMap<String, String> mapify2,
			HashMap<String, String> mapify3, String json) throws IllegalArgumentException, IllegalAccessException {
		Object newObj1 = null;
		for (Field field2 : newInstance.getClass().getDeclaredFields()) {
			field2.setAccessible(true);
			Class<?> type2 = field2.getType();
			switch (type2.toString()) {
			case "long":
				field2.setLong(newInstance, Long.parseLong(mapify3.get(field2.getName())));
				break;
			case "class java.lang.String":
				field2.set(newInstance, (String) mapify3.get(field2.getName()));
				break;
			case "interface java.util.List":
				break;
			default:
				newObj1 = setInnerObject(json, field2, newInstance, mapify2);
				break;
			}
		}
		return newObj1;
	}

	protected Object setInnerObject(String json, Field field, Object objInst, HashMap<String, String> mapify1) {
		Object newObj = null;
		try {
			if (json.contains(":{") && json != null) {
				String output = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
				Method method = objInst.getClass().getDeclaredMethod("get" + output);
				String name = method.getGenericReturnType().toString();
				String substring = name.substring(6);
				Class<?> c = Class.forName(substring);
				newObj = c.newInstance();
				for (Field field1 : newObj.getClass().getDeclaredFields()) {
					field1.setAccessible(true);
					Class<?> type1 = field1.getType();
					switch (type1.toString()) {
					case "long":
						field1.setLong(newObj, Long.parseLong(mapify1.get(field1.getName())));
						break;
					case "class java.lang.String":
						field1.set(newObj, (String) mapify1.get(field1.getName()));
						break;
					}
				}
				field.set(objInst, newObj);
			}
		} catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| NoSuchMethodException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return newObj;
	}

	protected String getOuterObject(String json) {
		String outerObj = null;
		if (!(json.contains(":{"))) {
			outerObj = json.replaceAll("\\{|\\}|\\[|\\]|\"", "");
			return outerObj;
		}
		List<String> innerObject = stripString(json);
		String innerObjC = innerObject.get(0);
		if (innerObject.get(0).contains(":[{")) {
			List<String> listInnerObject1 = stripList(innerObject.get(0));
			List<String> listInnerObject2 = stripList(listInnerObject1.get(0));
			outerObj = listInnerObject2.get(0);
			return outerObj;
		} else if (!(json.contains(":{") && json.contains(":[{"))) {
			outerObj = innerObjC;
			return outerObj;
		} else {
			outerObj = innerObjC;
			return outerObj;
		}
	}

	protected HashMap<String, String> getListObject(String json, int iteration) {
		List<String> innerObject = stripString(json);
		List<String> listInnerObject1 = stripList(innerObject.get(0));
		String innerObjK = listInnerObject1.get(2);
		HashMap<String, String> mapifyO0 = mapify(innerObjK);
		List<String> listInnerObject2 = stripList(listInnerObject1.get(0));
		String innerObjR = listInnerObject2.get(2);
		HashMap<String, String> mapifyO1 = mapify(innerObjR);
		if ("mapifyO0".contains(String.valueOf(iteration))) {
			return mapifyO0;
		} else if ("mapifyO1".toString().contains(String.valueOf(iteration))) {
			return mapifyO1;
		} else {
			return null;
		}
	}

	protected HashMap<String, String> getListObjectObject(String json, int iteration) {
		List<String> innerObject = stripString(json);
		List<String> listInnerObject1 = stripList(innerObject.get(0));
		String innerInnerObjK = listInnerObject1.get(1);
		HashMap<String, String> mapifyOO0 = mapify(innerInnerObjK);
		List<String> listInnerObject2 = stripList(listInnerObject1.get(0));
		String innerInnerObjR = listInnerObject2.get(1);
		HashMap<String, String> mapifyOO1 = mapify(innerInnerObjR);
		if ("mapifyOO0".contains(String.valueOf(iteration))) {
			return mapifyOO0;
		} else if ("mapifyOO1".toString().contains(String.valueOf(iteration))) {
			return mapifyOO1;
		} else {
			return null;
		}
	}

	protected List<String> stripList(String json) {
		List<String> strings = new ArrayList<>();
		HashMap<Integer, Integer> map1 = bracketCount(json);
		String innerListObject = extractSubString(map1, json);
		String excluding = removeSubstringBetweenIndexes(map1, json);
		strings.add(excluding);
		if (innerListObject.contains("{")) {
			HashMap<Integer, Integer> map2 = bracketCount(innerListObject);
			String substring1 = extractSubString(map2, innerListObject);
			strings.add(substring1);
			String innerListObject1 = removeSubstringBetweenIndexes(map2, innerListObject);
			strings.add(innerListObject1);
		}
		return strings;
	}

	protected List<String> stripString(String json) {
		List<String> strings = new ArrayList<>();
		HashMap<Integer, Integer> map1 = bracketCount(json);
		String mainObject = extractSubString(map1, json);
		String updatedString = null;
		String substring1 = null;
		String substring2 = null;
		if (mainObject.contains("{")) {
			HashMap<Integer, Integer> map2 = bracketCount(mainObject);
			substring1 = extractSubString(map2, mainObject);
			if (substring1.contains("{")) {
				HashMap<Integer, Integer> map3 = bracketCount(substring1);
				substring2 = extractSubString(map3, substring1);
				strings.add(substring2);
			} else {
				updatedString = removeSubstringBetweenIndexes(map2, mainObject);
				strings.add(updatedString);
				strings.add(substring1);
			}
		}
		return strings;
	}

	protected String removeSubstringBetweenIndexes(HashMap<Integer, Integer> map, String json) {
		StringBuilder sb = new StringBuilder().append(json);
		String result = null;
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			sb.delete(entry.getKey(), entry.getValue() + 1);
			result = sb.toString();
		}
		return result;
	}

	@Test
	protected HashMap<Integer, Integer> bracketCount(String json) {
		int counter1 = 0;
		int open = 0;
		int close = 0;
		char[] charArray = json.toCharArray();
		HashMap<Integer, Integer> indexMatches = new HashMap<Integer, Integer>();
		if (json != null) {
			for (int i = 0; i < charArray.length; ++i) {
				if (charArray[i] == '{') {
					counter1++;
					if (counter1 == 1) {
						open = -1;
						open = i;
					}
				} else if (charArray[i] == '}') {
					counter1--;
					if (counter1 == 0) {
						close = -1;
						close = i;
						break;
					}
				}
			}
		}
		indexMatches.put(open, close);
		return indexMatches;
	}

	@Test
	private String extractSubString(HashMap<Integer, Integer> indexMatches, String json) {
		String subString = null;
		for (Entry<Integer, Integer> entry : indexMatches.entrySet()) {
			subString = json.substring(entry.getKey() + 1, entry.getValue());
		}
		return subString;
	}

	@Test
	public HashMap<String, String> mapify(String jsonString) {
		HashMap<String, String> hmap = new HashMap<String, String>();
		for (String s : jsonString.replaceAll("\\{|\\}|\\[|\\]|\"", "").split(",")) {
			hmap.put(StringUtils.substringBefore(s, ":"), StringUtils.substringAfter(s, ":"));
		}
		return hmap;
	}

	public static void main(String[] args) {
		Address address5 = new Address(5, "Westwood Drive", "JHB");
		Address address2 = new Address(2, "4th Ave", "CPT");
		List<Person> children1 = new ArrayList<Person>();
		List<Person> children2 = new ArrayList<Person>();
		List<Person> children3 = new ArrayList<Person>();
		Person child1 = new Person(2, "Kelly", "Pan", address2, children2);
		Person child2 = new Person(3, "Roger", "Pan", address5, children3);
		children1.add(child1);
		children1.add(child2);
		Person person1 = new Person(1, "Peter", "Pan", address2, children1);

		Object parseStringToObject = new ToObjectParser().parseStringToObject(new Gson().toJson(person1),
				person1.getClass());
		System.out.println("ss");
	}
}
