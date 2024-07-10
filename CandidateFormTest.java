
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import java.time.Duration;
	import java.util.List;

	public class CandidateFormTest {
	    WebDriver driver;
	    WebDriverWait wait;

	    public CandidateFormTest() {
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--start-maximized");
	        driver = new ChromeDriver(options);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    public void openFormPage() {
	        driver.get("https://recruiterflow.com/prospect/add"); // Replace with the actual URL
	    }

	    public void fillForm(String firstName, String lastName, String email, String phoneNumber, String location) {
	        driver.findElement(By.name("first_name")).sendKeys(firstName);
	        driver.findElement(By.name("last_name")).sendKeys(lastName);
	        driver.findElement(By.name("email")).sendKeys(email);
	        driver.findElement(By.name("phone_number")).sendKeys(phoneNumber);
	        
	        // Search and select location
	        WebElement locationInput = driver.findElement(By.name("location"));
	        locationInput.sendKeys(location);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".location-dropdown-item"))).click();
	    }

	    public void addEducation(String schoolName, String degree, String specialization, String fromDate, String toDate) {
	        driver.findElement(By.linkText("Add education")).click();
	        List<WebElement> educationFields = driver.findElements(By.cssSelector(".education-field"));
	        educationFields.get(0).findElement(By.name("school_name")).sendKeys(schoolName);
	        educationFields.get(0).findElement(By.name("degree")).sendKeys(degree);
	        educationFields.get(0).findElement(By.name("specialization")).sendKeys(specialization);
	        educationFields.get(0).findElement(By.name("from_date")).sendKeys(fromDate);
	        educationFields.get(0).findElement(By.name("to_date")).sendKeys(toDate);
	    }

	    public void uploadResume(String filePath) {
	        driver.findElement(By.id("resume-upload")).sendKeys(filePath);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resume-upload-success")));
	    }

	    public void submitForm() {
	        driver.findElement(By.id("create-candidate")).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("candidate-created-success")));
	    }

	    public void searchAndVerifyCandidate(String candidateName) {
	        driver.get("URL_OF_YOUR_APPLICATION/prospect"); // Replace with the actual URL
	        driver.findElement(By.id("search-input")).sendKeys(candidateName);
	        driver.findElement(By.id("search-button")).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".candidate-result")));
	        
	        WebElement candidate = driver.findElement(By.cssSelector(".candidate-result"));
	        String displayedName = candidate.findElement(By.cssSelector(".candidate-name")).getText();
	        assert displayedName.equals(candidateName);
	    }

	    public void close() {
	        driver.quit();
	    }

	    public static void main(String[] args) {
	        CandidateFormTest test = new CandidateFormTest();
	        
	        // Positive test case
	        test.openFormPage();
	        test.fillForm("John", "Doe", "john.doe@example.com", "+1 408 600 1338", "New York, NY");
	        test.addEducation("Harvard University", "BSc", "Computer Science", "2010-09-01", "2014-05-30");
	        test.uploadResume("/path/to/resume.pdf"); // Replace with the actual file path
	        test.submitForm();
	        test.searchAndVerifyCandidate("John Doe");
	        
	        test.close();
	    }
	}

