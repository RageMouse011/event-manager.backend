package kz.dar.tech.eventstoreapi.document;

import co.elastic.clients.util.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.dar.tech.eventstoreapi.util.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "events")
public class Event {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Keyword)
    private Category category;
    @Field(type = FieldType.Text)
    private String location;
    @Field(type = FieldType.Integer)
    private int likes;
    @Field(type = FieldType.Text)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private String date;

    @Field(type = FieldType.Text)
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    private String time;
    @Field(type = FieldType.Text, storeNullValue = true)
    private String organizer;

    @Field(type = FieldType.Text)
    private String dateOfCreation;
}
