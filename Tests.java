import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Locale;

import Components.Parser;
import org.junit.Before;
import org.junit.Test;



public class Tests {

	private static final String FORMATO_INPUT = "$in.icl";
	private static final String FORMATO_OUTPUT = "$out.icl";

	@Test public void test1() { test(1); }
	@Test public void test2() { test(2); }
	@Test public void test3() { test(3); }
	@Test public void test4() { test(4); }
	@Test public void test5() { test(5); }
	@Test public void test6() { test(6); }
	@Test public void test7() { test(7); }
	@Test public void test8() { test(8); }
	@Test public void test9() { test(9); }
	@Test public void test10() { test(10); }
	@Test public void test11() { test(11); }
	@Test public void test12() { test(12); }
	@Test public void test13() { test(13); }
	@Test public void test14() { test(14); }
	@Test public void test15() { test(15); }
	@Test public void test16() { test(16); }
	@Test public void test17() { test(17); }
	@Test public void test18() { test(18); }
	@Test public void test19() { test(19); }
	@Test public void test20() { test(20); }
	@Test public void test21() { test(21); }
	@Test public void test22() { test(22); }
	@Test public void test23() { test(23); }
	@Test public void test24() { test(24); }
	@Test public void test25() { test(25); }
	@Test public void test26() { test(26); }
	@Test public void test27() { test(27); }
	@Test public void test28() { test(28); }
	@Test public void test29() { test(29); }
	@Test public void test30() { test(30); }
	@Test public void test31() { test(31); }
	@Test public void test32() { test(32); }
	@Test public void test33() { test(33); }
	@Test public void test34() { test(34); }

	private static final File BASE = new File("src/tests");

	private PrintStream consoleStream;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setup() {
		consoleStream = System.out;
		System.setOut(new PrintStream(outContent));
	}

	public void test(int num) {
		String in = FORMATO_INPUT.replaceAll("\\$", Integer.toString(num));
		String out = FORMATO_OUTPUT.replaceAll("\\$", Integer.toString(num)); 
		test(in, out);
	}

	public void test(String input, String output) {
		test(new File(BASE, input), new File(BASE, output));
	}

	public void test(File input, File output) {
		consoleStream.println("Testing!");
		consoleStream.println("Input: " + input.getAbsolutePath());
		consoleStream.println("Output: " + output.getAbsolutePath());

		String fullInput = "", fullOutput = "";
		try {
			fullInput = new String(Files.readAllBytes(input.toPath()));
			fullOutput = new String(Files.readAllBytes(output.toPath()));
			consoleStream.println("INPUT ============");
			consoleStream.println(new String(fullInput));
			consoleStream.println("OUTPUT ESPERADO =============");
			consoleStream.println(new String(fullOutput));
			consoleStream.println("OUTPUT =============");
		} catch(Exception e) {
			e.printStackTrace();
			fail("Erro a ler o ficheiro");
		}

		try {
			Locale.setDefault(Locale.US);
			Parser.main(new String[] {"-f",input.getPath()});
			//Parser.ReInit(System.in);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Erro no programa");
		} finally {
			byte[] outPrintBytes = outContent.toByteArray();
			consoleStream.println(new String(outPrintBytes));

			assertEquals(removeCarriages(fullOutput), removeCarriages(new String(outContent.toByteArray())));
		}
	}

	private static String removeCarriages(String s) {
		return s.replaceAll("\r\n", "\n");
	}

}
