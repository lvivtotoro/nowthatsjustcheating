package ntjc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

public class NTJC {

	public static String readProgram(String prog) {
		InputStream in;
		try {
			in = new URL("https://raw.githubusercontent.com/lvivtotoro/nowthatsjustcheating/master/programs/" + prog
					+ ".lua").openStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) != -1) {
				baos.write(buf, 0, len);
			}
			String body = new String(baos.toByteArray(), "UTF-8");
			return body;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		if (args.length < 1)
			throw new RuntimeException("Minimum arguments: 1");
		Globals globals = JsePlatform.standardGlobals();
		globals.set("args", CoerceJavaToLua.coerce(args));
		globals.load(readProgram(args[0])).call();
	}

}
