package za.co.sfy.utilities.json;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.google.gson.Gson;
import za.co.sfy.domain.Address;
import za.co.sfy.domain.Person;

@RunWith(MockitoJUnitRunner.class)
class ToStringParserTest {

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
	// *********************************************************************

	@BeforeEach
	void setUp() throws Exception {
		// *********************************************************************
		address5 = new Address(5, "Westwoodle Drive", "JHB");
		address2 = new Address(2, "4th Ave", "CPT");
		children1 = new ArrayList<Person>();
		children2 = new ArrayList<Person>();
		children3 = new ArrayList<Person>();
		child1 = new Person(2, "Kelly", "Pan", address2, children2);
		child2 = new Person(3, "David", "Pan", address5, children3);
		children1.add(child1);
		children1.add(child2);
		person1 = new Person(14, "Peter", "Pan", address2, children1);
		// *********************************************************************
	}

	@Test
	void test1() {
		assertEquals(new Gson().toJson(person1), new ToStringParser().parseObjectToString(person1));
		// *********************************************************************
	}

	@Test
	void test2() {
		assertEquals(new Gson().toJson(child2), new ToStringParser().parseObjectToString(child2));
		// *********************************************************************
	}

	@Test
	void test3() {
		assertEquals(new Gson().toJson(address5), new ToStringParser().parseObjectToString(address5));
		// *********************************************************************
	}
}
