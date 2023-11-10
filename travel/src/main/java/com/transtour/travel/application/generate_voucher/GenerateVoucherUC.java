package com.transtour.travel.application.generate_voucher;

import com.transtour.travel.application.generate_voucher.command.GenerateVoucherCommand;
import com.transtour.travel.domain.Travel;
import com.transtour.travel.domain.TravelInfoPayload;
import com.transtour.travel.domain.TravelNotFoundException;
import com.transtour.travel.infrastructure.persistence.postgres.TravelRepository;
import com.transtour.travel.shared.util.JasperReportUtil;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Service
@Transactional
public class GenerateVoucherUC {

    private static final Logger logger = LoggerFactory.getLogger(GenerateVoucherUC.class);
    private static final String FILE_PATH = "./files/";
    private final TravelRepository repository;
    private final JasperReportUtil jasperReportUtil;


    public GenerateVoucherUC(TravelRepository repository, JasperReportUtil jasperReportUtil) {
        this.repository = repository;
        this.jasperReportUtil = jasperReportUtil;
    }

    @SneakyThrows
    public void generate(GenerateVoucherCommand command) throws IOException {

        try {
            Travel travel = repository.findById(command.getTravelId()).orElseThrow(() -> new TravelNotFoundException(command.getTravelId().toString()));

            ArrayList<Map<String, Object>> pieceFieldDetailsMaps = new ArrayList<>();
            TravelInfoPayload travelInfoPayload = travel.getPayload();
            Map<String, Object> data = jasperReportUtil.mapDetail(travelInfoPayload);
            pieceFieldDetailsMaps.add(data);

            //generate signature
            File file = new File(FILE_PATH + travel.getOrderNumber() + ".pdf");
            FileUtils.writeByteArrayToFile(new File(FILE_PATH + travel.getOrderNumber() + "-signature.png"), travelInfoPayload.getSignature());
            //generate voucher
            //String fileName = FILE_PATH + travel.getOrderNumber() + ".pdf";
            jasperReportUtil.generate(file.getPath(), pieceFieldDetailsMaps, data);

            logger.info("generando voucher");

        } catch (JRException e) {
            logger.info("Error al generar voucer");
            logger.debug(e.getMessage());
        }

    }
}
