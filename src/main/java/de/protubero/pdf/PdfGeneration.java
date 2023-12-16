package de.protubero.pdf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.PdfOptions;
import com.microsoft.playwright.Page.SetContentOptions;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Playwright.CreateOptions;

public class PdfGeneration {

	public static void main(String[] args) throws IOException {

		CreateOptions createOptions = new CreateOptions();
		Map<String, String> env = new HashMap<>();
		env.put("PLAYWRIGHT_BROWSERS_PATH", "C:/work/playwright");
		env.put("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");
		
		
		createOptions.setEnv(env);
		try (Playwright playwright = Playwright.create(createOptions)) {
			Browser browser = playwright.chromium().launch();
			Page page = browser.newPage();
			SetContentOptions setContentOptions = new SetContentOptions();
			page.setContent(generateContent(), setContentOptions);
			PdfOptions options = new PdfOptions();
			byte[] bytes = page.pdf(options);
			Files.write(Paths.get("c:/work/export.pdf"), bytes);
		}
	}

	private static String generateContent() {
		// TODO Auto-generated method stub
		return """
				<html>
					<body>
						Text
					</body>
				</html>
				""";
	}
}
