package za.co.sfy.utilities.json;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import com.google.gson.Gson;
import za.co.sfy.domain.Address;
import za.co.sfy.domain.Person;

class ToObjectParserTest {

	// *********************************************************************
	@Mock
	Address address5;
	@Mock
	Address address2;
	@Mock
	List<Person> children1;
	@Mock
	List<Person> children2;
	@Mock
	List<Person> children3;
	@Mock
	Person child1;
	@Mock
	Person child2;
	@Mock
	Person person1;
	@Mock
	String gson1;
	@Mock
	String gson2;
	@Mock
	String gson3;
	@Mock
	String json1;
	@Mock
	String json2;
	@Mock
	String json3;
	@Mock
	Person personTest;
	@Mock
	Person childTest;
	@Mock
	Address addressTest;
	@Mock
	String parseObjectToString;
	@Mock
	Person fromJson;
	@Mock
	Person fromJsonC;
	@Mock
	Address fromJsonA;
	// *********************************************************************

	@BeforeEach
	void setUp() throws Exception {
		// *********************************************************************
		address5 = new Address(9, "Westwoodle Drive", "JHB");
		address2 = new Address(98, "41th Ave", "CPT");
		children1 = new ArrayList<Person>();
		children2 = new ArrayList<Person>();
		children3 = new ArrayList<Person>();
		child1 = new Person(2, "Kelly", "Pantamime", address2, children2);
		child2 = new Person(6, "David", "Sampson", address5, children3);
		children1.add(child1);
		children1.add(child2);
		person1 = new Person(8, "Pieter", "Panman", address2, children1);
		// *********************************************************************
		gson1 = new Gson().toJson(person1);
		fromJson = new Gson().fromJson(gson1, person1.getClass());
		json1 = new ToStringParser().parseObjectToString(person1);
		personTest = (Person) new ToObjectParser().parseStringToObject(json1, person1.getClass());
		// *********************************************************************
		gson2 = new Gson().toJson(child2);
		fromJsonC = new Gson().fromJson(gson2, child2.getClass());
		json2 = new ToStringParser().parseObjectToString(child2);
		childTest = (Person) new ToObjectParser().parseStringToObject(json2, child2.getClass());
		// *********************************************************************
		gson3 = new Gson().toJson(address5);
		fromJsonA = new Gson().fromJson(gson3, address5.getClass());
		json3 = new ToStringParser().parseObjectToString(address5);
		addressTest = (Address) new ToObjectParser().parseStringToObject(json3, address5.getClass());
		// *********************************************************************
	}

	@Test
	void test1() {
		assertEquals(fromJson.getId(), personTest.getId());
		// *********************************************************************
	}

	@Test
	void test2() {
		assertEquals(fromJsonC.getFirstName(), childTest.getFirstName());
		// *********************************************************************
	}

	@Test
	void test3() {
		assertEquals(fromJsonC.getId(), childTest.getId());
		// *********************************************************************
	}

	@Test
	void test4() {
		assertEquals(fromJsonA.getStreet(), addressTest.getStreet());
		// *********************************************************************
	}

	@Test
	void test5() {
		assertEquals(new Gson().toJson(person1), new ToStringParser().parseObjectToString(person1));
		// *********************************************************************
	}

	@Test
	void test6() {
		assertEquals(new Gson().toJson(child2), new ToStringParser().parseObjectToString(child2));
		// *********************************************************************
	}

	@Test
	void test7() {
		assertEquals(new Gson().toJson(address5), new ToStringParser().parseObjectToString(address5));
		// *********************************************************************
	}
}
