package csi;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class Price {
    private long id;
    private String product_code;
    private int number;
    private int depart;
    private Date begin;
    private Date end;
    private long value;
}
