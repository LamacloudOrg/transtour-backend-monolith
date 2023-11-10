package com.transtour.travel.shared.util;

import lombok.Getter;
import lombok.NonNull;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
public class JasperReportUtil {

    private final JasperReport jasperReport;

    private final Logger logger = LoggerFactory.getLogger(JasperReportUtil.class);

    public JasperReportUtil() throws URISyntaxException, JRException, FileNotFoundException {

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jasper-report-voucher.xml")) {

            File file = new File("./report/jasperreport.xml");

            // commons-io
            assert inputStream != null;
            FileUtils.copyInputStreamToFile(inputStream, file);


            jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void generate(String fileName, List<Map<String, Object>> pieceFieldDetailsMaps, Map<String, Object> pieceDetailsMap) throws JRException {
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pieceFieldDetailsMaps);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, pieceDetailsMap, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
        logger.info("Jasper Report generated :" + fileName);
    }

    public Map<String, Object> mapDetail(@NonNull Object o) {
        Map<String, Object> detail = new HashMap<>();
        Field[] fields = o.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            try {
                Field _field = o.getClass().getDeclaredField(field.getName());
                _field.setAccessible(true);
                Object value = _field.get(o);
                detail.put(field.getName(), value != null ? value.toString() : "");
            } catch (IllegalAccessException | NoSuchFieldException e) {
                logger.debug(e.getMessage());
            }
        });

        return detail;
    }

}
