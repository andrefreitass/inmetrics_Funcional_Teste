package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageLogin extends PageGenerics{
	
	
	@FindBy(xpath = "//button[contains(text(),'Entre')]")
    public WebElement BOTAO_ENTRE;

}
