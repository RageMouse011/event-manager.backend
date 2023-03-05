package kz.dar.tech.commentapi.document;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(indexName = "comments")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

    @Id
    private String id;

    @Field(type = FieldType.Keyword, fielddata = true)
    private String eventId;

    @Field(type = FieldType.Text)
    private String message;

    @Field(type = FieldType.Text)
    private String dateOfCreation;
}
