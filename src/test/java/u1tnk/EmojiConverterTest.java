package u1tnk;

import static junit.framework.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import u1tnk.EmojiConverter.Type;

public class EmojiConverterTest {

	private static EmojiConverter converter;
	
	@BeforeClass
	public static void setup(){
		converter = new EmojiConverter.Builder()
			.from(Type.UNICODE)
			.to(Type.SOFTBANK)
			.build();
	}
	@Test
	public void インスタンス作成(){
		assertNotNull(converter);
	}
	
	@Test
	public void 絵文字無し(){
		String input = "あさああああ";
		assertEquals(input, converter.convert(input));
		
	}
	
	@Test
	public void cp10000以下絵文字(){
		String input = "ああ" + new String(Character.toChars(Integer.parseInt("2600", 16))) + "ああ";
		String expected = "ああ" + new String(Character.toChars(Integer.parseInt("E04A", 16))) + "ああ";
		assertEquals(expected, converter.convert(input));
	}
	
	@Test
	public void cp10000以上絵文字(){
		String input = "ああ" + new String(Character.toChars(Integer.parseInt("1F302", 16))) + "ああ";
		String expected = "ああ" + new String(Character.toChars(Integer.parseInt("E43C", 16))) + "ああ";
		assertEquals(expected, converter.convert(input));
	}
	
	@Test
	public void ソフトバンクに無し(){
		String input = "ああ" + new String(Character.toChars(Integer.parseInt("1F301", 16))) + "ああ";
		String expected = "ああああ";
		assertEquals(expected, converter.convert(input));
	}
	
	@Test
	public void inputが合字(){
		String input = "ああ" 
			+ new String(Character.toChars(Integer.parseInt("1F1EF", 16))) 
			+ new String(Character.toChars(Integer.parseInt("1F1F5", 16))) 
			+ "ああ";
		System.out.println(input);
		String expected = "ああ" + new String(Character.toChars(Integer.parseInt("E50B", 16))) + "ああ";
		assertEquals(expected, converter.convert(input));
	}
	
}
