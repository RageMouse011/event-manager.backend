package kz.dar.tech.eventstoreapi.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import kz.dar.tech.eventstoreapi.document.Event;

import java.io.IOException;

public class EventSerializer extends JsonSerializer<Event> {

    @Override
    public void serialize(
            Event event,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider
    )
            throws IOException
    {
        jsonGenerator.writeString(event.toString());
    }
}
