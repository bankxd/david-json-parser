package za.co.sfy.utilities.json;

class bin {

	@SuppressWarnings("unused")
	private void com() {
//		import java.util.*;
//
//		import org.apache.commons.lang3.StringUtils;
//
//		import sun.misc.JavaObjectInputStreamAccess;
//
//		import java.beans.BeanInfo;
//		import java.beans.IntrospectionException;
//		import java.beans.Introspector;
//		import java.beans.PropertyDescriptor;
//		import java.lang.reflect.*;
//		import za.co.sfy.domain.Address;
//		import za.co.sfy.domain.Person;
//
//		public class CustomJsonParser{
//
//			Object attributeName = null;
//			Object attributeValue = null;
//
//			List<Object> attributeNames = null;
//			List<Object> attributeValues = null;
//
//			List<Object> listInnerAttributeNamesL = null;
//			List<Object> listInnerAttributeValuesL = null;
//
//			List<Object> innerObjectNames = null;
//			List<Object> innerObjectValues = null;
//
//			List<Object> innerInnerObjectNames = null;
//			List<Object> innerInnerObjectValues = null;
//
//			public static void main(String[] args) {
//				// *************************************************************************************************
//				String s = "{" + "\"id\":\"1\"," + "\"firstName\":\"Peter\"," + "\"lastName\":\"Pan\"," + "\"address\":{"
//						+ "\"id\":\"2\"," + "\"street\":\"4th Ave\"," + "\"city\":\"CPT\"" + "},\"children\":[{"
//						+ "\"id\":\"2\"," + "\"firstName\":\"Kelly\"," + "\"lastName\":\"Pan\"," + "\"address\":{"
//						+ "\"id\":\"2\"" + "\"street\":\"4th Ave\"" + "\"city\":\"CPT\"" + "},\"children\":\"[]\""
//						+ "}{\"id\":\"3\"," + "\"firstName\":\"Roger\"," + "\"lastName\":\"Pan\"," + "\"address\":{"
//						+ "\"id\":\"5\"" + "\"street\":\"Westwood Drive\"" + "\"city\":\"JHB\"" + "},\"children\":\"[]\""
//						+ "}}]}";
//				Address address5 = new Address("5", "Westwood Drive", "JHB");
//				Address address2 = new Address("2", "4th Ave", "CPT");
//				List<Person> children1 = new ArrayList<Person>();
//				List<Person> children2 = new ArrayList<Person>();
//				List<Person> children3 = new ArrayList<Person>();
//				Person child1 = new Person(2, "Kelly", "Pan", address2, children2);
//				Person child2 = new Person(3, "Roger", "Pan", address5, children3);
//				children1.add(child1);
//				children1.add(child2);
//				Person person1 = new Person(1, "Peter", "Pan", address2, children1);
//				// *************************************************************************************************
//
//				new CustomJsonParser().toJsonString(person1);
//
//				// *************************************************************************************************
////				new CustomJsonParser().toJsonObject(s, person1.getClass());
//				// *************************************************************************************************
//			}
//
//			public String toJsonStringNew(Object classInstanceToStringify) {
//
//				attributeNames = new ArrayList<Object>();
//				attributeValues = new ArrayList<Object>();
//
//				listInnerAttributeNamesL = new ArrayList<Object>();
//				listInnerAttributeValuesL = new ArrayList<Object>();
//
//				innerObjectNames = new ArrayList<Object>();
//				innerObjectValues = new ArrayList<Object>();
//
//				innerInnerObjectNames = new ArrayList<Object>();
//				innerInnerObjectValues = new ArrayList<Object>();
//
//				List<String> allGetMethods = findAllGetMethodsOnObject(classInstanceToStringify);
//
//				for (String methodName : allGetMethods) {
//
//					Class<?> returnTypeClass = findReturnTypeForMethodOnObject(methodName, classInstanceToStringify);
//					if (returnTypeClass.isInstance(Long.class)) {
//						attributeName = findAttributeNameForMethodOnObject(methodName, classInstanceToStringify);
//						attributeNames.add(attributeName);
//						attributeValue = findValueOfAttibuteForMethodOnObjectString(returnTypeClass, methodName,
//								classInstanceToStringify);
//						attributeValues.add(attributeValue);
//						break;
//					} else if (returnTypeClass.isInstance(String.class)) {
//						attributeName = findAttributeNameForMethodOnObject(methodName, classInstanceToStringify);
//						attributeNames.add(attributeName);
//						attributeValue = findValueOfAttibuteForMethodOnObjectString(returnTypeClass, methodName,
//								classInstanceToStringify);
//						attributeValues.add(attributeValue);
//						break;
//					} else if (returnTypeClass.isInstance(List.class)) {
//						attributeName = findAttributeNameForMethodOnObject(methodName, classInstanceToStringify);
//						listInnerAttributeNamesL.add(attributeName);
//						attributeValue = findValueOfAttibuteForMethodOnObjectList(returnTypeClass, methodName,
//								classInstanceToStringify);
//						listInnerAttributeValuesL.add(attributeValue);
//						break;
//					} else {
//						attributeName = findAttributeNameForMethodOnObject(methodName, classInstanceToStringify);
//						innerObjectNames.add(attributeName);
//						attributeValue = findValueOfAttibuteForMethodOnObjectObj(returnTypeClass, methodName,
//								classInstanceToStringify);
//						innerObjectValues.add(attributeValue);
//					}
//
//					StringBuilder builderJsonAttibute = new StringBuilder();
//					builderJsonAttibute.append("\"");
//					builderJsonAttibute.append(attributeName);
//
//					if (returnTypeClass.isInstance(String.class)) {
//
//						builderJsonAttibute.append("\" : \"");
//						builderJsonAttibute.append(attributeValue);
//						builderJsonAttibute.append("\"");
//
//					} else if (returnTypeClass.isInstance(ArrayList.class)) {
//
//						builderJsonAttibute.append("\" : [");
//						builderJsonAttibute.append(attributeValue);
//						builderJsonAttibute.append("]");
//					}
//				}
//				return null;
//			}
//
//			private List<String> findAllGetMethodsOnObject(Object classInstanceToStringify) {
//				List<String> retList = new ArrayList<String>();
//				for (Method method : classInstanceToStringify.getClass().getDeclaredMethods()) {
//					if (method.getName().startsWith("get")) {
//						retList.add((String) method.getName());
//					}
//				}
//				return retList;
//			}
//
//			private String findAttributeNameForMethodOnObject(String methodName, Object classInstanceToStringify) {
//				String methodNam = null;
//				try {
//					Method method = classInstanceToStringify.getClass().getMethod(methodName);
//					Class<?> clazz = method.getDeclaringClass();
//					BeanInfo info = Introspector.getBeanInfo(clazz);
//					PropertyDescriptor[] props = info.getPropertyDescriptors();
//					for (PropertyDescriptor pd : props) {
//						if (method.equals(pd.getWriteMethod()) || method.equals(pd.getReadMethod())) {
//							methodNam = pd.getDisplayName();
//						}
//					}
//				} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IntrospectionException e) {
//					e.printStackTrace();
//				}
//				return methodNam;
//			}
//
//			private Class<?> findReturnTypeForMethodOnObject(String methodName, Object classInstanceToStringify) {
//				Class<?> returnType = null;
//				try {
//					Method method = classInstanceToStringify.getClass().getMethod(methodName);
//					returnType = method.getReturnType();
//				} catch (NoSuchMethodException | SecurityException e) {
//					e.printStackTrace();
//				}
//				return returnType;
//			}
//
//			private String findValueOfAttibuteForMethodOnObjectString(Class<?> returnTypeClass, String methodName,
//					Object classInstanceToStringify) {
//				Object valueO = null;
//				try {
//					Method method = classInstanceToStringify.getClass().getDeclaredMethod(methodName);
//					Object obj = (Object) method.invoke(classInstanceToStringify);
//					String fieldName = findAttributeNameForMethodOnObject(methodName, classInstanceToStringify);
//					Field field = classInstanceToStringify.getClass().getDeclaredField(fieldName);
//					field.setAccessible(true);
//					valueO = field.get(classInstanceToStringify);
//				} catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException
//						| NoSuchFieldException | InvocationTargetException e) {
//					e.printStackTrace();
//				}
//				return valueO.toString();
//			}
//
//			private Object findValueOfAttibuteForMethodOnObjectList(Class<?> returnTypeClass, String methodName,
//					Object classInstanceToStringify) {
//				try {
//					Method method = classInstanceToStringify.getClass().getDeclaredMethod(methodName);
//					List<?> obj = (List<?>) method.invoke(classInstanceToStringify);
//					for (Object ob : obj) {
//						for (String innerMethod : findAllGetMethodsOnObject(ob)) {
//							String innerAttributeName = findAttributeNameForMethodOnObject(innerMethod, ob);
//							Class<?> innerMethodReturnType = findReturnTypeForMethodOnObject(innerMethod, ob);
//							String innerAttributeValue = findValueOfAttibuteForMethodOnObjectString(innerMethodReturnType,
//									innerMethod, ob);
//						}
//					}
//				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
//						| InvocationTargetException e) {
//					e.printStackTrace();
//				}
//				return null;
//			}
//
//			private Object findValueOfAttibuteForMethodOnObjectObj(Class<?> returnTypeClass, String methodName,
//					Object classInstanceToStringify) {
//				Object valueI = null;
//				try {
//					Method method = classInstanceToStringify.getClass().getDeclaredMethod(methodName);
//					Object objo = (Object) method.invoke(classInstanceToStringify);
//					for (String innerMethod : findAllGetMethodsOnObject(objo)) {
//						String innerAttributeName = findAttributeNameForMethodOnObject(innerMethod, objo);
//						Class<?> innerMethodReturnType = findReturnTypeForMethodOnObject(innerMethod, objo);
//						valueI = findValueOfAttibuteForMethodOnObjectString(innerMethodReturnType, innerMethod, objo);
//					}
//				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
//						| InvocationTargetException e) {
//					e.printStackTrace();
//				}
//
//				return valueI.toString();
//			}
//
//			public Object toJsonObject(String jsonString, Class<?> jsonClass) {
//				String ezSub = StringUtils.substringBetween((String) jsonString.substring(0), "{", "}");
//				String strippedStr = jsonString.replaceAll("\"", "");
//				String comSub0 = StringUtils.substringBetween((String) jsonString.substring(0), "{", ",");
//				String[] splitVals0 = comSub0.replaceAll("\"", "").split(":");
//				String val0 = null;
//				for (int i = 0; i < 2; i++) {
//					val0 = splitVals0[1];
//				}
//				String comSub1 = StringUtils.substringBetween((String) jsonString.substring(0), ",", ",");
//				String[] splitVals1 = comSub1.replaceAll("\"", "").split(":");
//				String val1 = null;
//				for (int i = 0; i < 2; i++) {
//					val1 = splitVals1[1];
//				}
//				String comSub2 = StringUtils.substringBetween((String) jsonString.substring(10), ",", ",");
//				String[] splitVals2 = comSub2.replaceAll("\"", "").split(":");
//				String val2 = null;
//				for (int i = 0; i < 2; i++) {
//					val2 = (splitVals2[1]);
//				}
//				String adSub = StringUtils.substringBetween((String) jsonString.substring(1), "{", "}");
//				String[] splitValsAd0 = adSub.replaceAll("\"", "").split(":|,");
//				String valAdId = null;
//				String valAdStreet = null;
//				String valAdCity = null;
//				for (int i = 1; i < splitValsAd0.length; i++) {
//					valAdId = splitValsAd0[1];
//					valAdStreet = splitValsAd0[3];
//					valAdCity = splitValsAd0[5];
//				}
//				Method method = null;
//				Address addressObj = null;
//				Person objInst = null;
//				try {
//					objInst = (Person) jsonClass.newInstance();
//					objInst.setId((Long) Long.parseLong(val0));
//					objInst.setFirstName(val1);
//					objInst.setLastName(val2);
//					method = jsonClass.getDeclaredMethod("getAddress");
//					addressObj = (Address) method.invoke(objInst);
////					addressObj.setId(valAdId);
//					addressObj.setStreet(valAdStreet);
//					addressObj.setCity(valAdCity);
//					objInst.setAddress(addressObj);
//				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
//						| SecurityException | InstantiationException e1) {
//					e1.printStackTrace();
//				}
//
//				return objInst;
//			}
//
//			public String toJsonString(Object classInstanceToStringify) {
//				StringBuilder sb = new StringBuilder("{\n");
//				for (Field field : classInstanceToStringify.getClass().getDeclaredFields()) {
//					field.setAccessible(true);
//					String fieldType = field.getType().toString();
//					switch (fieldType) {
//					case "long":
//						try {
//							sb.append("\"" + field.getName() + "\":")
//									.append("\"" + field.get(classInstanceToStringify) + "\"" + ",\n");
//						} catch (IllegalArgumentException | IllegalAccessException e) {
//							e.printStackTrace();
//						}
//						break;
//
//					case "class java.lang.String":
//						try {
//							sb.append("\"" + field.getName() + "\":")
//									.append("\"" + field.get(classInstanceToStringify) + "\"" + ",\n");
//						} catch (IllegalArgumentException | IllegalAccessException e) {
//							e.printStackTrace();
//						}
//						break;
//
//					default:
//						try {
//							List<?> methods = findAllGetMethodsOnObject(classInstanceToStringify);
//							sb.append("\"" + field.getName() + "\":{\n");
//							for (Field fieldA : field.getType().getDeclaredFields()) {
//								fieldA.setAccessible(true);
//								if (methods.contains(field.getName())) {
//									Method method = classInstanceToStringify.getClass().getDeclaredMethod(field.getName());
//									Object obj2 = (Object) method.invoke(classInstanceToStringify);
//									sb.append("\"" + fieldA.getName() + "\":").append("\"" + fieldA.get(obj2) + "\"" + "\n");
//								}
//							}
//							sb.append("},");
//						} catch (IllegalArgumentException | SecurityException | IllegalAccessException
//								| InvocationTargetException | NoSuchMethodException e) {
//							e.printStackTrace();
//						}
//
//						break;
//
//					case "interface java.util.List":
//						try {
//							sb.append("\"" + field.getName() + "\":[{\n");
//							List<?> methods = findAllGetMethodsOnObject(classInstanceToStringify);
//							Field[] fieldList = field.getType().getDeclaredFields();
//							for (Field f : fieldList) {
//							}
//							Method method = classInstanceToStringify.getClass().getDeclaredMethod("getChildren");
//							List<?> children = (List<?>) method.invoke(classInstanceToStringify);
//							for (Object childs : children) {
//								for (Field fieldC : childs.getClass().getDeclaredFields()) {
//									fieldC.setAccessible(true);
//									String fieldType1 = fieldC.getType().toString();
//									switch (fieldType1) {
//									case "long":
//										try {
//											sb.append("\"" + fieldC.getName() + "\":")
//													.append("\"" + fieldC.get(childs) + "\"" + ",\n");
//										} catch (IllegalArgumentException | IllegalAccessException e) {
//											e.printStackTrace();
//										}
//										break;
//
//									case "class java.lang.String":
//										try {
//											sb.append("\"" + fieldC.getName() + "\":")
//													.append("\"" + fieldC.get(childs) + "\"" + ",\n");
//										} catch (IllegalArgumentException | IllegalAccessException e) {
//											e.printStackTrace();
//										}
//										break;
//
//									default:
//										try {
//											List<?> methods1 = findAllGetMethodsOnObject(classInstanceToStringify);
//											sb.append("\"" + field.getName() + "\":{\n");
//											for (Field fieldA : field.getType().getDeclaredFields()) {
//												fieldA.setAccessible(true);
//												if (methods.contains(field.getName())) {
//													Method method2 = classInstanceToStringify.getClass()
//															.getDeclaredMethod(field.getName());
//													Object obj2 = (Object) method.invoke(classInstanceToStringify);
//													sb.append("\"" + fieldA.getName() + "\":")
//															.append("\"" + fieldA.get(obj2) + "\"" + "\n");
//												}
//											}
//											sb.append("},");
//										} catch (IllegalArgumentException | SecurityException | IllegalAccessException
//												| InvocationTargetException | NoSuchMethodException e) {
//											e.printStackTrace();
//										}
//
//										break;
//
//									case "interface java.util.List":
//										sb.append("\"" + "children" + "\":").append("\"" + "[]" + "\"" + "\n},{");
//									}
//									// reduced recursive complexity to save time
//								}
//							}
//							sb.append("}]");
//						} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException
//								| NoSuchMethodException | SecurityException e) {
//							e.printStackTrace();
//						}
//						break;
//					}
//				}
//				sb.append("}");
//				System.out.print(sb.toString());
//				return sb.toString();
//			}
//		}
	}

