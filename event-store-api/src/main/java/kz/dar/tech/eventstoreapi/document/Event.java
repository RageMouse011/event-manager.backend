package kz.dar.tech.eventstoreapi.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(indexName = "events")
public class Event {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Keyword)
    private String category;
    @Field(type = FieldType.Keyword)
    private List<String> sections;
    @Field(type = FieldType.Text)
    private String location;
    @Field(type = FieldType.Date)
    private Date startDate;
    @Field(type = FieldType.Date)
    private Date endDate;
    @Field(type = FieldType.Text)
    private String organizer;
}
