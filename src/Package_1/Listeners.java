package Package_1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener, ISuiteListener, IInvokedMethodListener {
    public int X = 0;
    public int l = 0;
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void onStart(ISuite suite) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void onFinish(ISuite suite) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void onTestFailure(ITestResult result) {
        ++l;
        System.out.println(l);      
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void onFinish(ITestContext context) {
    	Runtime runtime = Runtime.getRuntime();
    	final String HTML = "C:\\TestFrenzy\\testng-xslt\\Suite1.html";
        final String CSS = "C:\\TestFrenzy\\testng-xslt\\style.css";
        // Creating a simple pie chart with 
         DefaultPieDataset pieDataset = new DefaultPieDataset();
         pieDataset.setValue("Failed", new Integer(l));
         pieDataset.setValue("Passed", new Integer(X));
         pieDataset.setValue("Skipped", new Integer(0));
         JFreeChart piechart = ChartFactory.createPieChart("", pieDataset, true, true, false);
         PiePlot plot = (PiePlot) piechart.getPlot();
         plot.setSectionPaint("Passed", new Color(0,164,149));
         try {
            ChartUtilities.saveChartAsJPEG(new File("C:\\TestFrenzy\\reporte\\simplePiechart.jpg"), piechart, 525, 390);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
            // step 1
              Document document = new Document();
              // step 2
              PdfWriter writer = null;
            try {
                writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\TestFrenzy\\reporte\\reporte.pdf"));
            } catch (FileNotFoundException | DocumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
              // step 3
              document.open();
              // Add Image
              Image img = null;
              Image logo = null;
              Image logoCognits = null;
            try {
                img = Image.getInstance("C:\\TestFrenzy\\reporte\\simplePiechart.jpg");
                logo = Image.getInstance("C:\\LogoFrenzy.png");
                logoCognits = Image.getInstance("C:\\cognits-gris.png");
            } catch (BadElementException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
              try {
            	Paragraph paragraph1 = new Paragraph("   ");
                logo.setAlignment(Element.ALIGN_RIGHT);
                logoCognits.setAlignment(Element.ALIGN_LEFT);
              	img.setAlignment(Element.ALIGN_CENTER);
              	logo.scaleAbsoluteWidth(110f);
              	logo.scaleAbsoluteHeight(25f);
              	logoCognits.scaleAbsoluteWidth(110f);
              	logoCognits.scaleAbsoluteHeight(35f);
              	Paragraph p = new Paragraph();
              	p.add(new Chunk(logoCognits, 0, -7));
              	p.add(new Chunk(logo, 300, 0)); 
              	document.add(p);
//              	document.add(logoCognits);
//              	document.add(logo);
              	document.add(paragraph1);
              	document.add(paragraph1);
              	Font bold = new Font(FontFamily.HELVETICA, 30, Font.BOLD,BaseColor.GRAY);
              	Chunk blueText = new Chunk("Test Frenzy Report", bold);
              	Phrase phrase = new Phrase();
              	phrase.add(blueText);

              	Paragraph para = new Paragraph();
              	para.add(phrase);
              	para.setAlignment(Element.ALIGN_CENTER);
              	document.add(para);
              	document.add(paragraph1);
            	document.add(img);
                document.add(paragraph1);
            	PdfPTable table = new PdfPTable(3);
                table.addCell("Passed: " + X);
                table.addCell("Failed: " + l);
                table.addCell("Skipped: 0");
                document.add(table);
                document.add(paragraph1);
//            	document.add(new Paragraph("Passed: " + X));
//            	document.add(new Paragraph("Failed: " + l));
//            	document.add(new Paragraph("Skipped: 0"));
                            } catch (DocumentException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
              // step 4
              
              // CSS
              CSSResolver cssResolver = new StyleAttrCSSResolver();
              CssFile cssFile = null;
			try {
				cssFile = XMLWorkerHelper.getCSS(new FileInputStream(CSS));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              cssResolver.addCss(cssFile);
       
              // HTML
              HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
              htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
       
              // Pipelines
              PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
              HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
              CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
       
              // XML Worker
              XMLWorker worker = new XMLWorker(css, true);
              XMLParser p = new XMLParser(worker);
              try {
				p.parse(new FileInputStream(HTML));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //Again
              // CSS
              CSSResolver cssResolver1 = new StyleAttrCSSResolver();
              CssFile cssFile1 = null;
			try {
				cssFile1 = XMLWorkerHelper.getCSS(new FileInputStream(CSS));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              cssResolver1.addCss(cssFile1);
       
              // HTML
              HtmlPipelineContext htmlContext1 = new HtmlPipelineContext(null);
              htmlContext1.setTagFactory(Tags.getHtmlTagProcessorFactory());
       
              // Pipelines
              PdfWriterPipeline pdf1 = new PdfWriterPipeline(document, writer);
              HtmlPipeline html1 = new HtmlPipeline(htmlContext1, pdf1);
              CssResolverPipeline css1 = new CssResolverPipeline(cssResolver1, html1);
       
              // XML Worker
              XMLWorker worker1 = new XMLWorker(css1, true);
              XMLParser p1 = new XMLParser(worker1);
              try {
				p1.parse(new FileInputStream(HTML));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              
              
              //step 5
               document.close();
              System.out.println( "PDF Created!" );
          
        
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        ++X;    
        
    }
}