	@SuppressWarnings("unused")
	private void com1() {
		// *************************************************************************************************
//	String w1 = "{\"id\":1,\"firstName\":\"Peter\",\"lastName\":\"Pan\",\"address\":{\"id\":2,\"street\":\"4th Ave\",\"city\":\"CPT\"},\"children\":[{\"id\":2,\"firstName\":\"Kelly\",\"lastName\":\"Pan\",\"address\":{\"id\":2,\"street\":\"4th Ave\",\"city\":\"CPT\"},\"children\":\"[]\"},{\"id\":3,\"firstName\":\"Roger\",\"lastName\":\"Pan\",\"address\":{\"id\":5,\"street\":\"Westwood Drive\",\"city\":\"JHB\"},\"children\":\"[]\"}]}";
//	String w2 = "\"id\":1,\"firstName\":\"Peter\",\"lastName\":\"Pan\",\"address\":{\"id\":2,\"street\":\"4th Ave\",\"city\":\"CPT\"},\"children\":[{\"id\":2,\"firstName\":\"Kelly\",\"lastName\":\"Pan\",\"address\":{\"id\":2,\"street\":\"4th Ave\",\"city\":\"CPT\"},\"children\":\"[]\"},{\"id\":3,\"firstName\":\"Roger\",\"lastName\":\"Pan\",\"address\":{\"id\":5,\"street\":\"Westwood Drive\",\"city\":\"JHB\"},\"children\":\"[]\"}]";
//	String w3 = "\"id\":1,\"firstName\":\"Peter\",\"lastName\":\"Pan\",\"address\":,\"children\":[{\"id\":2,\"firstName\":\"Kelly\",\"lastName\":\"Pan\",\"address\":{\"id\":2,\"street\":\"4th Ave\",\"city\":\"CPT\"},\"children\":\"[]\"},{\"id\":3,\"firstName\":\"Roger\",\"lastName\":\"Pan\",\"address\":{\"id\":5,\"street\":\"Westwood Drive\",\"city\":\"JHB\"},\"children\":\"[]\"}]";
//	String w4 = "\"id\":1,\"firstName\":\"Peter\",\"lastName\":\"Pan\",\"address\":,\"children\":[,{\"id\":3,\"firstName\":\"Roger\",\"lastName\":\"Pan\",\"address\":{\"id\":5,\"street\":\"Westwood Drive\",\"city\":\"JHB\"},\"children\":\"[]\"}]";
//	String w5 = "\"id\":1,\"firstName\":\"Peter\",\"lastName\":\"Pan\",\"address\":,\"children\":[,]";
//
//	String ex1 = "\"id\":2,\"street\":\"4th Ave\",\"city\":\"CPT\"";
//	String ex21 = "\"id\":2,\"firstName\":\"Kelly\",\"lastName\":\"Pan\",\"address\":{\"id\":2,\"street\":\"4th Ave\",\"city\":\"CPT\"},\"children\":\"[]\"";
//	String ex3 = "\"id\":2,\"street\":\"4th Ave\",\"city\":\"CPT\"";
//	String ex41 = "\"id\":3,\"firstName\":\"Roger\",\"lastName\":\"Pan\",\"address\":{\"id\":5,\"street\":\"Westwood Drive\",\"city\":\"JHB\"},\"children\":\"[]\"";
//	String ex5 = "\"id\":5,\"street\":\"Westwood Drive\",\"city\":\"JHB\"";
		// *************************************************************************************************
//	Address address5 = new Address(5, "Westwood Drive", "JHB");
//	Address address2 = new Address(2, "4th Ave", "CPT");
//	List<Person> children1 = new ArrayList<Person>();
//	List<Person> children2 = new ArrayList<Person>();
//	List<Person> children3 = new ArrayList<Person>();
//	Person child1 = new Person(2, "Kelly", "Pan", address2, children2);
//	Person child2 = new Person(3, "Roger", "Pan", address5, children3);
//	children1.add(child1);
//	children1.add(child2);
//	Person person1 = new Person(1, "Peter", "Pan", address2, children1);
		// *************************************************************************************************
//	{"id":1,"firstName":"Peter","lastName":"Pan","address":{"id":2,"street":"4th Ave","city":"CPT"},
//	"children":[{"id":2,"firstName":"Kelly","lastName":"Pan","address":{"id":2,"street":"4th Ave","city":"CPT"},"children":"[]"},
//	            {"id":3,"firstName":"Roger","lastName":"Pan","address":{"id":5,"street":"Westwood Drive","city":"JHB"},"children":"[]"}]}

//	{person:{address}:[children{person:{address}[children]}{person{address}[children]}]}
//	1	   2      2 L1       3      4      4 L2     L2 3 5     6      6 L3     L3 5 L1 1
//	{ :{}, :[{ :{}, :[], },{ :{}, :[], }]}
//	  0	1  2 1 2 3  4 3 4  32  32   1 2  3 2 1 0
		// *************************************************************************************************
//		1{, 2{, 1}, 2[, 3{, 4{, 3}, 4[, 3], 2}, 3{, 4{, 3}, 4[, 3], 2}, 1], 0}
		// *************************************************************************************************
//		1{, 2{, 1}, L, 3{, 4{, 3}, L, L, 2}, 3{, 4{, 3}, L, L, 2}, L, 0}
		// *************************************************************************************************
//		1{, 2{, 1}, [1, 3{, 4{, 3}, [2, ]1, 2}, 3{, 4{, 3}, [2, ]1, 2}, ]0, 0}
		// *************************************************************************************************
//	[0, 55, 108, 163, 221, 276]
//	[94, 202, 219, 322, 339, 341]
//	[107, 216, 336]
//	[217, 337, 340]
//	[0, 55, 94, 107, 108, 163, 202, 216, 217, 219, 221, 276, 322, 336, 337, 339, 340, 341]
//	[1, 2, 1, 2, 3, 4, 3, 4, 3, 2, 3, 4, 3, 4, 3, 2, 1, 0]
		// *************************************************************************************************

		// *************************************************************************************************
//	List<String> innerObject = stripString(json);
//	String innerObjP = innerObject.get(1);
//	HashMap<String, String> mapifyOO1 = mapify(innerObjP);
//
//	List<String> listInnerObject1 = stripList(innerObject.get(0));
//	String innerInnerObjK = listInnerObject1.get(1);
//	HashMap<String, String> mapifyOO2 = mapify(innerInnerObjK);
//	String innerObjK = listInnerObject1.get(2);
//	HashMap<String, String> mapifyO2 = mapify(innerObjK);
//
//	List<String> listInnerObject2 = stripList(listInnerObject1.get(0));
//	String outerObj = listInnerObject2.get(0);
//	HashMap<String, String> mapifyO1 = mapify(outerObj);
//
//	String innerInnerObjR = listInnerObject2.get(1);
//	HashMap<String, String> mapifyOO3 = mapify(innerInnerObjR);
//	String innerObjR = listInnerObject2.get(2);
//	HashMap<String, String> mapifyO3 = mapify(innerObjR);
	}
}