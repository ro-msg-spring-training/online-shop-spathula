package ro.msg.learning.shop.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Revenue extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "location", referencedColumnName = "id")
    private Location location;

    private LocalDate revenueDate;

    private BigDecimal revenueSum;

    @Override
    public String toString() {
        return "Location: " + location.getName() + " | Revenue Sum: " + revenueSum;
    }
}
