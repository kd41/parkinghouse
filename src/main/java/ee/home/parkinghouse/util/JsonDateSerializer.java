package ee.home.parkinghouse.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT);
        String formattedDate = dateFormat.format(date);
        gen.writeString(formattedDate);
    }
}
