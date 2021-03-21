package br.edu.unoesc.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import br.edu.unoesc.models.Disciplina;
import br.edu.unoesc.repository.DisciplinaRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class ReportService {
    @Autowired
    DisciplinaRepository disciplinaRepository;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException{
        // List<Disciplina> listaDisciplinas = disciplinaRepository.findByIdProfessor(id);
        
        String path = "C:\\Users\\Public\\Downloads";

        List<Disciplina> listaDisciplinas = disciplinaRepository.findAll();
        File file = ResourceUtils.getFile("classpath:relatorios/disciplinas.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaDisciplinas);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("criado por", "Igor Corona de Matos");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if(reportFormat.equalsIgnoreCase("csv")){
            JRCsvExporter exporter = new JRCsvExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\disciplinas.csv")));
            SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
            configuration.setFieldDelimiter(";");
            exporter.setConfiguration(configuration);
            exporter.exportReport();   
        }

        if(reportFormat.equalsIgnoreCase("xls")){
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(true);
            configuration.setIgnoreGraphics(false);
            File outputFile = new File("output.xlsx");
            OutputStream fileOutputStream = new FileOutputStream(outputFile);
            Exporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\disciplinas.xls")));
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        }

        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\disciplinas.pdf");
        }                
        return "Arquivo "+reportFormat+" gerado em: "+path;
    }
}
