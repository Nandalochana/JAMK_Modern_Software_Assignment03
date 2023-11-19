package pojos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SearchInfo {
    private String customername;
    private String barbername;

    private Date startdate;

    private Date enddate;

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getBarbername() {
        return barbername;
    }

    public void setBarbername(String barbername) {
        this.barbername = barbername;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
