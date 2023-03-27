// Get the samples from https://www.adobe.com/go/pdftoolsapi_java_samples
// Run the sample:
// mvn -f pom.xml exec:java -Dexec.mainClass=com.adobe.pdfservices.operation.samples.exportpdf.ExportPDFToDOCX

public class ExportPDFToDOCX {

   // Initialize the logger.
   private static final Logger LOGGER = LoggerFactory.getLogger(ExportPDFToDOCX.class);

   public static void main(String[] args) {

     try {
       // Initial setup, create credentials instance.
       Credentials credentials = Credentials.serviceAccountCredentialsBuilder()
           .fromFile("pdfservices-api-credentials.json")
           .build();
       //Create an ExecutionContext using credentials and create a new operation instance.
       ExecutionContext executionContext = ExecutionContext.create(credentials);
       ExportPDFOperation exportPdfOperation = ExportPDFOperation.createNew(ExportPDFTargetFormat.DOCX);

       // Set operation input from a local PDF file
       FileRef sourceFileRef = FileRef.createFromLocalFile("src/main/resources/exportPDFInput.pdf");
       exportPdfOperation.setInput(sourceFileRef);

       // Execute the operation.
       FileRef result = exportPdfOperation.execute(executionContext);

       // Save the result to the specified location.
       result.saveAs("output/exportPdfOutput.docx");

     } catch (ServiceApiException | IOException | SdkException | ServiceUsageException ex) {
       LOGGER.error("Exception encountered while executing operation", ex);
     }
   }
 }
