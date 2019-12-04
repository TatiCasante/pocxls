package br.com.confidencecambio.aplicacaotemplate.aplicacao.ws.v1.rs;

import br.com.confidencecambio.aplicacaotemplate.aplicacao.ws.v1.rs.model.response.Response;
import br.com.confidencecambio.aplicacaotemplate.aplicacao.ws.v1.rs.model.response.RowResponse;
import br.com.confidencecambio.aplicacaotemplate.aplicacao.ws.v1.rs.model.response.SheetResponse;
import br.com.confidencecambio.aplicacaotemplate.aplicacao.ws.v1.rs.model.response.XLSResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.sourceforge.jtds.jdbc.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

@RequestMapping("/")
@RestController
public class ExemploV1RS {
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Invalid Input")
    })
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<String>> get() {
        return new ResponseEntity<>(new Response<>("ExemploV1RS GET OK"), HttpStatus.OK);
    }

    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Invalid Input")
    })
    @RequestMapping(value = "/leCelulaPorCelula", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<XLSResponse>> leCelulaPorCelula() throws IOException, InvalidFormatException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        XLSResponse response = new XLSResponse();
        response.setDtInicioProcessamento(formatter.format(new Date()));
        Workbook workbook = WorkbookFactory.create(new File("/home/confidence/ControledeSaldo.xlsx"));

        List<SheetResponse> sheetResponseList = new ArrayList<>();
        for(Sheet sheet: workbook) {
            SheetResponse sheetResponse = new SheetResponse();
            if (sheet.getRow(2) != null) {
                sheetResponse.setSaldoInicial(readStringCell(sheet.getRow(2).getCell(2)));
            }

            List<RowResponse> rowResponseList = new ArrayList<RowResponse>();
            for(int i = 5; i < sheet.getLastRowNum(); i ++) {
                Row row = sheet.getRow(i);
                RowResponse rowResponse = new RowResponse();
                rowResponse.setAssociacao(readStringCell(row.getCell(0)));
                rowResponse.setData(readStringCell(row.getCell(1)));
                rowResponse.setDescricao(readStringCell(row.getCell(2)));
                rowResponse.setDebito(readStringCell(row.getCell(3)));
                rowResponse.setCredito(readStringCell(row.getCell(4)));
                rowResponse.setSaldo(readStringCell(row.getCell(5)));
                rowResponse.setConciliacao(readStringCell(row.getCell(6)));
                rowResponse.setOrigem(readStringCell(row.getCell(7)));
                rowResponse.setProposta(readStringCell(row.getCell(8)));
                rowResponse.setLoja(readStringCell(row.getCell(9)));
                rowResponseList.add(rowResponse);
            }
            sheetResponse.setRows(rowResponseList);
            sheetResponseList.add(sheetResponse);
        }
        response.setDtTerminoProcessamento(formatter.format(new Date()));
        response.setSheets(sheetResponseList);
        return new ResponseEntity<>(new Response<>(response), HttpStatus.OK);

    }

    private String readStringCell(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case STRING:
                return cell.getRichStringCellValue().getString();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return String.valueOf(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case FORMULA:
                return String.valueOf(cell.getCellFormula());
            case BLANK:
                return "";
            default:
                return "";
        }
    }
}