package com.iscc.propertymanagent.domain;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import lombok.ToString;

        import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Notice implements Serializable {
    private int noticeid;
    private String noticecon;
    private String noticetime;
    private int holdid;

    public Notice(String noticecon, String noticetime, int holdid) {
        this.noticecon = noticecon;
        this.noticetime = noticetime;
        this.holdid = holdid;
    }
}
