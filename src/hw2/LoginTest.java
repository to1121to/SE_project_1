package hw2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginTest {
	
	main_menu test = null;
	private final PrintStream originalOut = System.out;
	private final InputStream originalIn = System.in;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final 

	@BeforeEach
	void setUp() throws Exception {
		test = new main_menu();
		System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	void tearDown() throws Exception {
		System.setOut(originalOut);
		System.setIn(originalIn);
		test = null;
	}

	@Test
	void test() throws Exception {
		
		String id = "10000001";
		InputStream inContent = new ByteArrayInputStream(id.getBytes());
		System.setIn(inContent);
		test.init_set();
		test.login();
		
		id = "C\r\n19980228" + System.getProperty("line.separator");
		inContent = new ByteArrayInputStream(id.getBytes());
		System.setIn(inContent);
		test.function();
		
		String expected = "您在19980228的總消費金額為22001";
		String output = outContent.toString().split("\n")[10];
		assertEquals(expected, output);
		//fail("Not yet implemented");
	}

}
