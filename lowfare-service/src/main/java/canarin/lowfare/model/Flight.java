package canarin.lowfare.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NonNull
    @Size(max = 3)
    private String origin;
    @NonNull
    @Size(max = 3)
    private String destination;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @NonNull
    private String departureDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @NonNull
    private String returnDate;
//    private int stops;
    @NonNull
    private int adults;
//    @NonNull
//    private String currency;
    @NonNull
    private Double priceTotal;
}
